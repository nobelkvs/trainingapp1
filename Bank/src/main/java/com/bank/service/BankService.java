package com.bank.service;
import com.bank.model.BankModel;
import java.sql.SQLException;
import java.util.List;

public interface BankService {
    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    // Create method for service
    int createbankservice(BankModel obj) throws SQLException;

    /**
     *
     * @param phno
     * @return
     * @throws SQLException
     */
    // Delete method for service
    int deletebankservice(Integer phno) throws SQLException;

    /**
     *
     * @param branch
     * @return
     */
    // Retrieve method for service
    List<BankModel> retrivebybname(String branch);

}
