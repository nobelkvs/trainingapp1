package com.bank.dao;
import com.bank.model.BankModel;
import java.sql.SQLException;
import java.util.List;
// It is used to access data from database
public interface BankDao {
    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    // In this we create data using model object
    int createbankDAO(BankModel obj) throws SQLException;

    /**
     *
     * @param phno
     * @return
     * @throws SQLException
     */
    // In this we delete data using phone number
    int deletebankDAO(Integer phno) throws SQLException;

    /**
     *
     * @param name
     * @return
     */
    // In this we retrieve data using name
    List<BankModel> retrivebankDAO(String name);

}
