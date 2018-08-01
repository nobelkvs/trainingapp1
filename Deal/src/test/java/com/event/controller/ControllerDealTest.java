package com.event.controller;

import com.event.model.ModelDeal;
import com.event.serviceimpl.DealServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Test;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ControllerDealTest {

    static final Logger log=Logger.getLogger(ControllerDealTest.class);
    DealServiceImpl dealService=new DealServiceImpl() ;
    ModelDeal deal=new ModelDeal();

    @Test
    public void create(){

        deal.setDealName("Arnav");
        deal.setOwnerName("Manisha");
        deal.setDealValue("40L");
        deal.setProbability(20);
        deal.setCustomerName("uma");
        deal.setCustomerContact("1547859624");
        String status=dealService.createDeal(deal);
        log.info(status);
        String result="****Deal created successfully****";
        assertEquals(status,result);

    }

    @Test
    public void retrieve(){

        String dealName="Arnav";
        List list =dealService.retriveDeal(dealName);
        log.info(list);
        String retrievedDealName = "";
        Iterator itr=list.iterator();
            while (itr.hasNext()) {
                ModelDeal deal = (ModelDeal) itr.next();
                retrievedDealName= deal.getDealName();
                assertEquals(dealName, retrievedDealName);
            }



    }

    @Test
    public void update(){
        deal.setDealId(1);
        deal.setDealValue("50L");
        deal.setProbability(50);
        int status = dealService.updateDeal(deal);
        log.info(status);
        int result=1;
        assertEquals(status,result);
    }

    @Test
    public void delete(){
        String id="221,222";
        String[] arr=id.split(",");
        for(int i=0;i<arr.length;i++){
            int id1=Integer.parseInt(arr[i]);
            int status=dealService.deleteDeal(id1);
            log.info(status);
            int result=0;
            assertEquals(status,result);
        }
    }

}