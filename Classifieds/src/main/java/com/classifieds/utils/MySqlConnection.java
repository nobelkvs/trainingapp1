package com.classifieds.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class to establish connection with database
 */
public class MySqlConnection {

    //Creating the instance of logger
final static Logger log=Logger.getLogger(MySqlConnection.class);
    //Defining connection object
    Connection con = null;

    public Connection getConnect() throws SQLException {

        //Load and register Drivers
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //Establing connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Classifieds", "root", "root_Rocks123");

           log.info(con);
            log.info("Connected");


        }
        catch (ClassNotFoundException e) {

            e.printStackTrace();

        }


        return con;

    }
}
