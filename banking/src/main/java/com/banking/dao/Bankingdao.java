package com.banking.dao;

import com.banking.model.Bankingmodel;
import com.banking.model.Bankingmodel;
import java.sql.SQLException;
import java.util.List;
public interface Bankingdao
{
    // In this we create the data using model object connected to service implementation
    int createbankingDAO(Bankingmodel obj) throws SQLException;
    // In this we delete the data using phone number connected to service implementation
    int deletebankingDAO(Integer phoneno) throws SQLException;
    // In this we retrieve the data using branch name connected at the service implementation
    List<Bankingmodel> retrivebankingDAO(String branch);

}
