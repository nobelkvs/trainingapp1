package com.event.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MysqlConnection {


    Connection con=null;

    public Connection getConnect() throws SQLException,ClassNotFoundException{


        Class.forName("com.mysql.jdbc.Driver");

        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Event","root","root_Rocks123");


        return con;

    }


}





