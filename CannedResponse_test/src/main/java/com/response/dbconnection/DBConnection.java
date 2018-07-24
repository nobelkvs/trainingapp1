package com.response.dbconnection;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class DBConnection for establishing a  Database Connection with mysql Database
 */
public class DBConnection {
    static final Logger log = Logger.getLogger(DBConnection.class);

    Connection con = null;

    /**
     * @return con It Returns Connection Object
     * @throws SQLException
     */

    public Connection getDBConnection() throws SQLException {

        try {
            //Regstering Driver here
            Class.forName("com.mysql.jdbc.Driver");

            //Getting Conncetion here
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Response_test", "root", "root_rocks123");

            log.info("DB Connection");
            log.info(con);

        } catch (ClassNotFoundException e) {
            log.error(e);
        }
        return con;

    }
}




