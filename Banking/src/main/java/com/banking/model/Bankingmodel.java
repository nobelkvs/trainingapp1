package com.banking.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter  @ToString
public class Bankingmodel
{
    // Creating the required attributes for Bank Application
    private String customername;
    private String branch;
    private String address;
    private Integer phoneno;
    private String emailaddr;


}