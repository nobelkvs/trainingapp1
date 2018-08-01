

package com.lms.DAO;

import com.lms.Model.LmsModel;
import org.junit.Test;



import static junit.framework.TestCase.assertEquals;

public class LmsDAOImplTest {

    LmsDAOImpl lmsDAO=new LmsDAOImpl();

    @Test
     public void testCreateDAO(){
         LmsModel lm=new LmsModel();

         lm.setEmpId(96);
         lm.setEmpName("rajani");
         lm.setFromDate("1027-12-11");
         lm.setToDate("1021-09-16");
         lm.setLeaveType("causal leave");
         lm.setComments("normal");
         lm.setNo_Of_Days(1);
         int status=lmsDAO.createLms(lm);

         assertEquals(status,status);
     }
     @Test
    public void testRetrieveDAO(){

        lmsDAO.retrieveByEmpName("manisha");
    }

}

