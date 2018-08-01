package com.lms.Controller;

import com.google.gson.Gson;
import com.lms.Model.LmsModel;
import com.lms.Service.LmsServiceImpl;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to send request and get response from the database
 */
public class ControllerServlet extends HttpServlet {


    /* Loggers allow to report info messages and warning messages,error messages */
    static final Logger log = Logger.getLogger(ControllerServlet.class);

    /**
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     * This dopost method is used to create the leave for the employee
     */


    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter out = res.getWriter();
        log.info("doPost method inside servletcontroller");

        /* getting user data from UI */
        Integer empId = Integer.valueOf(req.getParameter("empId"));
        String empName=req.getParameter("empName");
        String fromDate = req.getParameter("fromDate");
        String toDate = req.getParameter("toDate");
        String leaveType = req.getParameter("leaveType");
        String comments = req.getParameter("comments");
       // Integer noOfDays = Integer.valueOf(req.getParameter("noOfDays"));
        log.info("controler"+empId+fromDate);

        /* Creating the object for model class */

        LmsModel lms = new LmsModel();

        /* setting the values to the model object */
        lms.setEmpId(empId);
        lms.setEmpName(empName);
        lms.setFromDate(fromDate);
        lms.setToDate(toDate);
        lms.setLeaveType(leaveType);
        lms.setComments(comments);
       // lms.setNo_Of_Days(noOfDays);

        /* Creating the object for the LmsServiceImplementation class */
        LmsServiceImpl lsi = new LmsServiceImpl();
        int status = lsi.createLms(lms);

        /*Service Class(i.e LmsServiceImpl) method  returns the result of creation by passing model Object
         *If the status is '1' then it is successfully inserted
         */
        log.info(status == 1 ? "Success" : "try again");
        out.close();
    }



    /**
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     *  This doGet method is used to retrieve the employee leaves
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //Gson object generates the JSON data from the Java objects
        Gson gson = new Gson();

        String empName =req.getParameter("empName");
        log.info(empName);
        //Creating the array to the LmsModel list
        List<LmsModel> ls = new ArrayList<>();
        PrintWriter out = res.getWriter();
        LmsServiceImpl lm = new LmsServiceImpl();
        try {
            ls = lm.retrieveByEmpName(empName);
            log.info(ls);
            String json = gson.toJson(ls);
            out.println(json);
            log.info("Printing Json retrived data"+json);
        } catch (Exception e) {
            log.error("ERROR In Controller doGet " + e);
        }

    }


}