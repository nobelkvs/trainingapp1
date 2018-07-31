package com.service;

import com.dao.InvestDAO;
import com.dao.InvestDAOint;
import com.model.Invest;
import java.util.List;

/**
 * Takes the input data from the servlet and passes to DAO layer and vice versa
 */
public class InvestService implements InvestServiceInt {
    //A reference to DAO interface is created, to call all the methods
    InvestDAOint dao=null;
    public int createInvest(Invest invest)
    {
        //An object is created to InvestDAO class
        dao=(InvestDAOint)new InvestDAO();
        //calls the insertion method of DAO layer
        int status=dao.createInvest(invest);
        //return the status to servlet
        return status;

    }
    public int deleteInvest(int uid){
        dao=(InvestDAOint)new InvestDAO();
        //calls the deletion method of DAO layer
        int status=dao.deleteInvest(uid);
        //return the status to servlet
        return status;

    }
    public List<Invest> retrieveByFirst(String fname){

        dao=(InvestDAOint)new InvestDAO();
        //calls the retrival method given by first name of DAO layer
        List<Invest> investList=dao.retrieveByFirst(fname);
        //return the list to servlet
        return investList;
    }
    public List<Invest> retrieveDetails(){
        dao=(InvestDAOint)new InvestDAO();
        //calls the retrival method to get entire data
        List<Invest> list_details=dao.retrieveDetails();
        return list_details;
    }

}