package com.bookOrder.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {

    Connection con = null;

    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book_order", "root", "root_Rocks123");

            System.out.print(con);
            System.out.print("connected");


        } catch (Exception e) {
            System.out.println("not connected");
        }


        return con;
    }
}
