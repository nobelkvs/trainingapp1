package com.bookOrder.utils;

import com.bookOrder.controller.OrderController;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * This class has method to create database connection
 */
public class MysqlConnection {

    // Create Logger instance
    static final Logger log = Logger.getLogger(OrderController.class);


    Connection con = null;

    // Method to create connection to database
    public Connection getConnection() {

        try {

            // Loads the Driver class
            Class.forName("com.mysql.jdbc.Driver");

            // Gets the sql connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_order", "root", "root_Rocks123");

            // Checking the connection object
            if (con != null) {
                log.info("in myConnection... connected");
            } else {
                log.info("in myConnection... not connected to database");
            }

        } catch (Exception e) {
            log.error("error while connecting to database" + e);
        }

        return con;
    }
}
