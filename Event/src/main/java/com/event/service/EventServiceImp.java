package com.event.service;

import com.event.dao.EventDao;
import com.event.dao.EventDaoImp;
import com.event.model.EventModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 *here we are implementing service class and writing method bodies
 */

public class EventServiceImp implements EventService {

    @Override
    //Implementing createEventDataService method of service class
    public int createEventDataService(EventModel userDetails) throws SQLException, ClassNotFoundException {
        //Creating object to send the user data to dao
        EventDao ed = new EventDaoImp();
        int status = ed.createEventdataDao(userDetails);
        return status;
    }

    @Override
    //Implementing retrieveEventDataService method of service class
    public List<EventModel> retrieveEventDataService() throws SQLException {
        //Creating object to send the user data to dao
        EventDao ed = new EventDaoImp();
        List<EventModel> list = new ArrayList<>();
        //getting event deatails as list of objects by calling dao method
        list = ed.retrieveEventDataDao();
        return list;
    }

    @Override
    //Implementing deleteEventData method of service class
    public int deleteEventDataService(int eventId) throws SQLException {
        //Creating object to send the user data to dao
        EventDao ed = new EventDaoImp();
        //passing id to the method of dao implementation
        int status = ed.deleteEventdataDao(eventId);
        return status;
    }
}
