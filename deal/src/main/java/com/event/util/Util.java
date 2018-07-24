package com.event.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    static Connection con;
//logger instance
    static final Logger log=Logger.getLogger(Util.class);

    //getting connection method
    public static Connection getConnected() {

        try {

            //loading driver class
            Class.forName("com.mysql.jdbc.Driver");
            //getting SQL connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/deal_schema", "root", "root_Rocks123");
            if (con != null) {
                log.info("connection established");
            }

        } catch (ClassNotFoundException e) {
            log.error("error in connecting to database: "+e);

        } catch (SQLException e) {
            log.error("error in connecting to database: "+e);
        }
        //returning connection
        return con;
    }


}
