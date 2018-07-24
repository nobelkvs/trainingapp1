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
    int createAttendanceDAO(Attendance attendance) throws SQLException;

    //Method declaration for retrieve
    List<Attendance> retrieveAttendanceDAOByDate(LocalDate date) throws SQLException;

    //Method declaration for delete
    int deleteAttendanceDAO(String[] multiple) throws SQLException;
}
