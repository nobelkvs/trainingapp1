package UsersService;

import UsersController.UserController;
import UsersModels.UserPojo;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    static final Logger log = Logger.getLogger(UserController.class);


    //create user interface method declaration
    int createUserService(UserPojo userpojo) throws SQLException;

    //remove user by userid method declaration
    int removeUserService(int userId) throws SQLException, ClassNotFoundException;

    //list of users getting userinformation by role
    List<UserPojo> getUserByRoleService(String userRole) throws SQLException;

    //    //single  user information by userid
    List<UserPojo> getUserByIdService(int userId) throws SQLException;

    //update user by userId
    int updateUserByIdService(int userId);


}
