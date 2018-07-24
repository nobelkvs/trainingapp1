package UsersDao;

import UsersController.UserController;
import UsersModels.UserPojo;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    static final Logger log = Logger.getLogger(UserController.class);


    //create user interface method declaration
    int createUserDao(UserPojo userpojo) throws SQLException;

    //remove user by userid method declaration
    int removeUserDao(int userId) throws SQLException, ClassNotFoundException;

    //list of users getting userinformation by role
    List<UserPojo> getUserByRoleDao(String userRole) throws SQLException;

    //    //single  user information by userid
    List<UserPojo> getUserByIdDao(int userId) throws SQLException;

    //update user by userId
    int updateUserByIdDao(int userId);
}
