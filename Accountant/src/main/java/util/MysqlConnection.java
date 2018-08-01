package util;

import com.account.controller.AccountController;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnection {
    static final Logger log=Logger.getLogger(AccountController.class);
        Connection con=null;

       public Connection getConnect() throws SQLException{

            try {

                Class.forName("com.mysql.jdbc.Driver");

                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Accountant","root","root_rocks123");
                log.info("connection extablished");

            } catch (ClassNotFoundException e) {

                log.error("error in connection");
                e.printStackTrace();
            }

            return con;
        }

    }












