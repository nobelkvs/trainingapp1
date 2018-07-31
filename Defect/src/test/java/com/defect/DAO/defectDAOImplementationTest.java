package com.defect.DAO;

import com.defect.Model.defectModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class defectDAOImplementationTest {

    defectDAO dDAO = new defectDAOImplementation();

    @Test
    public void createDefectDAO() {

        defectModel dModel = new defectModel();
        dModel.setDescription("software virus");
        dModel.setCategory("software");
        dModel.setAssignedTo("sowmya");
        dModel.setPriority("p3");
        dModel.setStatus("open");

        int create = dDAO.createDefect(dModel);

        assertEquals(1,create);

    }

    @Test
    public void retrievingByAssignedToDAO() {

        String assignedTo = "Tanishq";
        List list = dDAO.retrievingByAssignedTo(assignedTo);
        assertEquals(0,list.size());

    }

    @Test
    public void deleteDefectDAO() {

        String multipleDelete = "123,124,125";
        int delete = dDAO.deleteDefect(multipleDelete.split(","));
        assertEquals(0,delete);
    }
}