package com.DAO;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is a class which gets connection with database and gives that connection where it is required
 */

public class MyDbConnection {
    static final Logger log = Logger.getLogger(MyDbConnection.class);

    /**
     * @return con
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            //Registering the driver with DriverManager
            Class.forName("com.mysql.jdbc.Driver");
            //Establishing the Connection with the Database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Document_schema", "root", "root_Rocks123");
            log.info("connection:" + con);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
        return con;
    }
}
