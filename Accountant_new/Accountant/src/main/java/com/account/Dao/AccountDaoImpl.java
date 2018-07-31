package com.account.Dao;

import com.account.controller.AccountController;
import com.account.model.Account;
import org.apache.log4j.Logger;
import util.MysqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @var SQL_INSERT_INTO_ACCOUNT defines the query for inserting data into database
 * @var QUERY_TO_RETRIVE defines the query to retrieve the data from database
 * @var SQL_DELETE defines the query to delete the data.
  */

// Implmenting the methods defined in accountDao.java
public class AccountDaoImpl implements accountDao{

    // Creating object of log so that we can use it for logging purpose
    static final Logger log=Logger.getLogger(AccountController.class);

    // Making mysql connection object to establish the connection with database--------------------------------------- 
    MysqlConnection c = new MysqlConnection();

    // Defining the SQL queries and storing them in variable to use them wherever required------------------------------
    private static final String SQL_INSERT_INTO_ACCOUNT = "insert into Account(Fname,Lname,Bankname,Branch,Address,Phone) values(?,?,?,?,?,?)";
    private static final String QUERY_TO_RETRIVE = "SELECT * FROM Account where Fname = ?";
    private static final String   SQL_DELETE = "delete from Account where id = ?";

    /**
    * createAccountDao is use to insert the data in the database
    * @param account
    * @return insertstatus
    * @throws SQLException
     */
    public int createAccountDao(Account account) throws SQLException {

        Connection conn = null;

        // Taking the variable to check for the whether insertion is done or not----------------------------------------
        int insertStatus = 0;

        try {

            // Calling the getconnect() method for database connection string------------------------------------------
            conn = c.getConnect();
            conn.setAutoCommit(false);
        } 
         catch (Exception e) {

             // Print stack trace for tracing the exacet location of the error-------------------------------------------
            e.printStackTrace();
        }

        PreparedStatement ps = null;
        try {

            // Passing the query prepared stmt to fetch the value
            ps = conn.prepareStatement(SQL_INSERT_INTO_ACCOUNT);

            // Logging the data which is passed which will be available in the log file
            log.info("fname=="+account.getFname());

            // Getting the parameters passed in the query and setting them-------------------------------------------
            ps.setString(1,account.getFname());
            ps.setString(2,account.getLname());
            ps.setString(3,account.getBankname());
            ps.setString(4,account.getBranch());
            ps.setString(5,account.getAddress());
            ps.setInt(6,account.getPhone());

            // Logging the msg -----------------------------------------------------------------------------------
            log.info("insertion in process");

            // On successfully insertion of data the value will increased by one which shows the operation is successful
            insertStatus =  ps.executeUpdate();
            log.info(insertStatus);

            // commit the operation to complete the action in sql DB--------------------------------------------
            conn.commit();
        } catch (Exception e1) {
            log.info("catch block"+e1);
            try {
                conn.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return insertStatus;
    }

/**
    * retrieveAccountDao is use to retrieve the data in the database
    * @param Fname
    * @return alist
    * @throws SQLException
     */
    public List<Account> retrieveAccountDao(String Fname) {

        // Declaring the list to store the retrieved value----------------------------------------------------------
        List<Account> alist = new ArrayList<Account>();
        


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MysqlConnection m = new MysqlConnection();
        try {
            conn =  m.getConnect();

            // Passing the query 
            ps = conn.prepareStatement(QUERY_TO_RETRIVE);

            // Passing the value of the parameter which is bassically using to fetch the value from db-------------
            ps.setString(1,Fname);
            rs = ps.executeQuery();
            log.info("before while in retrieving function");
            while (rs.next()){
                log.info("inside while in retrieving function");

             // Making model class object and getting and setting the value --------------------------------------   
             Account account=new Account();
             account.setId(rs.getInt(1));
             account.setFname(rs.getString(2));
             account.setLname(rs.getString(3));
             account.setBankname(rs.getString(4));
             account.setBranch(rs.getString(5));
             account.setAddress(rs.getString(6));
             
                account.setPhone(rs.getInt(7));

                //  log.info(rs.getInt(1)+" "+rs.getLong(2)+" "+rs.getString(3)+" "+rs.getLong(4)+" "+rs.getString(5)+" "+rs.getInt(6)+" "+rs.getLong(7)+" "+rs.getString(8)+" "+rs.getString(9));

                alist.add(account);
            }
            
            // Printing in log the list of parameters fetched from database
            log.info("list  "+alist);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alist;
    }

    /**
    * deleteAccount is use to delete the data in the database
    * @param Id
    * @return deleteStatus
    * @throws SQLException
     */
    public int deleteAccount(String[] Id) throws SQLException {
        Connection conn = null;
        int deleteStatus = 0;
        try {
            conn = c.getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        PreparedStatement ps = null;
        try {
              
              log.info("inside deleteting id");

            // There might be chances user want multiple records to be deleted for that purpose use loop to iterate through the parameters passed and delete them
            for (int i = 0; i<Id.length ; i++) {
                ps = conn.prepareStatement(SQL_DELETE);
                ps.setString(1, Id[i]);
                deleteStatus = ps.executeUpdate();
            }
        } catch (SQLException e) {

            log.error("error in deleting data");
            e.printStackTrace();
        }

        // Returning the status
        return deleteStatus;
    }


}
