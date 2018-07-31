package com.defect.Service;

import com.defect.DAO.defectDAO;
import com.defect.DAO.defectDAOImplementation;
import com.defect.Model.defectModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class defectServiceImplementationTest {

    defectDAO dDAO = new defectDAOImplementation();

    @Test
    public void creatingDefectService() {

        defectModel dm = new defectModel();

        dm.setDescription("software virus");
        dm.setCategory("software");
        dm.setAssignedTo("sowmya");
        dm.setPriority("p3");
        dm.setStatus("open");

        int createStatus = dDAO.createDefect(dm);
        assertEquals(1,createStatus);

    }

    @Test
    public void retrievingByAssignedToService() {
        String assignedTo = "Tanishq";
        List list = dDAO.retrievingByAssignedTo(assignedTo);
        assertEquals(0,list.size());
    }

    @Test
    public void deleteDefectService() {
        String multipleDelete = "123,124,125";
        int delete = dDAO.deleteDefect(multipleDelete.split(","));
        assertEquals(0,delete);
    }
}