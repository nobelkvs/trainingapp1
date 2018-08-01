package com.banking.dao;

import com.banking.controller.Bankingcontrollerservlet;
import com.banking.model.Bankingmodel;
import com.banking.utils.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.apache.log4j.Logger;


public class Bankingdaoimpl implements Bankingdao {

    static final Logger log = Logger.getLogger(Bankingdaoimpl.class);
    // Create an object for connecting the jdbc
    MySqlConnection conn = new MySqlConnection();

    private static final String SQL_INSERT_INTO_BANKING = "insert into bankingtable(customername,branch,address,phoneno,emailaddr) values(?,?,?,?,?)";
    private static final String SQL_DELETE_BY_PHONENO = "delete from bankingtable where phoneno = ?";
    private static final String SQL_RETRIVE_BY_BRANCHNAME = "SELECT * FROM bankingtable where branch = ?";


    // INSERTING THE DATA
    public int createbanking(Bankingmodel obj) {
        // Create an object for connection
        Connection connect = null;

        int insertStatus = 0;
        try {
            connect = conn.getConnect();
            // It will implicitly create a new active transaction.
            connect.setAutoCommit(false);
        } catch (Exception e1) {
            // This method helps to trace the exception.
            e1.printStackTrace();
        }
        // Initialize the prepare statemnet object ps to null
        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(SQL_INSERT_INTO_BANKING);
            // Setting the required parameters used while creating
            ps.setString(1, obj.getCustomername());
            ps.setString(2, obj.getBranch());
            ps.setString(3, obj.getAddress());
            ps.setInt(4, obj.getPhoneno());
            ps.setString(5, obj.getEmailaddr());


            insertStatus = ps.executeUpdate();
            if (insertStatus > 0) {
                log.info("successfully inserted");
            } else {
                log.info("failed to insert");

            }

            connect.commit();
        } catch (Exception e2) {
            // This method helps to trace the exception.
            e2.printStackTrace();
            log.info("err occured at " + e2);
            try {
                connect.rollback();
            } catch (SQLException e3) {

                e3.printStackTrace();
            }
        }
        // It returns the value of insertStatus
        return insertStatus;

    }


    // DELETING THE DATA
    public int deletebanking(Integer phoneno) {
        // Create an object for connection
        Connection connect = null;

        int deleteStatus = 0;
        try {
            connect = conn.getConnect();
        } catch (Exception e) {
            // This helps to trace the exception and Identify where is the error.
            e.printStackTrace();
        }
        log.info(connect);
        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(SQL_DELETE_BY_PHONENO);
            ps.setInt(1, phoneno);
            deleteStatus = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleteStatus;
    }

        //RETREIVING THE DATA
    public List<Bankingmodel> retrivebanking(String branch) {
        List<Bankingmodel> branchlist = new ArrayList<Bankingmodel>();
        Connection connect = null;

        PreparedStatement ps = null;

        ResultSet rs = null;

        MySqlConnection msc = new MySqlConnection();
        try {
            connect =  msc.getConnect();
            // Use connection  variable connect to retrieve into database table
            ps = connect.prepareStatement(SQL_RETRIVE_BY_BRANCHNAME);
            ps.setString(1,branch);
            rs = ps.executeQuery();
            while (rs.next()){
                log.info("inside the while");
                // Create object for model
                Bankingmodel b = new Bankingmodel();
                // Retrieving result from resultset
                b.setCustomername(rs.getString(1));
                b.setBranch(rs.getString(2));
                b.setAddress(rs.getString(3));
                b.setPhoneno(Integer.valueOf(rs.getString(4)));
                b.setEmailaddr(rs.getString(5));
                // The result is added to list
                branchlist.add(b);
            }
            log.info("list is the  "+ branchlist);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return branchlist;
    }
    public static void main(String[]args) throws SQLException {
        // Create an object for dao
        Bankingdao bd = new Bankingdaoimpl();
        List <Bankingmodel> b =bd.retrivebanking("li");
        for(Bankingmodel bm : b){
            log.info(b);
        }

    }
}




