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

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", date=" + date +
                ", checkintime=" + checkintime +
                ", checkouttime=" + checkouttime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(Time checkintime) {
        this.checkintime = checkintime;
    }

    public Time getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(Time checkouttime) {
        this.checkouttime = checkouttime;
    }




}
