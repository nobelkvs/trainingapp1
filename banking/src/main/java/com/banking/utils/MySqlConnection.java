package com.banking.utils;
import com.banking.controller.Bankingcontrollerservlet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MySqlConnection {

    static final Logger log=Logger.getLogger(MySqlConnection.class);
    // Create an object for connecting the jdbc
    Connection connect=null;

    public Connection getConnect() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // It establishes a connection to a database by obtaining a Connection object.
            connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/banking","root","root_rocks123");
            if(connect!=null)
            {
                log.info("connection is created");
            }else
            {log.info("connection failed");}
        } catch (ClassNotFoundException e) {
            // This method helps to track the exception by printing the description and where the error is.
            e.printStackTrace();
        }
        // It returns the value of connect.
        return connect;
    }
}
