package com.event.serviceimpl;

import com.event.dao.DAODeal;
import com.event.daoimpl.DAODealImpl;
import com.event.model.ModelDeal;
import com.event.service.ServiceDeal;

import org.apache.log4j.Logger;

import java.util.List;

public class ServiceDealImpl implements ServiceDeal {
    //logger instance
    static final Logger log=Logger.getLogger(ServiceDealImpl.class);
    //DAO instance
    DAODeal daoDeal=new DAODealImpl();


    //create deal service method
    @Override
    public String createDeal(ModelDeal deal) {
        log.info("entered into create service");
        String mesg=daoDeal.createDAO(deal);
        return mesg;
    }


    //retrive deal service method
    @Override
    public List retriveDeal(String name) {
        log.info("entered into retrive service");
        List list=daoDeal.retriveDAO(name);
        return list;
    }


    //delete deal service method
    @Override
    public int deleteDeal(int id) {
        log.info("entered into delete service");
        int mesg=daoDeal.deleteDAO(id);
        return mesg;
    }


    //update deal service method
    @Override
    public int updateDeal(ModelDeal deal) {
        log.info("entered into update service");
        int mesg=daoDeal.updateDAO(deal);
        log.info(mesg+"from service");
        return mesg;
    }
}
