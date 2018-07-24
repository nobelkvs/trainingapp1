package UsersUtils;

import lombok.SneakyThrows;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    Connection con=null;

    //initialing the Logger of MyConnection
    private static final Logger log=Logger.getLogger(MyConnection.class);


    /**

     * a the simplest form of a class method, just to get the mysql connection connection
     * @return object  This returns connection object of mysql database
     * @exception Exception On input error.
     * @see Exception
     */

    @SneakyThrows
    public Connection getConnect() throws SQLException{


            Class.forName("com.mysql.jdbc.Driver");

            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/UsersApp_Schema","root","root_Rocks123");

            log.info(con.toString());

        return con;

    }
}
