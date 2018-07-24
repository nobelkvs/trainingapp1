package com.attendance.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;

/**
 * implement Attendance model
 */
@Setter
@Getter
@ToString
public class Attendance implements Serializable {

    private Integer id;
    private LocalDate date;
    private Time checkintime;
    private Time checkouttime;
}
