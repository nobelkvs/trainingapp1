package com.attendance.service;

import com.attendance.model.Attendance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Create interface AttendanceService
 */
public interface AttendanceService {

    //Declare service method for creating
    int createAttendanceService(Attendance obj) throws SQLException;

    //Declare service method for retrieving
    List<Attendance> retrieveByDate(LocalDate date) throws SQLException;

    //Declare service method for deletion
    int deleteAttendanceService(String[] multiple);
}
