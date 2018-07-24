package com.bank.service;
import com.bank.dao.bankdao;
import com.bank.dao.bankdaoimpl;
import com.bank.model.bankmodel;
import java.sql.SQLException;
import java.util.List;
public class bankserviceimpl implements bankservice {
    // Initialize dao object bd to null
    bankdao bd = null;
    // Method creation for createbankService and pass model object
    @Override
    public int createbankservice(bankmodel obj) throws SQLException {
        bd = new bankdaoimpl();
        int status = bd.createbankDAO(obj);
        return status;

    }
    // Method creation for deletebankService and pass phone number through which we delete data
    public int deletebankservice(Integer phno) throws SQLException {
        bd=new bankdaoimpl();
        int status=bd.deletebankDAO(phno);
        return status;
    }
    // Method creation for retrievebankService and pass bank name through which we retrieve data
    public List<bankmodel> retrivebybname(String branch) {
        bd=new bankdaoimpl();
        List<bankmodel> status=bd.retrivebankDAO(branch);
        return status;
        }
}
