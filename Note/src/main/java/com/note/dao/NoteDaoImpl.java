package com.note.dao;

import com.note.model.NoteModel;
import com.note.utils.MysqlConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteDaoImpl implements NoteDao {

    // sql queries
    private String SQL_INSERT = "insert into  note (subject,note,owner) values (?,?,?)";
    private String SQL_DELETE = " DELETE FROM note WHERE id = ?";
    private String SQL_RETRIVE = "SELECT * FROM note ";

    //logging object
    Logger log = Logger.getLogger(NoteDaoImpl.class);
    //sql connection object
    MysqlConnection c = new MysqlConnection();

    int status = 0;
    int count = 0;
    Connection conn = null;

    /**
     * method to create a note, stores it in database
     *
     * @param noteModel
     * @return
     */


    public int createNote(NoteModel noteModel) {
        log.info("into create dao");
        log.info("object : " + noteModel);
        int insertStatus = 0;

        //checking database connection
        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.error("can not connect");
        }
        log.info("Connection id" + conn);
        PreparedStatement ps = null;

        try {

            conn.setAutoCommit(false);
            // calling sql query and setting values to it
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, noteModel.getSubject());
            ps.setString(2, noteModel.getNote());
            ps.setString(3, noteModel.getOwner());

            insertStatus = ps.executeUpdate();
            conn.commit();

        } catch (Exception e1) {
            log.error("sql exception " + e1);
            e1.printStackTrace();
        }

        return insertStatus;
    }

    /**
     * method to delete a note
     *
     * @param arr
     * @return
     */
    public int[] deleteNote(String[] arr) {
        log.info("in delete dao");
        int[] deletearr = new int[arr.length
                ];

        log.info("array in dao : " + arr);
        //checking db connection
        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.error("can not connect" + e);
        }
        //System.out.print(conn);
        PreparedStatement ps = null;
        for (int i = 0; i < arr.length; i++) {

            try {
                log.info("in try for delete" + arr.length);
                // calling sql query and setting values to it
                ps = conn.prepareStatement(SQL_DELETE);
                ps.setInt(1, Integer.parseInt(arr[i]));
                status = ps.executeUpdate();
                log.info("deleted" + status);
                deletearr[i] = status;
                log.info(deletearr[i]);
//                if(status >  0) {
//                    count+= 1;
//                    log.info("in if "+status +count);
//                }
                //else array index store in variable returjn to js alerrt

            } catch (SQLException e) {
                log.error("sql exception " + e);
                e.printStackTrace();
            }

            log.info("in loop " + i);
        }

        log.info(status);
        log.info("delete array is   " + deletearr.length);
        //log.info(count);
        // return count;
        return deletearr;
    }

    /**
     * method to retrieve data
     *
     * @return
     */

    public List<NoteModel> retrieveByNoteName() {
        List<NoteModel> list = new ArrayList<NoteModel>();
        log.info("in retrive dao");
        //log.info(name);
        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.info("can not connect");
        }
        log.info(conn);
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            conn.setAutoCommit(false);
            log.info("try");
            ps = conn.prepareStatement(SQL_RETRIVE);
            // ps.setString(1, name);
            rs = ps.executeQuery();

            while (rs.next()) {
                log.info("while");
                NoteModel not = new NoteModel();
                not.setId(rs.getInt(1));
                not.setSubject(rs.getString(2));
                not.setNote(rs.getString(3));
                not.setOwner(rs.getString(4));
                list.add(not);
            }


            //return list;
        } catch (SQLException e) {
            log.error("sql exception " + e);
        }

        return list;

    }
}
