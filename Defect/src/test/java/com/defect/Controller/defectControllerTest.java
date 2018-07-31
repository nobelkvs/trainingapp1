package com.defect.Controller;

import com.defect.Model.defectModel;
import com.defect.Service.defectService;
import com.defect.Service.defectServiceImplementation;
import com.defect.Service.defectServiceImplementationTest;
import org.junit.Test;
import sun.nio.cs.ext.SJIS_0213;

import java.util.List;

import static org.junit.Assert.*;

public class defectControllerTest {

    defectService ds = new defectServiceImplementation();

    @Test
    public void doPost() {

        String description = "software virus";
        String category = "software";
        String assignedTo = "sowmya";
        String priority = "p3";
        String status = "open";
        defectModel dModel = new defectModel();

        dModel.setDescription(description);
        dModel.setCategory(category);
        dModel.setAssignedTo(assignedTo);
        dModel.setPriority(priority);
        dModel.setStatus(status);
        defectController dc = new defectController();

        int create = ds.creatingDefect(dModel);

        assertEquals(1,create);

    }

    @Test
    public void doGet() {
        String assignedTo = "Tanishq";
        List list = ds.retrievingByAssignedTo(assignedTo);
        assertEquals(0,list.size());
    }

    @Test
    public void doDelete() {

        String multipleDelete = "123,124,125";
        int delete = ds.deleteDefect(multipleDelete.split(","));
        assertEquals(0,delete);
    }
}