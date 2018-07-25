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
     */
    @Override
    //Implementing createEventDataService method of service class
    public int createEventDataService(EventModel userDetails) throws SQLException, ClassNotFoundException {
        log.info("Entered createEventDataService in service implementation class");
        //Creating object to send the user data to dao
        EventDao ed = new EventDaoImp();
        int status = ed.createEventdataDao(userDetails);
        return status;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    //Implementing retrieveEventDataService method of service class
    public List<EventModel> retrieveEventDataService() throws SQLException {
        log.info("Entered retrieveEventDataService in service implementation class");
        //Creating object to send the user data to dao
        EventDao ed = new EventDaoImp();
        List<EventModel> list = new ArrayList<>();
        //getting event deatails as list of objects by calling dao method
        list = ed.retrieveEventDataDao();
        return list;
    }

    /**
     *
     * @param eventId
     * @return
     * @throws SQLException
     */
    @Override
    //Implementing deleteEventData method of service class
    public int deleteEventDataService(int eventId) throws SQLException {
        log.info("Entered deleteEventDataService in service implementation class");
        //Creating object to send the user data to dao
        EventDao ed = new EventDaoImp();
        //passing id to the method of dao implementation
        int status = ed.deleteEventdataDao(eventId);
        return status;
    }
}
