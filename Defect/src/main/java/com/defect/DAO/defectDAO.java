package com.defect.DAO;

import com.defect.Model.defectModel;

import java.util.List;

/**
 * DAO of Defect Application
 */
public interface defectDAO {

    /**
     * method declaration for creating defects
     * @param dm
     * @return
     */
    int createDefectDAO(defectModel dm);

    /**
     * method declaration for retrieving defects by Assigned To
     * @param assignedTo
     * @return
     */
    List<defectModel> retrievingByAssignedToDAO(String assignedTo);

    /**
     * method declaration for deleting multiple defects
     * @param Id
     * @return
     */
    int deleteDefectDAO(String[] Id);
}
