package UsersDaoImpl;

import UsersController.UserController;
import UsersDao.UserDao;
import UsersModels.UserPojo;
import UsersUtils.MyConnection;
import org.apache.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    static final Logger log = Logger.getLogger(UserController.class);


    int insertStatus = 0;


    //querries to perform operation on the database
    final private String INSERT_USER_INFO_TO_USERTABLE = "INSERT INTO UserTable(userName,userEmailAddress,userRole,userPhoneNumber,userCreationDate,password) VALUES(?,?,?,?,?,?)";
    final private String RETRIEVE_USER_BY_USER_ID = "SELECT * FROM UserTable where userId=?";
    final private String RETRIEVE_USER_BY_USER_ROLE = "SELECT * FROM UsersApp_Schema.UserTable where userRole=?";
    final private String REMOVE_USER_BY_USER_ID = "DELETE FROM UsersApp_Schema.UserTable where userId=?";

    // creating instance of myConnection for database connection
    MyConnection mc = new MyConnection();
    Connection con = null;

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     *
     * @param userpojo This is the only paramter to insert data of user in database method
     * @return int This returns the status of the insertion to the database
     * @throws SQLException On input error.
     * @see SQLException
     */

    @Override
    public int createUser(UserPojo userpojo) throws SQLException {

        try {
            //getting the connection from the util package Myconnection class
            con = mc.getConnect();
            //            con.setAutoCommit(false); is to avoid the duplicate or improper information in database
            con.setAutoCommit(false);
            log.info("inside Create UserDao");

            PreparedStatement ps = null;



            //Preparing the insert statement
            ps = con.prepareStatement(INSERT_USER_INFO_TO_USERTABLE);

            ps.setString(1, userpojo.getUserName());
            ps.setString(2, userpojo.getUserEmailAddress());
            ps.setString(3, userpojo.getUserRole());
            ps.setString(4, userpojo.getUserPhoneNumber());
            ps.setDate(5, Date.valueOf(userpojo.getUserCreationDate()));
            ps.setString(6, userpojo.getPassword());

            insertStatus = ps.executeUpdate();
            //  commiting the data to database
            con.commit();
        } catch (Exception e) {

            log.error("in user Dao Implementation" + e);
            con.rollback();
        } finally {
            log.info("Connection Closed..");

            con.close();

        }

        //return the status of insert
        return insertStatus;
    }

    /**
     * This method is used to remove the user information .This is
     * a the simplest form of a class method, just to
     *
     * @param userId This is the only paramter to remove  data of user in database based on the user identity number
     * @return int This returns the status of the database to the database
     * @throws SQLException On input error.
     * @see SQLException
     */


    @Override
    public int removeUser(int userId) throws SQLException {

        int deleteStatus = 0;
        log.info("in getUserByRoleDao");
        con = mc.getConnect();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(REMOVE_USER_BY_USER_ID);
            ps.setInt(1, userId);
            deleteStatus = ps.executeUpdate();

        } catch (Exception e) {
            log.info("error in remove dao" + e);
        }


        return deleteStatus;
    }

    /**
     * @param userRole This is the only paramter to remove  data of user in database based on the user role i.e user or agent
     * @return List This returns the user list  of the users based on the user role
     * @throws SQLException On input error.
     * @see SQLException
     */
    @Override
    public List<UserPojo> getUserByRole(String userRole) throws SQLException {

        log.info("in getUserByRoleDao");
        con = mc.getConnect();
        ResultSet rs = null;
        //creating list of users
        List<UserPojo> listOfUsers = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(RETRIEVE_USER_BY_USER_ROLE);
            ps.setString(1, userRole);
            rs = ps.executeQuery();

            while (rs.next()) {
                //creating object of userspojo
                UserPojo uPojo = new UserPojo();
                uPojo.setUserId(rs.getInt(1));
                uPojo.setUserName(rs.getString(2));
                uPojo.setUserEmailAddress(rs.getString(3));
                uPojo.setUserRole(rs.getString(4));
                uPojo.setUserPhoneNumber(rs.getString(5));
                uPojo.setUserCreationDate(rs.getDate(6).toLocalDate());
                uPojo.setPassword(rs.getString(7));


                //adding users to list
                listOfUsers.add(uPojo);
            }

        } catch (Exception e) {
            log.info("error in dao retrive" + e);

        }


        return listOfUsers;
    }

    /**
     * This method is used to remove the user information .This is
     * a the simplest form of a class method, just to
     *
     * @param userId This is the only paramter to get the data of user in database based on the user identity number
     * @return List This returns the list of users
     * @throws SQLException On input error.
     * @see SQLException
     */


    @Override
    public List<UserPojo> getUserById(int userId) throws SQLException {

        List<UserPojo> users = new ArrayList();

        log.info("in getUserByIdDao");
        log.info("inside dao " + userId);

        con = mc.getConnect();
        ResultSet rs = null;
        try {
            PreparedStatement ps = null;
            ps = con.prepareStatement(RETRIEVE_USER_BY_USER_ID);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                UserPojo uPojo = new UserPojo();
                uPojo.setUserId(rs.getInt(1));
                uPojo.setUserName(rs.getString(2));
                uPojo.setUserEmailAddress(rs.getString(3));
                uPojo.setUserRole(rs.getString(4));
                uPojo.setUserPhoneNumber(rs.getString(5));
                uPojo.setUserCreationDate(rs.getDate(6).toLocalDate());
                uPojo.setPassword(rs.getString(7));
                users.add(uPojo);
            }
            log.info("after while inretrive by id");


        } catch (Exception e) {
            log.info("errorrr" + e);
        }

        return users;
    }

    @Override
    public int updateUserById(int userId) {
        return 0;
    }

}
