package com.note.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {


    Connection con = null;

    public Connection getConnect() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Notes", "root", "root_rocks123");

            System.out.print(con);
            System.out.print("connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;

    }

}












