package com.defect.Controller;

import com.defect.Model.defectModel;
import com.defect.Service.defectService;
import com.defect.Service.defectServiceImplementation;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller of Defect Application
 */
public class defectController extends HttpServlet{

    // Create instance of logger in defectController class
    static final Logger log = Logger.getLogger(defectController.class);

    /**
     * This method is for taking input from UI and inserting data into Database
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Took input data from UI
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String assignedTo = request.getParameter("assignedTo");
        String priority = request.getParameter("priority");
        String status = request.getParameter("status");

        // Create an object for defectModel
        defectModel defect = new defectModel();

        //setting data to defect model object
        defect.setDescription(description);
        defect.setCategory(category);
        defect.setAssignedTo(assignedTo);
        defect.setPriority(priority);
        defect.setStatus(status);

        //PrintWriter out = response.getWriter();

        // Create an object for defectServiceImplementation
        defectService ds = new defectServiceImplementation();

        int insertStatus = 0;

        try {
            // Call creatingDefectService method by passing defectModel object
            insertStatus = ds.creatingDefectService(defect);

        } catch (Exception e) {
            log.error(e);
            log.info(insertStatus);
        }


        if (insertStatus >= 1)
            log.info("inserted");
        else
            log.info("failed to insert");

    }

    /**
     * This method is for retrieving data with specified parameter
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Create Gson object
        Gson gson = new Gson();

        // Took assignedTo parameter from UI and assigning it to assignedTo
        String assignedTo = request.getParameter("assignedTo");

        List<defectModel> list = new ArrayList<>();

        // PrintWriter enables you to write formatted data into text format
        PrintWriter out = response.getWriter();

        // Create an object for defectServiceImplementation
        defectService ds = new defectServiceImplementation();

        try {

            // Call the retrievingByAssignedToService by passing assignedTo parameter and assigning retrieved data to list
            list = ds.retrievingByAssignedToService(assignedTo);

            // Convert GSON object to JSON
            String json = gson.toJson(list);
            out.print(json);
            log.info(json);

        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }

    }

    /**
     * This method is for deleting the records from Database by specified parameter
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        // Create Gson object
        Gson gson = new Gson();

        // For multiple Delete's
        String Id = request.getParameter("Id");

        // Took each Id into an array by slicing using comma(,)
        String[] multipleDelete = Id.split(",");

        // Create an object for defectServiceImplementation
        defectService ts = new defectServiceImplementation();

        int deleteStatus = 0;

        try {

            // Call deleteDefectService by passing an array
            deleteStatus = ts.deleteDefectService(multipleDelete);

        } catch (Exception e) {
            log.error(e);
            log.info(deleteStatus);
        }


        if (deleteStatus >= 1){
            log.info("deleted");
            out.print("success");
        }
        else{
            log.info("failed to deleted");
        }


    }

}
