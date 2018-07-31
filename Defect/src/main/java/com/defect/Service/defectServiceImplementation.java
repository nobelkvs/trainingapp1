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
    public int creatingDefect(defectModel dm) {

        log.info("In Create defect Service");

        // Call createDefectDAO method
        int createStatus = dDAO.createDefect(dm);

        return createStatus;
    }

    /**
     * method implementation for retrieving defects by Assigned To
     * @param assignedTo
     * @return
     */
    public List<defectModel> retrievingByAssignedTo(String assignedTo) {

        log.info("In Retrieve by Assigned to defect Service");

        // Call retrievingByAssignedToDAO method and returning the retrieved list
        List<defectModel> retrieveAssignedStatus = dDAO.retrievingByAssignedTo(assignedTo);

        return retrieveAssignedStatus;
    }

    /**
     * method implementation for deleting multiple defects
     * @param Id
     * @return
     */
    public int deleteDefect(String[] Id) {

        log.info("In Delete defect Service");

        // Call deleteDefectDAO method
        int deleteStatus = dDAO.deleteDefect(Id);

        return deleteStatus;
    }
}
