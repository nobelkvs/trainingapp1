package com.defect.Service;

import com.defect.Model.defectModel;

import java.util.List;

/**
 * Service of Defect Application
 */
public interface defectService {

    /**
     * method declaration for creating defects
     * @param dm
     * @return
     */
    int creatingDefect(defectModel dm);

    /**
     * method declaration for retrieving defects by Assigned To
     * @param assignedTo
     * @return
     */
    List<defectModel> retrievingByAssignedTo(String assignedTo);

    /**
     * method declaration for deleting multiple defects
     * @param Id
     * @return
     */
    int deleteDefect(String[] Id);
}
