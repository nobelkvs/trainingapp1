package com.attendance.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {


    Connection con = null;

    /**
     * Method implementation of get connection
     *
     * @return
     * @throws SQLException
     */

    public Connection getConnect() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            //Load the driver class into memory at run time
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Attendance_Regularization", "root", "root_Rocks123");

            System.out.print(con);
            System.out.print("connected");


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return con;

    }


}












