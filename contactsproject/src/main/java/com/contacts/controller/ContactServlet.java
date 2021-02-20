package com.contacts.controller;

import com.contacts.entity.Contacts;
import com.contacts.service.ContactService;
import com.contacts.service.ContactServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Rajnish.Kumar
 */


@WebServlet("/contact")
//@ServletSecurity(
//        value = @HttpConstraint(
//                rolesAllowed = {
//                        "secured"
//                }),
//        httpMethodConstraints = {
//                @HttpMethodConstraint(value = "GET", rolesAllowed = {
//                        "secured"
//                })
//        })

public class ContactServlet extends HttpServlet {


    private final static Logger logger = Logger.getLogger(ContactServlet.class);

    /**
     * Processes requests for both HTTP GET
     * methods.
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        Contacts contacts = new Contacts();
        ContactService contactServiceImpl = new ContactServiceImpl();
        int status;
        String outputResponse;

        /*
         If contact phoneNumber is not null
         insert or update contact data to database
         else delete contact data from database
        */

        if( request.getParameter("phoneNumber") != null){

            // Copy all request data in contact object
            if(request.getParameter("contactId") != null){
                logger.info("This post request is for update data");
                contacts.setContactId(Integer.parseInt(request.getParameter("contactId")));
            } else {
                logger.info("This post request is to add new contact data");
            }

            contacts.setFirstName(request.getParameter("firstName"));
            contacts.setLastName(request.getParameter("lastName"));
            contacts.setTitle(request.getParameter("title"));
            contacts.setCompany(request.getParameter("company"));
            contacts.setEmail(request.getParameter("email"));
            contacts.setPhoneNumber(Long.parseLong(request.getParameter("phoneNumber")));
            contacts.setTags(request.getParameter("tags"));

            try {
                status = contactServiceImpl.createContactService(contacts);

                // If contact is deleted status is 1 else it is 0
                if(status==0 & request.getParameter("contactId") == null){
                    outputResponse = "new contact not saved try again";
                } else if(status==0 & request.getParameter("contactId") != null){
                    outputResponse = "contact not updated try again";
                } else if(request.getParameter("contactId") != null){
                    outputResponse = "contact updated successfully";
                } else {
                    outputResponse = "new contact added successfully";
                }
            } catch (ClassNotFoundException | SQLException e) {
                logger.info("Exception occur while inserting or updating contact data into database");
                outputResponse = "try again reason: Exception";
                e.printStackTrace();
            }

        } else {
            logger.info("This request is for delete contact data from database");
            /*
             If multiple contacts is selected to delete
             get all ids from request and parse in integer value and
             add in to contactList
            */

            Enumeration enumeration = request.getParameterNames();
            List<Integer> contactList = new ArrayList<>();
            while (enumeration.hasMoreElements()) {
                String parameterName = (String) enumeration.nextElement();
                logger.debug("Parameter = " + parameterName);

                String [] ids  = request.getParameterValues(parameterName);
                for (String id : ids) {
                    contactList.add(Integer.parseInt(id));
                }
            }

            // Copy all request ids into integer array
            int [] contactId = contactList.stream().filter(i -> i != null).mapToInt(i -> i).toArray();
            logger.debug("All ids value to delete = " + Arrays.toString(contactId));

            // Set response based on status value
            try {
                status = contactServiceImpl.deleteContactService(contactId);
                if(status==0){
                    outputResponse = "contact not deleted try again";
                } else {
                    outputResponse = "contact deleted successfully from database";
                }
            } catch (SQLException | ClassNotFoundException e) {
                logger.info("Exception occur while deleting contact data from database");
                outputResponse = "contact not deleted try again, Reason: Exception";
                e.printStackTrace();
            }

        }

        out.print(outputResponse);

        out.close();

    }

    /**
     * Processes requests for both HTTP GET
     * methods.
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ContactService contactServiceImpl = new ContactServiceImpl();
        List<Contacts> contactsList;
        String outputResponse;
        // Get contactsList from database and set into http response
        try {
            contactsList = contactServiceImpl.getContactService();
            if(contactsList != null){
                Gson gson = new Gson();
                JsonElement element = gson.toJsonTree(contactsList, new TypeToken<List<Contacts>>() {}.getType());
                JsonArray jsonArray = element.getAsJsonArray();
                out.print(jsonArray);
            } else {
                outputResponse = "data is not available in database";
                out.print(outputResponse);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.info("Exception occur while retrieving contact data from database :" +e);
            outputResponse = "try again Reason: Exception";
            out.print(outputResponse);
            e.printStackTrace();
        }

    }
}
