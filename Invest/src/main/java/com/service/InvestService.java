package com.service;

import com.dao.InvestDAO;
import com.dao.InvestDAOint;
import com.model.Invest;
import java.util.List;

/**
 * transfer the information from Servlet to DAO and vice versa
 */
public class InvestService implements InvestServiceInt {
    InvestDAOint dao=null;
    public int createInvestService(Invest invest)
    {
        dao=(InvestDAOint)new InvestDAO();
        int status=dao.createInvestDAO(invest);
        return status;

    }
    public int deleteInvestService(int User_id){
        dao=(InvestDAOint)new InvestDAO();
        int status=dao.deleteInvestDAO(User_id);
        return status;

    }
    public List<Invest> retrieveByFirstService(String First_name){
        dao=(InvestDAOint)new InvestDAO();
        List<Invest> investList=dao.retrieveByFirstDAO(First_name);
        return investList;
    }

}
