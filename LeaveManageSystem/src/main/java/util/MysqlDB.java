
package util;

import lombok.SneakyThrows;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDB {

        static Connection con=null;

    @SneakyThrows
    public static Connection getConnect() throws SQLException{

            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_schema","root","root_rocks123");
            return con;
    }

}
