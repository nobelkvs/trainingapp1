/*
package com.lms.DAO;

import com.lms.Model.LmsModel;
import org.junit.Test;
import util.MysqlDB;


import static junit.framework.TestCase.assertEquals;

public class LmsDAOImplTest {

    LmsDAOImpl lmsDAO=new LmsDAOImpl();

    @Test
     public void testCreateDAO(){
         LmsModel lm=new LmsModel();

         lm.setEmpId(96);
         lm.setFromDate("1027-12-11 13:23:42");
         lm.setToDate("1021-09-16 13:26:42");
         lm.setLeaveType("metrooo");
         lm.setComments("ddddd");
         lm.setNo_Of_Days(1);
         int status=lmsDAO.createLmsDAO(lm);

         assertEquals(1,status);
     }
     @Test
    public void testRetrieveDAO(){

        lmsDAO.retrieveByEmpId(94);
    }
    @Test
    public void testDeleteDAO(){
        int  deleteStatus = lmsDAO.deleteLmsDAO(94);
       assertEquals(0,deleteStatus);
     //  assertNull(0);

    }

}*/
