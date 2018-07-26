package com.event.service;

import com.event.model.ModelDeal;

import java.util.List;

/**
 * Interface to implement Service
 */
public interface ServiceDeal {

     String createDeal(ModelDeal deal);
     List retriveDeal(String name);
     int deleteDeal(int id);
     int updateDeal(ModelDeal deal);
}
