package com.event.dao;

import com.event.model.EventModel;

import java.sql.SQLException;
import java.util.List;

/*
 * Writing interface class for dao layer
 * writing methods to perform different operations such as creating,retrieving,deleting
 * @see EventDaoImp class
 */
public interface EventDao {

    int createEventData(EventModel userDetails) throws ClassNotFoundException, SQLException;

    List<EventModel> retrieveEventData() throws SQLException;

    int deleteEventdata(int eventId) throws SQLException;
}
