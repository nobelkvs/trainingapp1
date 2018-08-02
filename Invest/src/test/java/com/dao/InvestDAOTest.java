
package com.dao;

import com.model.Invest;

import com.service.InvestService;
import com.service.InvestServiceInt;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.PrintWriter;
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

    @Test
    public void testFirstDAO()
    {
        InvestDAO dao=new InvestDAO();
        List<Invest> list=new ArrayList();
        list=dao.retrieveByFirst("Naveen");
        assertEquals(6,list.size());
    }


/**
     * used to test for insertion of data,status indicates whehther data is inserted or not
     * if status is equals to expected number, then data is inserted successfully.
     */

    @Test
    public void testInsertionDAO()
    {
        Invest i =new Invest();
        i.setFname("Naveen ");
        i.setLname("NaveenC");
        i.setArate(10);
        i.setPrincipal(10000);
        i.setNo_years(5);
        i.setPeriods(6);
        InvestDAO dao=new InvestDAO();
        int status=0;
        status=dao.createInvest(i);
        assertEquals(0,status);

    }


/**
     * This method is ued to test for deletion of data, if status is equals to given expected number than data
     * is successfully deleted
     */

    @Test
    public void TestDeletionDAO()
    {
        InvestDAO dao=new InvestDAO();
        int status=0;
        status=dao.deleteInvest(8);
        assertEquals(0,status);
    }
    @Test
    public void testInsertionController()
    {
        Invest invest = new Invest();
        InvestServiceInt service=null;
        int insertstatus=0;
        String fname = "naveen";
        String lname ="sonuna";
        int principal = 1000;
        int arate = 2;
        int no_years =3;
        int periods = 4;
        invest.setFname(fname);
        invest.setLname(lname);
        invest.setPrincipal(principal);
        invest.setArate(arate);
        invest.setNo_years(no_years);
        invest.setPeriods(periods);

        service = (InvestServiceInt) new InvestService();
        insertstatus = service.createInvest(invest);
        assertEquals(0,insertstatus);
    }

   @Test
    public void testDeletionController()
    {
        InvestServiceInt service=null;
        service = (InvestServiceInt) new InvestService();
        int id = 50;
        int deletestatus = 0;
        deletestatus = service.deleteInvest(id);
        assertEquals(0,deletestatus);
    }
    @Test
    public void testRetrievalController()
    {
        InvestServiceInt service=null;
        service = (InvestServiceInt) new InvestService();
        List<Invest> list_first = service.retrieveByFirst("naveen");
        assertEquals(6,list_first.size());

    }


}

