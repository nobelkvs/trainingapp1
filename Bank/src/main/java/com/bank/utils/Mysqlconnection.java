package com.bank.utils;
import com.bank.controller.bankcontrollerservlet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Mysqlconnection {
    // It is able to access the logger from all classes in Banking Application
    static final Logger log=Logger.getLogger(Mysqlconnection.class);
    // Create an object for connection
    Connection con=null;
    // The getConnect() method establishes a explicit connection to a database server.
    public Connection getConnect() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // It establishes a connection to a database by obtaining a Connection object.
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root_rocks123");
            if(con!=null)
            {
                log.info("connection established");
            }else
                {log.info("failed");}
        } catch (ClassNotFoundException e) {
            // This method helps to trace the exception.
            e.printStackTrace();
        }
        // It returns the value of con
        return con;
    }
}
