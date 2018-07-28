package com.banking.service;
import com.banking.model.Bankingmodel;
import java.sql.SQLException;
import java.util.List;



public interface Bankingservice {

    // Creating method for the banking service
    int createbankingservice(Bankingmodel obj) throws SQLException;
    // Deleting method for the banking service
    int deletebankingservice(Integer phoneno) throws SQLException;
    // Retrieving method for the banking service
    List<Bankingmodel> retrivebybranchname(String branch);

}