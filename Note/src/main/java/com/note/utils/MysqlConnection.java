package com.note.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {


    Connection con = null;  //connection object
    //logging object
    Logger log = Logger.getLogger(MysqlConnection.class);

    public Connection getConnect() throws SQLException {

        try {
            //loading class
            Class.forName("com.mysql.jdbc.Driver");
            //loading driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Notes", "root", "root_rocks123");

            log.info(con);
            log.info("connected");
        } catch (ClassNotFoundException e) {
            log.error(e);
            e.printStackTrace();
        }
        return con;

    }

}












