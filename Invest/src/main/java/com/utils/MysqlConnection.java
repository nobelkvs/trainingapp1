package com.utils;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * used for estabishment of database connectivity
 */
public class MysqlConnection {
    static final Logger log=Logger.getLogger(MysqlConnection.class);
    Connection con = null;
    public Connection getConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Invest", "root", "root_rocks123");
            log.info("connected");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("not connected");
        }
        return con;

    }


}












