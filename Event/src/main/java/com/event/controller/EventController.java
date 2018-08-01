package com.event.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.service.EventService;
import com.event.service.EventServiceImp;
import com.event.model.EventModel;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * This program used to control the request and response calls from UI to manage Events
 * In controller we make use of four different methods
 * doPost for Create operation
 * doGet for Retrieve operation
 * doPut for Update operation
 * doDelete for Delete operation
 */
//@WebServlet(description = "Get Event Details", urlPatterns = { "/EventController" })
//Event controller extends http servlet
public class EventController extends HttpServlet {
    int insertStatus = 0, deleteStatus = 0;

    //creating a log to check the flow of program
    static final Logger log = Logger.getLogger(EventController.class);

    /**
     * @param request
     * @param response
     * @throws IOException
     * @see EventService class
     */

    //writing method to Insert(POST) user details in a database
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        //assigning user data to variables
        String eventName = request.getParameter("eventName");
        String priority = request.getParameter("priority");
        String status = request.getParameter("status");
        String owner = request.getParameter("owner");
        String startDate = request.getParameter("startDate");
        String startTime = request.getParameter("startTime");
        String endDate = request.getParameter("endDate");
        String endTime = request.getParameter("endTime");
        String emailAddress = request.getParameter("emailAddress");

        //creating object for event model class
        EventModel em = new EventModel();
        //Binding data to the object of type model class
        em.setEventName(eventName);
        em.setPriority(priority);
        em.setStatus(status);
        em.setOwner(owner);
        em.setStartDate(startDate);
        em.setStartTime(startTime);
        em.setEndDate(endDate);
        em.setEndTime(endTime);
        em.setEmailAddress(emailAddress);
        log.info(em);
        //Creting object for service to send object em
        EventService eventService = new EventServiceImp();
        //writing try catch blocks to perform further actions
        try {
            //Sending data to the method createEventData in EventService class
            insertStatus = eventService.createEventData(em);
        }
        //get to know if there is any exception
        catch (Exception e) {
            e.printStackTrace();
            log.error("Got exception " + e);
        }
        //get to know whether data inserted successfully or not
        log.info((insertStatus >= 1) ? "Successfully Inserted the data" : "Failed to Insert the Data");
    }


    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see EventService class
     */
    //Retrieving method to get the data from event data table
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("In doGet method of Event controller");
        PrintWriter out = response.getWriter();
        //Creating object to call service
        EventService es = new EventServiceImp();
        //Creating GSOn object
        Gson gson = new Gson();

        try {
            //creating a list for storing the retrieved records
            List<EventModel> listOfEvents = new ArrayList<>();
            //calling method retrieveEventData of service class
            listOfEvents = es.retrieveEventData();
            //Converting listOfEvents to JSON object
            String json2 = gson.toJson(listOfEvents);
            log.info(json2);
            //Writing json data to the UI
            out.println(json2);
        } catch (SQLException e) {
            log.error("SQL Exception " + e);
        }




     /*  PrintWriter out=response.getWriter();
        try {
            es=new EventServiceImp();
            //calling method retrieveEventDataService of service class
            listOfEvents = es.retrieveEventDataService();
             int i=0;
             log.info(listOfEvents);
             //Iterating List objects of event details
             while(i<listOfEvents.size())
             {
                 EventModel tm=listOfEvents.get(i);
                 out.println("EventId="+tm.getEventId());
                 out.println("EventName="+tm.getEventName());
                 out.println("Priority="+tm.getPriority());
                 out.println("status="+tm.getStatus());
                 out.println("owner="+tm.getOwner());
                 out.println("StartDate="+tm.getStartDate());
                 out.println("StartTime="+tm.getStartTime());
                 out.println("EndDate="+tm.getEndDate());
                 out.println("EndTime="+tm.getEndTime());
                 out.println("EmailAddress="+tm.getEmailAddress());
                 i++;
             }

        }
        catch (SQLException e)
        {
            log.error("SQL Exception "+e);
        }*/
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @see EventService class
     */
    //Deleting method to remove the data from event data table
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //accessing eventId from user and assigning to a variable
        String userRole = request.getParameter("userRole");
        int eventId = Integer.parseInt(request.getParameter("eventId"));
        EventModel eventModel = new EventModel();
        //Comparing user Role
        if (userRole.equalsIgnoreCase("admin")) {
            //creating object for service classes
            EventService eventService = new EventServiceImp();
            try {
                //sending event id to the deleteEventData method of serivce classes
                deleteStatus = eventService.deleteEventData(eventId);
                log.info((deleteStatus >= 1) ? "Successfully removed event details" : "sorry!! failed to remove the event details, try again");
                out.print(deleteStatus);
            } catch (SQLException e) {
                e.printStackTrace();
                log.error(e);
            }
        } else {
            deleteStatus = 0;
            log.info(deleteStatus);
            out.print(deleteStatus);
        }

    }
}
