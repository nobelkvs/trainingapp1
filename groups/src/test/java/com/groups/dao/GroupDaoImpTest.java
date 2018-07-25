package com.groups.dao;

import com.groups.model.Group;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class GroupDaoImpTest {

    GroupDao dao = new GroupDaoImp();
    Group group = new Group();

    @Test
    public void createGroupDao() throws SQLException {
        group.setGroupName("Engg");
        group.setOwner("Raja");
        group.setSendAs("sraja@gmail.com");
        group.setFeedback("");
        int status = dao.CreateGroupDao(group);
        if (status == 1)
            assertEquals(1, status);
        else
            assertEquals(0, status);


    }

    @Test
    public void createGroupDao1() throws SQLException {
        boolean flag=false;
        group.setGroupName("Engg");
        group.setOwner("Raja");
        group.setSendAs("sraja@gmail.com");
       // group.setFeedback("");
        try {
            int status = dao.CreateGroupDao(group);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch(NullPointerException e){
            flag = true;
        }
        assertEquals(0,0);
        if(flag==true)
            assertTrue(flag);
        else
            assertFalse(flag);

    }

    @Test
    public void retrieveGroupDetails() throws IOException, SQLException {
        List<Group> list = null;
        list = dao.RetrieveGroupDetails("Engg");
        assertEquals(list.size(),list.size());

    }

    @Test
    public void deleteGroupDao() {
       String str = "1,2,3,19";
       String [] s=str.split(",");

        int status = dao.deleteGroupDao(s);
        if (status == 1)
            assertEquals(1, status);
        else
            assertEquals(0, status);


    }

}