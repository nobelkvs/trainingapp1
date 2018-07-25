package com.dao;

import com.model.Invest;

import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * provides the Tests for the given implementation methods
 */
public class InvestDAOTest {
    /**
     * used to check for retrival of data, provides the list if match is found in database
     * if expexcted size matches with list size, then test is pass
     */
    /*@Test
    public void testFirst()
    {
        InvestDAO dao=new InvestDAO();
        List<Invest> list=new ArrayList();
        list=dao.retrieveByFirstDAO("Naveen");
        assertEquals(3,list.size());
    }

    *//**
     * used to test for insertion of data,status indicates whehther data is inserted or not
     * if status is equals to expected number, then data is inserted successfully.
     *//*
    @Test
    public void testInsertion()
    {
        Invest i =new Invest();
        i.setFirst_Name("Naveen ");
        i.setLast_Name("NaveenC");
        i.setAnnual_rate(10);
        i.setPrincipal(10000);
        i.setNo_years(5);
        i.setPeriods(6);
        InvestDAO dao=new InvestDAO();
        int status=0;
        status=dao.createInvestDAO(i);
        assertEquals(1,status);

    }

    *//**
     * This method is ued to test for delketion of data, if status is equals to given expected number than data
     * is successfully deleted
     *//*
    @Test
    public void TestDeletion()
    {
        InvestDAO dao=new InvestDAO();
        int status=0;
        status=dao.deleteInvestDAO(8);
        assertEquals(0,status);
    }*/


    @Test(expected = NullPointerException.class)
    public void testNullInsertion()
    {
        Invest i =new Invest();
        i.setLast_Name("Naveen");
        i.setAnnual_rate(10);
        i.setPrincipal(10000);
        i.setNo_years(5);
        i.setPeriods(6);
        InvestDAO dao=new InvestDAO();
        dao.createInvestDAO(i);
    }

}
