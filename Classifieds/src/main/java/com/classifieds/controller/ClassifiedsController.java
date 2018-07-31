package com.classifieds.controller;

import com.classifieds.model.Classifieds;
import com.classifieds.service.ClassifiedsService;
import com.classifieds.service.ClassifiedsServiceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet class with methods to create , retrieve and delete records
 */
public class ClassifiedsController extends HttpServlet {

    // statement for logger
    static final Logger log = Logger.getLogger(ClassifiedsController.class);


    /**
     * This method is for inserting records into database
     *
     * @param req
     * @param res
     * @return insertStatus to return status
     * @throws ServletException
     * @throws IOException
     * @see com.classifieds.service
     */

    //method to create classifieds
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //variable to return status
        int insertStatus = 0;
        String classifiedDescription = req.getParameter("classifiedDescription");
        String category = req.getParameter("category");
        String city = req.getParameter("city");

        //settings params to model object

        Classifieds classified = new Classifieds();

        classified.setClassifiedDescription(classifiedDescription);
        classified.setCategory(category);
        classified.setCity(city);

        //creating service object
        ClassifiedsService cs = new ClassifiedsServiceImpl();

        try {

            //calling service method and returning status

            insertStatus = cs.createClassifiedsService(classified);

        } catch (SQLException e) {

            log.error(e);

        }

        //validating and printing status


        log.info(insertStatus < 1 ? "failed to insert" : "successfully inserted");
    }

    /**
     * This method is for retrieving records from database
     *
     * @param req
     * @param res
     * @return list to retrieve records from database
     * @throws ServletException
     * @throws IOException
     * @see com.classifieds.service
     */

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //param to retrieve data
        String city = req.getParameter("city");

        PrintWriter out = res.getWriter();

        //retrieving data from table in a list

        List<Classifieds> list = new ArrayList<Classifieds>();

        //creating service object

        ClassifiedsService cs = new ClassifiedsServiceImpl();

        try {

            //retreive data from the database into a list

            list = cs.retrieveClassifiedsService(city);

            //converting list data to json
            Gson gson = new Gson();
            String str = gson.toJson(list);

            out.println(str);
            log.info(str);

        } catch (SQLException e) {

            log.error(e);

            log.info("unable to retrieve" + e);
        }

    }

    /**
     * This method is for deleting the records
     *
     * @param req
     * @param resp
     * @return deleteStatus to return the status
     * @throws ServletException
     * @throws IOException
     * @see com.classifieds.service
     */

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //params for multiple deletion

        String muldel = req.getParameter("cid");

        //splitting strings with commas and storing in an array

        String[] multidel = muldel.split(",");

        PrintWriter out = resp.getWriter();

        int deleteStatus = 0;

        //creating service object

        ClassifiedsService cs = new ClassifiedsServiceImpl();

        try {

            //calling service method and returning status

            deleteStatus = cs.deleteClassifiedsService(multidel);

        } catch (SQLException e) {

            log.error(e);
        }

        //validating and printing status

        if (deleteStatus >= 1) {

            log.info("successfully deleted");

            out.print("success");
        } else

            log.warn("failed to delete");


    }
}
