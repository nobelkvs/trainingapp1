package com.event.dao;

import com.event.model.ModelDeal;

import java.util.List;

/**
 * Interface to implement DAO operations
 */
public interface DealDAO {

     String create(ModelDeal deal);
    List<ModelDeal> retrive(String name);
    int delete(int id);
    int update(ModelDeal deal);
}
