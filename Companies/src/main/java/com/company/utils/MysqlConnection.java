package com.company.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class for Database connection
 */
public class MysqlConnection {
    //Creating instance of logger
    static final Logger log = Logger.getLogger(MysqlConnection.class);

    Connection con = null;

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/companies", "root", "root_Rocks123");
            log.info(con);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;

    }


}












