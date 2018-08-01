package com.event.daoimpl;


import com.event.dao.DealDAO;
import com.event.model.ModelDeal;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DAODealImplTest {
    DealDAO dao=new DealDAOImpl();
    ModelDeal deal=new ModelDeal();
    @Test
    public void testCreateDAO() {

        deal.setDealName("nidhi");
        deal.setOwnerName("tara");
        deal.setDealValue("45L");
        deal.setProbability(20);
        deal.setCustomerName("druthi");
        deal.setCustomerContact("1578359648");
        String  mesg=dao.create(deal);
        String originalmesg="****Deal created successfully****";
        assertEquals(mesg,originalmesg);
    }

    @Test
    public void testRetriveDAO() {
        String name="aparna";
        List list=dao.retrive(name);
        int id=1;
       int retrivedDealId=0;
        Iterator itr=list.iterator();
        while (itr.hasNext()){
            ModelDeal deal= (ModelDeal) itr.next();
            retrivedDealId=deal.getDealId();
        }
        assertEquals(id,retrivedDealId);
    }

    @Test
    public void testDeleteDAO() {
        int i=5;
        int deleteStatus=dao.delete(i);
        assertEquals(0,deleteStatus);
    }

    @Test
    public void testUpdateDAO() {
        deal.setDealValue("99L");
        deal.setProbability(99);
       deal.setDealId(1);
        int updateStatus=dao.update(deal);
        assertEquals(1,updateStatus);
    }

}