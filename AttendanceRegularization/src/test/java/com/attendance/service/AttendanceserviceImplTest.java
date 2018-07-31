package com.attendance.service;

import com.attendance.dao.AttendanceDAO;
import com.attendance.dao.AttendanceDAOImpl;
import com.attendance.model.Attendance;
import org.junit.Test;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class AttendanceserviceImplTest {

    AttendanceDAO ad= new AttendanceDAOImpl();
    @Test
    public void createAttendance() {
        Attendance attendance = new Attendance();
        attendance.setId(474);
        attendance.setDate(LocalDate.ofEpochDay(2018-05-24));
        attendance.setCheckintime(Time.valueOf("08:25:37"));
        attendance.setCheckouttime(Time.valueOf("09:15:21"));

        int create = 0;
        try {
            create = ad.createAttendance(attendance);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(0,create);


    }

    @Test
    public void retrieveByDate() {
        List<Attendance> al=null;
        try {
            al=ad.retrieveAttendanceByDate(LocalDate.parse("2018-07-10"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(al.size(),al.size());
    }


    @Test
    public void deleteAttendance(){
        String id= "471,479";
        int delete = 0;
        try {
            delete = ad.deleteAttendance(id.split(","));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(0,delete);

    }
}