package com.bank.service;
import com.bank.model.bankmodel;
import java.sql.SQLException;
import java.util.List;

public interface bankservice {
    // Create method for service
    int createbankservice(bankmodel obj) throws SQLException;
    // Delete method for service
    int deletebankservice(Integer phno) throws SQLException;
    // Retrieve method for service
    List<bankmodel> retrivebybname(String branch);

}
