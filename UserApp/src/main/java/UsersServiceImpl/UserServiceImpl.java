package UsersServiceImpl;

import UsersController.UserController;
import UsersDao.UserDao;
import UsersDaoImpl.UserDaoImpl;
import UsersModels.UserPojo;
import UsersService.UserService;


import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.*;

public class UserServiceImpl implements UserService {
    static final Logger log = Logger.getLogger(UserController.class);


    UserDao userDao = null;


    /**
     * a the simplest form of a class method, just to createUserDao
     *
     * @param userpojo This is the only paramter to pass it to us
     * @return int This returns the status of the database to the database
     * @throws SQLException On input error.
     * @see SQLException
     */
    @Override
    public int createUser(UserPojo userpojo) throws SQLException {
        userDao = new UserDaoImpl();
        int createStatus = userDao.createUser(userpojo);


        return createStatus;
    }

    /**
     * a the simplest form of a class method, just to removeUserDao
     *
     * @param userId This is the only paramter to pass it to us
     * @return int This returns delete status of user
     * @throws SQLException On input error.
     * @see SQLException
     */

    @Override
    public int removeUser(int userId) throws SQLException, ClassNotFoundException {
        userDao = new UserDaoImpl();
        int deleteStatus = userDao.removeUser(userId);
        return deleteStatus;
    }

    /**
     * a the simplest form of a class method, just to getUserByRoleDao
     *
     * @param userRole This is the only paramter to pass it to us
     * @return List  This returns list of users based on the role
     * @throws SQLException On input error.
     * @see SQLException
     */

    @Override
    public List<UserPojo> getUserByRole(String userRole) throws SQLException {
        userDao = new UserDaoImpl();
        List<UserPojo> up = userDao.getUserByRole(userRole);
        return up;
    }

    /**
     * a the simplest form of a class method, just to getUserByUserId
     *
     * @param userId This is the only paramter to pass it to us
     * @return List  This returns list of users based on the user id
     * @throws SQLException On input error.
     * @see SQLException
     */

    @Override
    public List<UserPojo> getUserById(int userId) throws SQLException {
        log.info("in serviceimpl getUserByIdService " + userId);

        userDao = new UserDaoImpl();
        log.info("inside service " + userId);
        List<UserPojo> listOfUsers = userDao.getUserById(userId);

        return listOfUsers;
    }

    @Override
    public int updateUserById(int userId) {
        userDao = new UserDaoImpl();


        return 0;
    }
}
