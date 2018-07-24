package UsersDaoImpl;

import UsersDao.UserDao;
import UsersModels.UserPojo;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    UserDao testObj=new UserDaoImpl();

    @Test
    public void testCreateUserService() {



    }

    @Test
    public void testRemoveUserService() throws SQLException, ClassNotFoundException {

       int status= testObj.removeUserDao(11);
       assertEquals(status,status);


    }

    @Test
    public void testGetUserByRoleService() throws SQLException {

        List<UserPojo> list= testObj.getUserByRoleDao("agent");
        assertEquals(list.size(),list.size());
    }

    @Test
    public void testGetUserByIdService() throws SQLException {

        List<UserPojo> list= testObj.getUserByIdDao(11);

        assertEquals(list.size(),list.size());

    }
}