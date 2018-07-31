package com.attendance.controller;

import com.attendance.model.Attendance;
import com.attendance.service.AttendanceService;
import com.attendance.service.AttendanceserviceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller class
 */
public class AttendanceControllerServlet extends HttpServlet {

    //Create instance of logger in AttendanceControllerServlet class
    static final Logger log = Logger.getLogger(AttendanceControllerServlet.class);

    int insertStatus = 0;

    /**
     * This method is for  get the values from UI
     *
     * @param req
     * @param res
     */

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {


        //Get the values from UI
        int id = Integer.parseInt(req.getParameter("id"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        Time checkintime = Time.valueOf(req.getParameter("checkintime"));
        Time checkouttime = Time.valueOf(req.getParameter("checkouttime"));

        //Create object for model
        Attendance attendance = new Attendance();

        //Set the values into model
        attendance.setId(id);
        attendance.setDate(date);
        attendance.setCheckintime(checkintime);
        attendance.setCheckouttime(checkouttime);

        //Create object for AttendanceserviceImpl class
        AttendanceService attendanceService = new AttendanceserviceImpl();

        try {

            //Call createAttendanceService by passing model object
            insertStatus = attendanceService.createAttendance(attendance);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        PrintWriter out= res.getWriter();


        if (insertStatus >= 1){
            log.info("successfully inserted");
        out.print(1);
    }
        else{

            log.warn("failed to insert");
            out.print(0);
        }


        log.info(insertStatus);


    }

    /**
     * This method is for display the values in the UI
     *
     * @param req
     * @param res
     */

    protected void doGet(HttpServletRequest req, HttpServletResponse res) {

        LocalDate date = LocalDate.parse(req.getParameter("rdate"));
       // Date date=Date.parse(req.getParameter("rdate"));
        List<Attendance> list_byDate = new ArrayList<Attendance>();

        //PrintWrite enables you to write formatted data into text format
        PrintWriter out = null;
        try {
            out = res.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Create object for gson
        Gson gson = new Gson();

        //Create object for AttendanceserviceImpl class
        AttendanceService attendanceService = new AttendanceserviceImpl();

        try {
            //Call retrieveByDate by passing model parameter
            list_byDate = attendanceService.retrieveByDate(date);

            //Convert data gson to json string
            String json = gson.toJson(list_byDate);
            log.info(json);
            out.print(json);

        } catch (SQLException e) {
            e.printStackTrace();
            log.warn(e);
        }

    }

    /**
     * This method is for delete the data from database
     *
     * @param req
     * @param res
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {

        int deleteStatus = 0;


        PrintWriter out = res.getWriter();

        String id = req.getParameter("id");
        String multiple[] = id.split(",");

        //Create object for AttendanceserviceImpl class
        AttendanceService attendanceService = new AttendanceserviceImpl();

        //Call deleteAttendanceService by passing model parameter
        deleteStatus = attendanceService.deleteAttendance(multiple);

        if (deleteStatus >= 1) {
            log.info("successfully deleted");
            out.println(1);
        } else
            log.warn("failed to delete ");

    }

}
