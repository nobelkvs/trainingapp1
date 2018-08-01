package com.event.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This program is used to provide connection to the database
 */

public class MysqlConnection {


    Connection con=null;

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public Connection getConnect() throws SQLException,ClassNotFoundException{


        Class.forName("com.mysql.jdbc.Driver");
        //Getting connection from localhost and created database by providing username, password
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Event","root","root_Rocks123");

        //Returning connection
        return con;

    }


}





