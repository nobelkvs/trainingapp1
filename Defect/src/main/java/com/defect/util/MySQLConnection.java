package com.defect.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * MySQLConnection of defect Application
 */
public class MySQLConnection {

    // Create instance of logger in defectServiceImplementation class
    static final Logger log = Logger.getLogger(MySQLConnection.class);

    public Connection connection = null;

    /**
     *method implementation of getConnection
     * @return
     */
    public Connection getConnection() {

        log.info("In getConnection method of MySQLConnection class");

        try{
            String URL = "jdbc:mysql://localhost:3306/Defects";
            String root = "root";
            String password = "root_rocks123";

            // Load the drivers class file into memory at the runtime
            Class.forName("com.mysql.jdbc.Driver");

            // call the constructor of the driver class at compile time
            connection = DriverManager.getConnection(URL,root,password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }
}
