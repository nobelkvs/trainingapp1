
package util;

import com.lms.DAO.LmsDAOImpl;
import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDB {

     /**This logging allows you to report and persist error and warning
       messages as well as info messages
   */

    static final Logger log = Logger.getLogger(MysqlDB.class);

        static Connection con=null;

    @SneakyThrows
    public static Connection getConnect() throws SQLException{

            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/leave_schema","root","root_rocks123");
            return con;
    }

}
