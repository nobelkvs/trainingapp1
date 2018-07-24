package com.company.controller;

import com.company.model.Company;
import com.company.service.CompanyService;
import com.company.service.CompanyServiceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CompanyController extends HttpServlet {
    //Creating the instance of class
    static final Logger log = Logger.getLogger(CompanyController.class);
    /**
     * Getting the values from UI and sending it to the service for retrieve operation
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        //getting headoffice from UI
        String HeadOffice = req.getParameter("Headoffice");

        //To store the company details
        List<Company> list = new ArrayList<Company>();

        CompanyService companyService = new CompanyServiceImpl();
        list = companyService.retrieveCompanyDetailsByName(HeadOffice);
        //Creating Gson object and then converting it to json
        Gson gson = new Gson();
        String s = gson.toJson(list);
        log.info("json data:" + s);
        out.print(s);

    }

    /**
     * Getting the values from UI and binding those values in the model object then sending it to the service
     * for insert operation
     * @param req
     * @param resp
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int status = 0;

        PrintWriter out = resp.getWriter();
        //Getting the values from UI
        String companyName = req.getParameter("companyname");
        Long employees = Long.parseLong(req.getParameter("employees"));
        String headOffice = req.getParameter("HeadOffice");

        //Setting the values into model
        Company company = new Company();
        company.setName(companyName);
        company.setEmployees(employees);
        company.setHeadOffice(headOffice);

        log.info("In put method" + company);

        //Creating the object to the CompanyServiceImpl
        CompanyService companyService = new CompanyServiceImpl();
        //Status to check whether details are inserted are not
        status = companyService.createCompany(company);

        try {
            //If status is nagative or zero then it will go to else condition
            if (status >= 1) {
                out.println("inserted:" + status);
                log.info("inserted");
            } else {
                out.println("failed:" + status);
                log.info("failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println(e);
        }
    }


    /**
     *  Getting the values from UI and sending it to the service for delete operation.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int deleteStatus = 0;
        PrintWriter out = resp.getWriter();

        //Creating object to the service implementation class
        CompanyService companyService = new CompanyServiceImpl();

        //Getting values from UI
        String companyIds = (req.getParameter("companyIds"));
        String multiple[] = companyIds.split(",");

        try {
            //Status to check whether details are deleted are not
            deleteStatus = companyService.deleteMultipleComp(multiple);
            log.info(deleteStatus + "deleteStatus in controller");

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (deleteStatus >= 1) {
            log.info("successfully deleted");
            out.println("success");
        } else {

            log.warn("Failed");
        }

    }
}
