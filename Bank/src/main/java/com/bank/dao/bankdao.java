package com.bank.dao;
import com.bank.model.bankmodel;
import java.sql.SQLException;
import java.util.List;
// It is used to access data from database
public interface bankdao {
    // In this we create data using model object
    int createbankDAO(bankmodel obj) throws SQLException;
    // In this we delete data using phone number
    int deletebankDAO(Integer phno) throws SQLException;
    // In this we retrieve data using name
    List<bankmodel> retrivebankDAO(String name);

}
