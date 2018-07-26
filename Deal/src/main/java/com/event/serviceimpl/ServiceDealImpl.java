package com.event.serviceimpl;

import com.event.dao.DAODeal;
import com.event.daoimpl.DAODealImpl;
import com.event.model.ModelDeal;
import com.event.service.ServiceDeal;

import org.apache.log4j.Logger;

import java.util.List;

public class ServiceDealImpl implements ServiceDeal {
    /* Logger instance */
    static final Logger log=Logger.getLogger(ServiceDealImpl.class);

    /* DAODeal instance */
    DAODeal daoDeal=new DAODealImpl();


    /**
     * This method is used to Create Deal
     * @param deal
     * @return status
     */
    @Override
    public String createDeal(ModelDeal deal) {
        log.info("entered into create service");
        String status=daoDeal.createDAO(deal);
        return status;
    }


    /**
     * This method is used to Retrive Deal
     * @param name
     * @return List
     */
    @Override
    public List retriveDeal(String name) {
        log.info("entered into retrive service");
        List list=daoDeal.retriveDAO(name);
        return list;
    }


    /**
     * This method is used to Delete Deal
     * @param id
     * @return Status
     */
    @Override
    public int deleteDeal(int id) {
        log.info("entered into delete service");
        int status=daoDeal.deleteDAO(id);
        return status;
    }


    /**
     * This method is used to Update Deal
     * @param deal
     * @return Status
     */
    @Override
    public int updateDeal(ModelDeal deal) {
        log.info("entered into update service");
        int status=daoDeal.updateDAO(deal);
        log.info(status+"from service");
        return status;
    }
}
