package com.contacts.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContactDBConnection {
    static Connection con=null;

    public static Connection getConnect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/applet_bb","root","root");

        return con;
    }
}
