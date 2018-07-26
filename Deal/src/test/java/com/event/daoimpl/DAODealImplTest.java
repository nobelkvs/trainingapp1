package com.event.daoimpl;


import com.event.dao.DAODeal;
import com.event.model.ModelDeal;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DAODealImplTest {
    ModelDeal deal=new ModelDeal();
    @Test
    public void testCreateDAO() {

        deal.setDealName("nidhi");
        deal.setOwnerName("tara");
        deal.setDealValue("45L");
        deal.setProbability(20);
        deal.setCustomerName("druthi");
        deal.setCustomerContact("1578359648");
        DAODeal dao=new DAODealImpl();
        String  mesg=dao.createDAO(deal);
        String originalmesg="****Deal created successfully****";
        //String othermesg="deal created";
        assertEquals(mesg,originalmesg);
        //System.out.println("deal created successfully");
    }

    @Test
    public void testRetriveDAO() {
        DAODeal dao=new DAODealImpl();
        String name="aparna";
        List list=dao.retriveDAO(name);
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
        DAODeal dao=new DAODealImpl();
        int i=5;
        int deleteStatus=dao.deleteDAO(i);
        assertEquals(0,deleteStatus);
    }

    @Test
    public void testUpdateDAO() {
        DAODeal dao=new DAODealImpl();
        deal.setDealValue("99L");
        deal.setProbability(99);
       deal.setDealId(1);
        int updateStatus=dao.updateDAO(deal);
        assertEquals(1,updateStatus);
        //System.out.println(mesg);
    }

}