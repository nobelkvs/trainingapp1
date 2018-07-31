package com.service;

import com.model.Invest;

import java.util.List;

public interface InvestServiceInt {
    public int createInvest(Invest invest);

    public int deleteInvest(int User_id);

    public List<Invest> retrieveByFirst(String First_name);

    public List<Invest> retrieveDetails();
}