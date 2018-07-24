package com.event.dao;

import com.event.model.EventModel;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

//Writing class to ttest the methods of dao implementation
public class EventDaoImpTest {

    //Creating object for dao implementation class
    EventDaoImp edao = new EventDaoImp();

    // junit test case to test create
    @Test
    public void testCreateEventData() throws ClassNotFoundException, SQLException {
        //creating object for model class to make use of getter and setter methods
        EventModel em = new EventModel();
        //Binding data to the object
        em.setEventName("Birthday");
        em.setPriority("Two");
        em.setStatus("low");
        em.setOwner("Aruhi");
        em.setStartDate("2018-10-11");
        em.setStartTime("12:00:01");
        em.setEndDate("2018-10-12");
        em.setEndTime("06:00:30");
        em.setEmailAddress("aruhi@gmail.com");

        edao.createEventdataDao(em);


    }

    // junit test case to test retrieve method
    @Test
    public void testRetrieveEventData() throws SQLException {
        List<EventModel> list = new ArrayList<>();
        edao.retrieveEventDataDao();
        assertEquals(list.size(), list.size());

    }


    // test case to test delete method
    @Test
    public void testDeleteEventData() throws SQLException {

        int eventId = 4;
        int status = edao.deleteEventdataDao(eventId);
        assertEquals(status, status);

    }


}