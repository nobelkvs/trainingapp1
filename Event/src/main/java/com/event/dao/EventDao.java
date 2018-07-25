package com.event.dao;

import com.event.model.EventModel;

import java.sql.SQLException;
import java.util.List;

/*
 * Writing interface class for dao layer
 * writing methods to perform different operations such as creating,retrieving,deleting
 */
public interface EventDao {

    int createEventdataDao(EventModel userDetails) throws ClassNotFoundException, SQLException;

    List<EventModel> retrieveEventDataDao() throws SQLException;

    int deleteEventdataDao(int eventId) throws SQLException;
}
