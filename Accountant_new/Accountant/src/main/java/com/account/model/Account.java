package com.account.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// Adding LOMBOK dependencies and by using that we are declairing getter and setter method 
@Setter
@Getter
@ToString
public class Account {
    private Integer Id;
    private String Fname;
    private String Lname;
    private String Bankname;
    private String Branch;
    private String Address;
    private Integer Phone;


}
