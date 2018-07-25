package com.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Setter
@Getter
@ToString

public class Invest implements Serializable {
    private Integer User_id;
    private String First_Name;
    private String Last_Name;
    private Integer Principal;
    private Integer Annual_rate;
    private Integer No_years;
    private Integer Periods;

}
