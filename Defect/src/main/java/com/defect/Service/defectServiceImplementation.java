package com.defect.Service;

import com.defect.DAO.defectDAO;
import com.defect.DAO.defectDAOImplementation;
import com.defect.Model.defectModel;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * defectServiceImplementation of Defect Application
 */
public class defectServiceImplementation implements defectService {

    // Create instance of logger in defectServiceImplementation class
    static final Logger log = Logger.getLogger(defectServiceImplementation.class);

    // Create an DAO object
    defectDAO dDAO = new defectDAOImplementation();

    /**
     * method implementation for creating defects
     * @param dm
     * @return
     */
    public int creatingDefectService(defectModel dm) {

        log.info("In Create defect Service");

        // Call createDefectDAO method
        int createStatus = dDAO.createDefectDAO(dm);

        return createStatus;
    }

    /**
     * method implementation for retrieving defects by Assigned To
     * @param assignedTo
     * @return
     */
    public List<defectModel> retrievingByAssignedToService(String assignedTo) {

        log.info("In Retrieve by Assigned to defect Service");

        // Call retrievingByAssignedToDAO method and returning the retrieved list
        List<defectModel> retrieveAssignedStatus = dDAO.retrievingByAssignedToDAO(assignedTo);

        return retrieveAssignedStatus;
    }

    /**
     * method implementation for deleting multiple defects
     * @param Id
     * @return
     */
    public int deleteDefectService(String[] Id) {

        log.info("In Delete defect Service");

        // Call deleteDefectDAO method
        int deleteStatus = dDAO.deleteDefectDAO(Id);

        return deleteStatus;
    }
}
