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

    public int createLmsService(LmsModel lms) {
        log.info("serviceimpl");
        int createStatus = 0;
        createStatus = LmsDAOImpl.createLmsDAO(lms);
        return createStatus;
    }

    public List<LmsModel> retrieveByEmpId(int EmpId) {
        log.info("retrieve serviceimpl");
        LmsDAOImpl ldao = new LmsDAOImpl();
        List<LmsModel> modelList = ldao.retrieveByEmpId(EmpId);
        log.info(modelList);
        return modelList;
    }

    public int deleteLmsService(int EmpId) {
        log.info("serviceimpl");
        int deleteStatus = 0;
        deleteStatus = LmsDAOImpl.deleteLmsDAO(EmpId);
        return deleteStatus;
    }
  /*  public static int updateLmsService(LmsModel lms) {
        int updateStatus = 0;
        LmsDAOImpl td = new LmsDAOImpl();
        updateStatus = td.updateLmsDAO(lms);
        return updateStatus;
    }
*/
}