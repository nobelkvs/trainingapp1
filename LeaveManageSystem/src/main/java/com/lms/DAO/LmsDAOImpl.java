package com.lms.DAO;

import com.lms.Model.LmsModel;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import util.MysqlDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LmsDAOImpl {

     /*This logging allows you to report and persist error and warning
   messages as well as info messages
   */

    static final Logger log = Logger.getLogger(LmsDAOImpl.class);

    /*mysql database queries for creating,updating ,deleting and retrieving the employee leave
     */
    private static final String SQL_INSERT_INTO_EmpLeave = "insert into leave_schema.EmpLeave(Emp_Id,From_Date,To_Date,Leave_Type,Comments,No_Of_Days) values(?,?,?,?,?,?)";
    private static final String SQL_UPDATE_INTO_EmpLeave = "update EmpLeave SET From_Date=?, To_Date=? where  Emp_Id=?";
    private static final String SQL_DELETE_INTO_EmpLeave = "delete from EmpLeave where Emp_Id=?";
    private static final String SQL_RETRIVE = "select From_Date,To_Date,Leave_Type,Comments,No_Of_Days from leave_schema.EmpLeave where Emp_Id=?";

    static int createStatus = 0;

    @SneakyThrows
    public static int createLmsDAO(LmsModel lms) {
        //Creating the obj for Mysql class
            MysqlDB db = new MysqlDB();
            Connection conn = null;
            try{
         //Getting the connection from database
                    conn = db.getConnect();
                    log.info("connection " + conn);
                    PreparedStatement ps = null;
          //PreaparedStatement is used for executing the database queries.
                    ps = conn.prepareStatement(SQL_INSERT_INTO_EmpLeave);
                    log.info(lms.getFromDate() + " " + lms.getToDate());
                    ps.setString(1, String.valueOf(lms.getEmpId()));
                    ps.setString(2, lms.getFromDate());
                    ps.setString(3, lms.getToDate());
                    ps.setString(4, lms.getLeaveType());
                    ps.setString(5, lms.getComments());
                    ps.setString(6, String.valueOf(lms.getNo_Of_Days()));
                    createStatus = ps.executeUpdate();

          } catch (Exception e) {
                log.error(e);
            }
                return createStatus;
        }

//For retrieving the employee leaves in database.
    public static List<LmsModel> retrieveByEmpId(int EmpId) {
         Connection conn = null;
         ResultSet rs = null;
         log.info("dao" + EmpId);
         List<LmsModel> leavelist = new ArrayList<>();
        try {
              MysqlDB db = new MysqlDB();
              conn = db.getConnect();
              log.info(conn);
        } catch (SQLException e) {
               log.error(e);
        }

        PreparedStatement ps = null;
        try {
              log.info(EmpId);
              if (conn != null) {
                ps = conn.prepareStatement(SQL_RETRIVE);
            }
                ps.setInt(1, EmpId);
                log.info(EmpId);
                rs = ps.executeQuery();
                log.info("after rs" + rs);
                log.info("RESULT SET IS..."+rs);
                while (rs.next()) {
                log.info("in while");
                LmsModel lm = new LmsModel();
                lm.setEmpId(EmpId);

                lm.setFromDate(rs.getString(1));
                lm.setToDate(rs.getString(2));
                lm.setLeaveType(rs.getString(3));
                lm.setComments(rs.getString(4));
                lm.setNo_Of_Days(rs.getInt(5));
                log.info(lm.getFromDate() + " " + lm.getToDate());
                leavelist.add(lm);
            }

        } catch (Exception e) {
                e.printStackTrace();
                log.error(e);
        }

                log.info(leavelist);
                return leavelist;
    }
//For deleting the employee leave from the database
    public static int deleteLmsDAO(int EmpId) {
        // MysqlDB db=null;
           Connection conn = null;
           int deleteStatus = 0;
        try {
             MysqlDB db = new MysqlDB();
             conn = db.getConnect();
             log.info(conn);
        } catch (SQLException e) {
              log.error(e);
        }
        PreparedStatement ps = null;

        try {
             log.info("tryblock");
             ps = conn.prepareStatement(SQL_DELETE_INTO_EmpLeave);
             ps.setInt(1, EmpId);
             log.info(EmpId);
             deleteStatus = ps.executeUpdate();
             log.info(deleteStatus);
        } catch (Exception e) {
             log.error(e);
        }

        return deleteStatus;


    }

   /* public int updateLmsDAO(LmsModel lms) {
        Connection conn = null;
        int updateStatus = 0;
        try {
            MysqlDB db=new MysqlDB();
            conn = db.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement ps = null;

        try {

            ps = conn.prepareStatement(SQL_UPDATE_INTO_EmpLeave );
            ps.setInt(3, lms.getEmpId());
            ps.setString(1,lms.getFromDate());
            ps.setString(2, lms.getToDate());
            updateStatus = ps.executeUpdate();
        } catch (Exception e) {
            log.error(e);
        }

        return updateStatus;


    }*/



}









