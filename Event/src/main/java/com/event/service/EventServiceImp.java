package com.event.service;

import com.event.dao.EventDao;
import com.event.dao.EventDaoImp;
import com.event.model.EventModel;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 *here we are implementing service class and writing method bodies
 */

public class EventServiceImp implements EventService {
    static final Logger log = Logger.getLogger(EventServiceImp.class);


    /**
     *
     * @param userDetails
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @see EventDao class
     */
    @Override
    //Implementing createEventData method of service class
    public int createEventData(EventModel userDetails) throws SQLException, ClassNotFoundException {
        log.info("Entered createEventDataService in service implementation class");
        log.info(userDetails);
        //Creating object to send the user data to dao
        EventDao ed = new EventDaoImp();
        int status = ed.createEventData(userDetails);
        return status;
    }

    /**
     *
     * @return
     * @throws SQLException
     * @see EventDao class
     */
    @Override
    //Implementing retrieveEventData method of service class
    public List<EventModel> retrieveEventData() throws SQLException {
        log.info("Entered retrieveEventDataService in service implementation class");
        //Creating object to send the user data to dao
        EventDao ed = new EventDaoImp();
        List<EventModel> list = new ArrayList<>();
        //getting event deatails as list of objects by calling dao method
        list = ed.retrieveEventData();
        return list;
    }

    @Override
    public int deleteEventData(int eventId) throws SQLException {
        log.info("Entered deleteEventData in service implementation class");
        //Creating object to send the user data to dao
        EventDao ed = new EventDaoImp();
        //passing id to the method of dao implementation
        int status = ed.deleteEventdata(eventId);
        return status;
    }


}
