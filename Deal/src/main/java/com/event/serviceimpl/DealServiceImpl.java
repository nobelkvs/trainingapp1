package com.event.serviceimpl;

import com.event.dao.DealDAO;
import com.event.daoimpl.DealDAOImpl;
import com.event.model.ModelDeal;
import com.event.service.DealService;
import org.apache.log4j.Logger;

import java.util.List;

public class DealServiceImpl implements DealService {
    /* Logger instance */
    static final Logger log=Logger.getLogger(DealServiceImpl.class);

    /* DAODeal instance */
    DealDAO dealDAO=new DealDAOImpl();


    /**
     * This method is used to Create Deal
     * @param deal
     * @return status
     */
    @Override
    public String createDeal(ModelDeal deal) {
        log.info("entered into create service");
        String status=dealDAO.create(deal);
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
        List list=dealDAO.retrive(name);
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
        int status=dealDAO.delete(id);
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
        int status=dealDAO.update(deal);
        log.info(status+"from service");
        return status;
    }
}
