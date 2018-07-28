package com.banking.controller;

import java.io.*;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.banking.model.Bankingmodel;
import com.banking.service.Bankingservice;
import com.banking.service.Bankingserviceimpl;
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
public class Bankingcontrollerservlet extends HttpServlet {
    // It is able to access the logger from all classes in Banking Application
    static final Logger log = Logger.getLogger(Bankingcontrollerservlet.class);



    // Create operation using Post method for the Banking Application
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Initialize the insertStatus with 0
        int insertStatus = 0;
        // Print Writer Enables you to write the formatted data to the text-output stream
        PrintWriter out = res.getWriter();
        String Customername = req.getParameter("customername");
        String Branch = req.getParameter("branch");
        String Address = req.getParameter("address");
        Integer Phoneno = Integer.valueOf(req.getParameter("phoneno"));
        String Emailaddr = req.getParameter("emailaddr");


        Bankingmodel b = new Bankingmodel();
        // Setting the value to the object
        b.setCustomername(Customername);
        b.setBranch(Branch);
        b.setAddress(Address);
        b.setPhoneno(Phoneno);
        b.setEmailaddr(Emailaddr);
        // Creating an object for sevice
        Bankingservice bs = new Bankingserviceimpl();
        try {
            insertStatus = bs.createbankingservice(b);
        } catch (SQLException e) {
            // This method helps to trace the exception.
            e.printStackTrace();
        }
        // checking whether the data is inserted or not.
        if (insertStatus >= 1) {
            out.print("success");
        } else
            out.print("failed to insert");
    }





    // Retrieve operation using Get method for the Banking Application
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String branch = req.getParameter("branch");
        List<Bankingmodel> list = new ArrayList<Bankingmodel>();


        // Enables you to write the formatted data to the text-output stream
        PrintWriter out = res.getWriter();
        // Create an object for service
        Bankingservice bs =  new Bankingserviceimpl();
        try {
            list = bs.retrivebybranchname(branch);
            // Gson is used to convert Java Objects into their JSON representation
            Gson gson=new Gson();
            String json=gson.toJson(list);
            out.print(json);

        }catch (Exception e ){
            // This method helps to trace the exception.
            e.printStackTrace();

        }
    }


    // Delete operation using Delete method for the Banking Application

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        PrintWriter out = res.getWriter();
        // Initializing deleteStatus with 0
        int deleteStatus = 0;
        Integer phoneno = Integer.parseInt(req.getParameter("phoneno"));
        // Create an object for service
        Bankingservice bs = new Bankingserviceimpl();
        try {
            deleteStatus = bs.deletebankingservice(phoneno);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        out.println(deleteStatus);
        // used to check whether the data is deleted successfully or not
        if(deleteStatus>=1) {
            log.info("successfully deleted" );

        }
        else
            log.info("failed to delete");

    }

    }


