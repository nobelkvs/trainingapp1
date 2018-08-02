package com.defect.DAO;

import com.defect.Model.defectModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test DAOImplementation of Defect Application
 */
public class defectDAOImplementationTest {

    // Create an DAO object
    defectDAO dDAO = new defectDAOImplementation();

    @Test
    /**
     * Test case for createDefect method
     */
    public void createDefect() {

        // Create an object for defectModel
        defectModel dModel = new defectModel();

        // Setting Test values
        dModel.setDescription("software virus");
        dModel.setCategory("software");
        dModel.setAssignedTo("sowmya");
        dModel.setPriority("p3");
        dModel.setStatus("open");

        // Call creatingDefect method in DAO class by passing defectModel object
        int create = dDAO.createDefect(dModel);

        //checking the expected and actual results
        assertEquals(1,create);

    }

    @Test
    /**
     * Test case for retrievingByAssignedTo method
     */
    public void retrievingByAssignedTo() {

        // Setting Test values
        String assignedTo = "Tanishq";

        //Call the retrievingByAssignedTo in DAO class by passing assignedTo parameter and assigning retrieved data to list
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

        // Call deleteDefect in DAO Class by passing an String
        int delete = dDAO.deleteDefect(multipleDelete.split(","));

        //checking the expected and actual results
        assertEquals(0,delete);
    }
}