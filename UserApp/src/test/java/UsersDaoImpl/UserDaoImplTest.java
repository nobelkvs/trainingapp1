package UsersDaoImpl;

import UsersDao.UserDao;
import UsersModels.UserPojo;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    UserDao testObj=new UserDaoImpl();

    @Test
    public void testCreateUserService() throws SQLException {

        UserPojo testPojo=new UserPojo();
        testPojo.setUserName("Sitha");
        testPojo.setUserEmailAddress("sitha@yahoo.com");
        testPojo.setPassword("Sitha@Ram1");
        testPojo.setUserPhoneNumber("9638527413");
        testPojo.setUserCreationDate(LocalDate.now());
        testPojo.setUserRole("user");


        int createstatus=testObj.createUser(testPojo);
        assertEquals(createstatus,createstatus);

    }

    @Test
    public void testRemoveUserService() throws SQLException, ClassNotFoundException {

       int status= testObj.removeUser(11);

       assertEquals(status,status);


    }

    @Test
    public void testGetUserByRoleService() throws SQLException {

        List<UserPojo> list= testObj.getUserByRole("agent");
        assertEquals(list.size(),list.size());
    }

    @Test
    public void testGetUserByIdService() throws SQLException {

        List<UserPojo> list= testObj.getUserById(11);

        assertEquals(list.size(),list.size());

    }
}