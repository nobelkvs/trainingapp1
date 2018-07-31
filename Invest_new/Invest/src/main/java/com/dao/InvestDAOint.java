package com.dao;

import com.model.Invest;

import java.util.List;

public interface InvestDAOint {
    public int createInvest(Invest invest);
    public int deleteInvest(int Uid);
    public List<Invest> retrieveByFirst(String fname);
    public List<Invest> retrieveDetails();
}