package com.dao;

import com.model.Invest;
import java.util.List;

/**
 * Provides empty implementation methods to the DAO layer
 */
public interface InvestDAOint {
    public int createInvestDAO(Invest invest);
    public int deleteInvestDAO(int User_id);
    public List<Invest> retrieveByFirstDAO(String First_name);

}
