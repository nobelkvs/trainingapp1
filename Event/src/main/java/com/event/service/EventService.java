package com.event.service;

import com.event.model.EventModel;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;
/*
 * interface class for service
 * Service layer useful to do any business logics
 * @see EventServiceImp class
 */

public interface EventService {
    // methods for create, retrieve and delete operations in service layer
    int createEventData(EventModel userDetails) throws SQLException, ClassNotFoundException;

    List<EventModel> retrieveEventData() throws SQLException;

    int deleteEventData(int eventId) throws SQLException;

}
