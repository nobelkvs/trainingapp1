package com.groups.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Creating the connection
 */
public class MysqlConnection {
    Logger log = Logger.getLogger(MysqlConnection.class);
    Connection con = null;

    /**
     * Creating the connection
     *
     * @return con
     * @throws SQLException
     */
    public Connection getConnect() throws SQLException {
        try {
            // Registering the mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            //getConnection accepts 3 parameters url, username, password of mysql database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Groups", "root", "root_rocks123");
            log.info(con);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.info("Not Connected to database");
        }
        return con;

    }
}
