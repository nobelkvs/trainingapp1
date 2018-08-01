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
    private static final String SQL_INSERT_INTO_LeaveDetails = "insert into leave_schema.LeaveDetails(Emp_Id,Emp_Name,From_Date,To_Date,Leave_Type,Comments) values(?,?,?,?,?,?)";
    private static final String SQL_DELETE_INTO_LeaveDetails= "delete from LeaveDetails where Emp_Name=?";
    private static final String SQL_RETRIVE = "select Emp_Id,Emp_Name,From_Date,To_Date,Leave_Type,Comments from leave_schema.LeaveDetails where Emp_Name=?";

    static int createStatus = 0;

    @SneakyThrows
    public static int createLms(LmsModel lms) {

        /* Creating the obj for Mysql class */

        MysqlDB db = new MysqlDB();
        Connection conn = null;
        try {

            /*  Getting the connection from database  */
            conn = db.getConnect();
            log.info("connection " + conn);
            PreparedStatement ps = null;

            /* PreaparedStatement is used for executing the database queries and inserting data into database */
            ps = conn.prepareStatement(SQL_INSERT_INTO_LeaveDetails);
            log.info(lms.getFromDate() + " " + lms.getToDate());
            ps.setString(1, String.valueOf(lms.getEmpId()));
            ps.setString(2, lms.getEmpName());
            ps.setString(3, lms.getFromDate());
            ps.setString(4, lms.getToDate());
            ps.setString(5, lms.getLeaveType());
            ps.setString(6, lms.getComments());
          //  ps.setString(7, String.valueOf(lms.getNo_Of_Days()));
            /* Executing the sql query */
            createStatus = ps.executeUpdate();

        } catch (Exception e) {
            log.error("Error in Creating leave Dao"+e);
        }
        /* Returning the createstatus */
        return createStatus;
    }

    /*For retrieving the employee leaves from the database */
    public static List<LmsModel> retrieveByEmpName(String empName) {
        Connection conn = null;
        ResultSet rs = null;
        log.info("in dao retrieve method" + empName);
        List<LmsModel> leavelist = new ArrayList<>();
        try {

            /* Creating the object for my database class */
            MysqlDB db = new MysqlDB();
            conn = db.getConnect();

            /* Gettting the conncetion from database */
            log.info(conn);
        } catch (SQLException e) {
            log.error("Error getting connection in dao retrieve"+e);
        }

        /*Prepared statement to execute the database queries */
        PreparedStatement ps = null;
        try {

            if (conn != null) {


                /* If connection is not null then retrieve the leave from database */
                ps = conn.prepareStatement(SQL_RETRIVE);
            }
            ps.setString(1, empName);

            /* Executing the sql retrieve query */
            rs = ps.executeQuery();

            while (rs.next()) {

           /* Creating the object to the model class */
                LmsModel lm = new LmsModel();
                lm.setEmpName(rs.getString(2));
                lm.setEmpId(rs.getInt(1));
                lm.setFromDate(rs.getString(3));
                lm.setToDate(rs.getString(4));
                lm.setLeaveType(rs.getString(5));
                lm.setComments(rs.getString(6));
               // lm.setNo_Of_Days(rs.getInt(6));
                log.info(lm.getFromDate() + " " + lm.getToDate());
                leavelist.add(lm);
            }
    /* Catching an exception and displaying in log file */
        } catch (Exception e) {

            log.error("Error in dao implementation"+e);
        }

    /* Returning the list of employee leaves */
        return leavelist;
    }


}














