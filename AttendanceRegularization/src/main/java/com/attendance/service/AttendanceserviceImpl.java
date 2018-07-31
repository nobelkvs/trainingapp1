package com.attendance.service;

import com.attendance.controller.AttendanceControllerServlet;
import com.attendance.dao.AttendanceDAO;
import com.attendance.dao.AttendanceDAOImpl;
import com.attendance.model.Attendance;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Create service class for implementing service interface
 */
public class AttendanceserviceImpl implements AttendanceService {

    //Create instance of logger in AttendanceControllerServlet class
    static final Logger log = Logger.getLogger(AttendanceserviceImpl.class);

    /**
     * Declare create service method to call create dao method
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    public int createAttendance(Attendance obj) throws SQLException {

        //Create AttendanceDAO object

        AttendanceDAO ad = new AttendanceDAOImpl();
        int status = ad.createAttendance(obj);
        log.info("status"+status);
        return status;
    }

    /**
     * Declare retrieve service method to call dao retrieve method
     *
     * @param date
     * @return
     * @throws SQLException
     */
    @Override
    public List<Attendance> retrieveByDate(LocalDate date) throws SQLException {

        //Create AttendanceDAO object
        AttendanceDAO ad = new AttendanceDAOImpl();

        List<Attendance> Status = ad.retrieveAttendanceByDate(date);


        log.info(Status);

        return Status;
    }

    /**
     * Declare delete service method to call dao delete method
     *
     * @param multiple
     * @return
     */
    public int deleteAttendance(String[] multiple) {

        //Create AttendanceDAO object
        AttendanceDAO ad = new AttendanceDAOImpl();
        int Status = 0;
        try {
            Status = ad.deleteAttendance(multiple);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info(Status);

        return Status;
    }
}
