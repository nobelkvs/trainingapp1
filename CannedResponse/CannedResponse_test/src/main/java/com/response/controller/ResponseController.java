package com.response.controller;

import com.google.gson.Gson;
import com.response.model.*;
import com.response.service.*;
import org.apache.log4j.Logger;
import com.response.dao.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Servlet Class for Canned Response Applicaton</h1>
 *
 * @see com.response.service
 */
public class ResponseController extends HttpServlet {

    static final Logger log = Logger.getLogger(ResponseController.class);
    ResponseServiceInterface responseService = new ResponseService();
    ResponseDAO responseDAO = new ResponseDAO();

    /**
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     * @var creatstatus for returning the status of creation of response
     * doPost(,) method for Creating a Response.
     * @see com.response.service
     * @see com.response.model
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action1 = req.getParameter("action");
        // If action getLables to reteive Lables from DB.
        if (action1.equalsIgnoreCase("getLables")) {
            log.info("Action is GetLables in doPost Method to retrieve lables");
            PrintWriter out = res.getWriter();
            ResponseDAO responseDAO = new ResponseDAO();
            List<String> lables = new ArrayList<String>();
            try {
                lables = responseDAO.retrieveLables();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            log.info(lables);
            out.print(lables);

        } else {
            log.info("In doPost Method to Create a New Response");
            //Creating a Object of a Model.

            Response response = new Response();
             // req.getParameter - for Getting values from UI

            PrintWriter out = res.getWriter();
            String title = req.getParameter("utitle");
            String message = req.getParameter("umessage");
            String label = req.getParameter("ulabel");
            String mark_public = req.getParameter("umarkpublic");
            LocalDate updated_date = LocalDate.now();
            log.info("set values to Response Object");
            response.setTitle(title);
            response.setMessage(message);
            response.setLabel(label);
            response.setMark_public(mark_public);
            response.setUpdate_date(updated_date);

            int creatstatus = 0;

            try {
                creatstatus = responseService.createResponseService(response);
                if (creatstatus >= 1) {
                    log.info("Response is inserted from Controller");
                    out.print("Success");
                } else {
                    log.error("Response insertion failed from Controller");
                    out.print("Failed");
                }
            } catch (SQLException e) {
                log.error(e);
            }


        }
    }

    /**
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     * @var listResponse to holds the retrieved Data from Service
     * @see com.google.gson.Gson
     * doGet() method to retrieve List of Response Objects based on User or Admin
     */

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String label = req.getParameter("ulabel");
        String role = req.getParameter("Role");
        if (role.equalsIgnoreCase("user")) {
            log.info("From doGet() if the role is User");
            Gson gson = new Gson();
            List<Response> listResponse = null;
            try {
                listResponse = responseService.retrieveResponseServiceMarkPublic(label);
            } catch (SQLException e) {
                e.printStackTrace();
                log.info(e);
            }
            // gson.toJson() for Creating a JSON String Form a List Collection to Send Data to JavaScript On Ajax Call

            String gsonString = gson.toJson(listResponse);
            out.print(gsonString);
        } else {
            log.info(" From doGet() If the role is Admin ");
            Gson gson = new Gson();
            List<Response> listResponse = null;
            try {
                listResponse = responseService.retrieveResponseService(label);
            } catch (SQLException e) {
                e.printStackTrace();
                log.info(e);
            }

            //gson.toJson() for Creating a JSON String Form a List Collection to Send Data to JavaScript On Ajax Call
            String gsonString = gson.toJson(listResponse);
            out.print(gsonString);
        }
    }

    /**
     * @param   req
     * @param   res
     * @throws  IOException
     * @var     status for returning a the status of the Deletion of the Response Based on response ID
     */

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String Role = req.getParameter("Role");
        String delete_ID = "0";
        if (Role.equalsIgnoreCase("admin")) {
            delete_ID = (req.getParameter("delete_uId"));
        } else {
            delete_ID = req.getParameter("delete_uId");
        }
        String[] Delete_IDS = delete_ID.split(",");
        PrintWriter out = res.getWriter();
        int status = 0;
        try {
            status = responseService.deleteResponseService(Delete_IDS);
            if (status >= 1) {
                log.info("Deleted Successfully");
                out.print("Success");

            } else {
                log.error("Deletion failed");
                out.print("failed");
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }
}

