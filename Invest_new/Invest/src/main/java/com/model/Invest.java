package com.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Setter
@Getter
@ToString

public class Invest implements Serializable {
    private Integer uid;
    private String fname;
    private String lname;
    private Integer principal;
    private Integer arate;
    private Integer no_years;
    private Integer periods;

}
