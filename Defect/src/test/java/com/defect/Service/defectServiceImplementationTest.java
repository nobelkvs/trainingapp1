package com.defect.Service;

import com.defect.DAO.defectDAO;
import com.defect.DAO.defectDAOImplementation;
import com.defect.Model.defectModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test ServiceImplementation of Defect Application
 */
public class defectServiceImplementationTest {

    // Create an DAO object
    defectDAO dDAO = new defectDAOImplementation();

    @Test
    /**
     * Test case for createDefect method
     */
    public void creatingDefect() {

        // Create an object for defectModel
        defectModel dm = new defectModel();

        // Setting Test values
        dm.setDescription("software virus");
        dm.setCategory("software");
        dm.setAssignedTo("sowmya");
        dm.setPriority("p3");
        dm.setStatus("open");

        // Call creatingDefect method in DAO class by passing defectModel object
        int createStatus = dDAO.createDefect(dm);

        //checking the expected and actual results
        assertEquals(1,createStatus);

    }

    @Test
    /**
     * Test case for retrievingByAssignedTo method
     */
    public void retrievingByAssignedTo() {

        // Setting Test values
        String assignedTo = "Tanishq";

        //Call the retrievingByAssignedTo in Service class by passing assignedTo parameter and assigning retrieved data to list
        List list = dDAO.retrievingByAssignedTo(assignedTo);

        //checking the expected and actual results
        assertEquals(0,list.size());
    }

    @Test
    /**
     * Test case for deleteDefect method
     */
    public void deleteDefect() {

        // Setting Test values
        String multipleDelete = "123,124,125";

        // Call deleteDefectService in Service by passing an String
        int delete = dDAO.deleteDefect(multipleDelete.split(","));

        //checking the expected and actual results
        assertEquals(0,delete);
    }
}