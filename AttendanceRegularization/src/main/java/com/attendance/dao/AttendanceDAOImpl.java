package com.attendance.dao;

import com.attendance.model.Attendance;
import com.attendance.util.MysqlConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Create AttendanceDAOImpl class
 */

public class AttendanceDAOImpl implements AttendanceDAO {

    //Create instance of logger in AttendanceDAOImpl class
    static final Logger log = Logger.getLogger(AttendanceDAOImpl.class);

    //Declare sql commands for attendance application
    private static final String SQL_INSERT_INTO_TABLE = "insert into attendance(id,Date,checkin_time,checkout_time) values(?,?,?,?)";

    private static final String SQL_GET_ATTENDANCES_BY_DATE = "SELECT * FROM attendance WHERE Date=?";

    private static final String SQL_DELETE_DATA_BY_ID = " delete  FROM attendance WHERE Id=?";

    //Create object for MysqlConnection class
    MysqlConnection c = new MysqlConnection();

    //Create createAttendanceDAO method
    public int createAttendanceDAO(Attendance attendance) throws SQLException {


        //Get the Connection
        Connection conn = null;
        int insertStatus = 0;

        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.error("can not connect");
        }
        log.info("Connection id" + conn);

        //Prepare statemennt
        PreparedStatement ps = null;
        conn.setAutoCommit(false);

        //Enable to send sql commands and insert into database
        ps = conn.prepareStatement(SQL_INSERT_INTO_TABLE);

        //Set the values into database
        ps.setInt(1, attendance.getId());
        ps.setDate(2, Date.valueOf(attendance.getDate()));
        ps.setTime(3, attendance.getCheckintime());
        ps.setTime(4, attendance.getCheckouttime());

        //Execute the sql commands
        insertStatus = ps.executeUpdate();
        conn.commit();
        return insertStatus;

    }

    /**
     * Create method to retrieve the data from the database
     *
     * @param date
     * @return
     * @throws SQLException
     */

    public List<Attendance> retrieveAttendanceDAOByDate(LocalDate date) throws SQLException {

        //Create array list to store database values
        List<Attendance> listAttendance = new ArrayList<Attendance>();

        //Get the Connection
        Connection conn = null;

        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.error("can not connect");
        }
        log.info(conn);

        //Prepare statemennt
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Enable to send sql commands retrieve from database
        ps = conn.prepareStatement(SQL_GET_ATTENDANCES_BY_DATE);

        ps.setDate(1, Date.valueOf(date));

        //Execute the sql commands
        rs = ps.executeQuery();
        while (rs.next()) {
            //Create object for model class
            Attendance ad = new Attendance();

            //Set the values into model
            ad.setId(rs.getInt(1));
            ad.setDate(rs.getDate(2).toLocalDate());
            ad.setCheckintime(rs.getTime(3));
            ad.setCheckouttime(rs.getTime(4));

            //Add the values into list
            listAttendance.add(ad);

        }

        return listAttendance;
    }

    /**
     * Create delete method to delete particular data from database
     *
     * @param multiple
     * @return
     * @throws SQLException
     */
    public int deleteAttendanceDAO(String[] multiple) throws SQLException {

        // Get the Connection
        Connection conn = null;

        int deleteStatus = 0;

        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.error("can not connect");
        }
        log.info(conn);
        for (int i = 0; i < multiple.length; i++) {
            //Prepare statemennt
            PreparedStatement ps = null;

            //Enable to send sql commands delete from database
            ps = conn.prepareStatement(SQL_DELETE_DATA_BY_ID);
            ps.setInt(1, Integer.parseInt(multiple[i]));

            //Execute the sql commands
            deleteStatus = ps.executeUpdate();
        }

        log.info(deleteStatus);

        return deleteStatus;
    }

}
