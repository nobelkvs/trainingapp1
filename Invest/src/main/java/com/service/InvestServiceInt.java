package com.service;

import com.model.Invest;
import java.util.List;

/**
 * provides the empty implementation methods to the Service layer
 */
public interface InvestServiceInt {
    public int createInvestService(Invest invest);
    public int deleteInvestService(int User_id);
    public List<Invest> retrieveByFirstService(String First_name);
}
