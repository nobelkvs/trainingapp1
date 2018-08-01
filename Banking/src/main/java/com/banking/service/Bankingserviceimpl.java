package com.banking.service;
import com.banking.dao.Bankingdao;
import com.banking.dao.Bankingdaoimpl;
import com.banking.model.Bankingmodel;
import java.sql.SQLException;
import java.util.List;
public class Bankingserviceimpl implements Bankingservice {

    // Initializing the  dao object bdao to null
  Bankingdao bdao = null;
    // Method creation for createbankService and pass model object

    public int createbanking(Bankingmodel obj) throws SQLException {
        bdao = new Bankingdaoimpl();
        int createstatus = bdao.createbanking(obj);
        return createstatus;

    }

    // Method creation for deletebankService and passing the phone number
    public int deletebanking(Integer phoneno) throws SQLException {
        bdao=new Bankingdaoimpl();
        int deletestatus=bdao.deletebanking(phoneno);
        return deletestatus;
    }
    // Method creation for retrievebankService and passing the bank branch name
    public List<Bankingmodel> retrivebybranchname(String branch) {
        bdao=new Bankingdaoimpl();
        List<Bankingmodel> retreivestatus=bdao.retrivebanking(branch);
        return retreivestatus;
    }
}

