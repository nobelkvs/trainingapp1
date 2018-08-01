package com.lms.Service;

import com.lms.DAO.LmsDAOImpl;
import com.lms.Model.LmsModel;
import org.apache.log4j.Logger;
import java.util.List;

public class LmsServiceImpl {

     /*This logging allows you to report and persist error and warning
   messages as well as info messages
   */

    static final Logger log = Logger.getLogger(LmsServiceImpl.class);
    int createstatus;
    int updateStatus;
    int deleteStatus;
/* Method to create the leave to the dao */
    public int createLms(LmsModel lms) {
        log.info(" create serviceimpl");
        int createStatus = 0;
        createStatus = LmsDAOImpl.createLms(lms);
        return createStatus;
    }
/* Method to retrieve the leaves from dao */
    public List<LmsModel> retrieveByEmpName(String EmpName) {
        log.info("retrieve serviceimpl");
        LmsDAOImpl ldao = new LmsDAOImpl();
        List<LmsModel> modelList = ldao.retrieveByEmpName(EmpName);
        log.info(modelList);
        return modelList;
    }

}