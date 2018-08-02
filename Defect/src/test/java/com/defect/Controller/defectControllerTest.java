package com.defect.Controller;

import com.defect.Model.defectModel;
import com.defect.Service.defectService;
import com.defect.Service.defectServiceImplementation;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test Controller of Defect Application
 */
public class defectControllerTest {

    // Create an object for defectServiceImplementation
    defectService ds = new defectServiceImplementation();

    @Test
    /**
     * Test case for doPost method
     */
    public void doPost() {

        // Create an object for defectModel
        defectModel dModel = new defectModel();

        // Setting Test values
        dModel.setDescription("Software Virus");
        dModel.setCategory("software");
        dModel.setAssignedTo("sowmya");
        dModel.setPriority("p3");
        dModel.setStatus("open");

        // Call creatingDefect method in Service class by passing defectModel object
        int create = ds.creatingDefect(dModel);

        //checking the expected and actual results
        assertEquals(1,create);

    }

    @Test
    /**
     * Test case for doGet method
     */
    public void doGet() {

        // Setting Test values
        String assignedTo = "Tanishq";

        // Call the retrievingByAssignedTo in Service class by passing assignedTo parameter and assigning retrieved data to list
        List list = ds.retrievingByAssignedTo(assignedTo);

        //checking the expected and actual results
        assertEquals(0,list.size());
    }

    @Test
    /**
     * Test case for doDelete method
     */
    public void doDelete() {

        // Setting Test values
        String multipleDelete = "123,124,125";

        // Call deleteDefectService in Service by passing an String
        int delete = ds.deleteDefect(multipleDelete.split(","));

        //checking the expected and actual results
        assertEquals(0,delete);
    }
}