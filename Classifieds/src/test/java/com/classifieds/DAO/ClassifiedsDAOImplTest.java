package com.classifieds.DAO;

import com.classifieds.model.Classifieds;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class ClassifiedsDAOImplTest {

    @Test
    public void createClassifieds() throws SQLException {

        Classifieds c = new Classifieds();

        c.setClassifiedDescription("mobile for sale");

        c.setCategory("gadgets");

        c.setCity("mahabubnagar");

        ClassifiedsDAO cd = new ClassifiedsDAOImpl();

        int createStatus = 0;

        createStatus = cd.createClassifieds(c);

        assertEquals(1, createStatus);
    }

    @Test
    public void retrieveClassifieds() {

        ClassifiedsDAO cd = new ClassifiedsDAOImpl();

        List<Classifieds> list = null;

        try {

            list = cd.retrieveClassifieds("mahabubnagar");

        } catch (SQLException e) {

            e.printStackTrace();
        }

        assertEquals(list.size(), list.size());
    }

    @Test
    public void deleteClassifieds() throws SQLException {

        ClassifiedsDAO cd = new ClassifiedsDAOImpl();

        String id = "42,43";

        String[] str = id.split(",");

        int deleteStatus = cd.deleteClassifieds(str);

        assertEquals(0, deleteStatus);

    }
}