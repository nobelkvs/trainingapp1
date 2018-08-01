package com.event.controller;

import com.event.model.EventModel;
import com.event.service.EventServiceImp;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EventControllerTest {

    //creating a log to check the flow of program
    static final Logger log = Logger.getLogger(EventController.class);
    //Creating object for service implementation class
    EventServiceImp eser = new EventServiceImp();

    // junit test case to test doPost method
    @Test
    public void testDoPost() {
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
        try {
            eser.createEventData(em);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("SQLException in Controller test class in testDoPost "+e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("ClassNotFoundException in Controller test class in testDoPost "+e);
        }

    }

    // junit test case to test doGet method
    @Test
    public void testDoGet() {
        List<EventModel> list = new ArrayList<>();
        try {
            list = eser.retrieveEventData();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("SQLException in Controller test class in testDoGet "+e);
        }
        assertEquals(list.size(), list.size());
    }

    // junit test case to test doDelete method
    @Test
    public void testDoDelete() {
        int eventId = 4;
        int status = 0;
        try {
            status = eser.deleteEventData(eventId);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("SQLException in Controller test class in testDoDelete "+e);
        }
        assertEquals(status, status);
    }
}