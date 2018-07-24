package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This is the DBConnection class which gives the connection to the Database
 */

public class DBConnection {

    /**
     * This is the getConnection method with no arguments. This method returns the connection object when ever it is called. It is a static method.
     * @return
     */
    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Policy_schema", "root", "root_rocks123");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
}
