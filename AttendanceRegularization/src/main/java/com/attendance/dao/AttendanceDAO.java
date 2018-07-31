package com.attendance.dao;

import com.attendance.model.Attendance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Create AttendanceDAO interface
 */
public interface AttendanceDAO {

    //Method declaration for create
    int createAttendance(Attendance attendance) throws SQLException;

    //Method declaration for retrieve
    List<Attendance> retrieveAttendanceByDate(LocalDate date) throws SQLException;

    //Method declaration for delete
    int deleteAttendance(String[] multiple) throws SQLException;
}
