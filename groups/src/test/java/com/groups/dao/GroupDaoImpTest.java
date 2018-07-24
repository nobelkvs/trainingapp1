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
        assertEquals(1,status);

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
        assertEquals(0,status);


    }

}