package com.event.dao;

import com.event.DatabaseConnection.MysqlConnection;

import com.event.model.EventModel;
import com.event.service.EventService;
import com.event.service.EventServiceImp;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/*
 *EventDaoImplementation class to perform actions related to database for CRUD operations
 * It is mainly associated with database
 */

public class EventDaoImp implements EventDao {

    //creating log to check program flow
    static final Logger log = Logger.getLogger(EventDaoImp.class);
    //SQL Query to insert the event data into the database
    private static final String Sql_Insert_Into_EventDataTable = "INSERT INTO eventdata(event_name,priority,status,owner,start_date,start_time,end_date,end_time,email_address) VALUES(?,?,?,?,?,?,?,?,?)";
    //SQL Query to retrieve the event data from the database
    private static final String Sql_Retrieve_From_EventDataTable = "SELECT * FROM eventdata";
    //SQL Query to delete the event data from the database
    private static final String Sql_Delete_From_EventDataTable = "DELETE FROM eventdata WHERE event_id=?";
    //Connecting to the database
    MysqlConnection connection = new MysqlConnection();

    //implementing method createEventdataDao to insert the data in a database
    @Override
    public int createEventdataDao(EventModel userDetails) throws ClassNotFoundException, SQLException {

        log.info("In createEventdataDao method Implementation");

        Connection conn = null;
        int insertStatus = 0;
        try {
            conn = connection.getConnect();
            log.info("Connection id " + conn);
        } catch (Exception e) {
            log.error("Failed to connect the server in createEvendatatDao method");

        }

        //prepare statement
        PreparedStatement ps = null;


        ps = conn.prepareStatement(Sql_Insert_Into_EventDataTable);
        ps.setString(1, userDetails.getEventName());
        ps.setString(2, userDetails.getPriority());
        ps.setString(3, userDetails.getStatus());
        ps.setString(4, userDetails.getOwner());
        ps.setDate(5, Date.valueOf(userDetails.getStartDate()));
        ps.setTime(6, Time.valueOf(userDetails.getStartTime()));
        ps.setDate(7, Date.valueOf(userDetails.getEndDate()));
        ps.setTime(8, Time.valueOf(userDetails.getEndTime()));
        ps.setString(9, userDetails.getEmailAddress());
        insertStatus = ps.executeUpdate();

        return insertStatus;
    }

    //implementing method retrieveEventDataDao to get the data from database
    @Override
    public List<EventModel> retrieveEventDataDao() throws SQLException {
        log.info("In createEventdataDao method Implementation");

        Connection conn = null;
        ResultSet rs = null;

        try {
            conn = connection.getConnect();
        } catch (Exception e) {
            log.error("can not connect to the server in  method retrieveEventdataDao");
        }
        log.info("Connection id " + conn);

        List<EventModel> listOfEvents = new ArrayList<>();
        //prepare statemennt
        PreparedStatement ps = null;
        ps = conn.prepareStatement(Sql_Retrieve_From_EventDataTable);
        rs = ps.executeQuery();
        while (rs.next()) {
            EventModel em = new EventModel();
            em.setEventId(rs.getInt(1));
            em.setEventName(rs.getString(2));
            em.setPriority(rs.getString(3));
            em.setStatus(rs.getString(4));
            em.setOwner(rs.getString(5));
            em.setStartDate(rs.getString(6));
            em.setStartTime(rs.getString(7));
            em.setEndDate(rs.getString(8));
            em.setEndTime(rs.getString(9));
            em.setEmailAddress(rs.getString(10));

            listOfEvents.add(em);
        }
        return listOfEvents;
    }


    //implementing method deleteEventdataDao to get the delete data from database
    @Override
    public int deleteEventdataDao(int eventId) throws SQLException {
        log.info("In createEventdataDao method Implementation");

        Connection conn = null;
        ResultSet rs = null;
        int deleteStatus = 0;

        try {
            conn = connection.getConnect();
            log.info("Connection id " + conn);
        } catch (Exception e) {
            log.error("can not connect to the server in  method deleteEventdataDao");
        }
        //prepare statemennt
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(Sql_Delete_From_EventDataTable);
            ps.setInt(1, eventId);
            deleteStatus = ps.executeUpdate();
            log.info(deleteStatus);

        } catch (Exception e1) {
            e1.printStackTrace();
            log.error("exception in deleteEventdatadao method " + e1);
        }
        return deleteStatus;
    }


}
