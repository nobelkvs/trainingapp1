package com.lms.Controller;

import com.lms.Model.LmsModel;
import com.lms.Service.LmsServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ControllerServletTest {



    @Test
    public void Testcreate(){

        LmsModel lms = new LmsModel();
        lms.setEmpId(123);
        lms.setEmpName("saiteja");
        lms.setFromDate("1027-12-11 13:23:42");
        lms.setToDate("1028-11-11 13:25:42");
        lms.setLeaveType("causal leave");
        lms.setComments("normal leave");
        LmsServiceImpl lmsService=new LmsServiceImpl();
      int status=  lmsService.createLms(lms);
        assertEquals(status,status);
    }
    @Test
    public void Testretrieve(){
        LmsModel lms=new LmsModel();

        LmsServiceImpl lmsService=new LmsServiceImpl();
     List retrieveStatus=lmsService.retrieveByEmpName("manisha");
     assertEquals( retrieveStatus,retrieveStatus);
    }

}

