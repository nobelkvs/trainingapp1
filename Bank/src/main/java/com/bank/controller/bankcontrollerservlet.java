package com.bank.controller;
import java.io.*;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.bank.model.bankmodel;
import com.bank.service.bankservice;
import com.bank.service.bankserviceimpl;
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

public class bankcontrollerservlet extends HttpServlet {
    // It is able to access the logger from all classes in Banking Application
    static final Logger log=Logger.getLogger(bankcontrollerservlet.class);

    // Create operation for Bank Application using Post method
    protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
        // Initialize 0 to insertStatus
        int insertStatus=0;
        // Enables you to write formatted data to text-output stream
        PrintWriter out=res.getWriter();
        String name=req.getParameter("name");
        String branch=req.getParameter("branch");
        String address=req.getParameter("address");
        Integer phno= Integer.valueOf(req.getParameter("phno"));
        String email=req.getParameter("email");
        // Create an object for model class
        bankmodel bm=new bankmodel();
        // Setting the value to the object
        bm.setName(name);
        bm.setBranch(branch);
        bm.setAddress(address);
        bm.setPhno(phno);
        bm.setEmail(email);
        // Create an object for sevice
        bankservice bs=new bankserviceimpl();
        try{
            insertStatus=bs.createbankservice(bm);
        }catch (SQLException e){
            // This method helps to trace the exception.
            e.printStackTrace();
        }
        // This if-else block is used to check whether data is inserted successfully or not
        if(insertStatus>=1) {
            out.print("successfully inserted");
        } else
            out.print("failed to insert");
    }

    // Retrieve operation for Bank Application using Get method
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // The request.getParameter() method here is used to retrieve the input values
        String branch = req.getParameter("branch");
        // This list is able to invoke methods
        List<bankmodel> list = new ArrayList<bankmodel>();
        // Enables you to write formatted data to text-output stream
        PrintWriter out = resp.getWriter();
        // Create an object for service
        bankservice bs =  new bankserviceimpl();
        try {
            list = bs.retrivebybname(branch);
            // Gson is used to convert Java Objects into their JSON representation
            Gson gson=new Gson();
            String json=gson.toJson(list);
            out.print(json);
            //log("hello");
        }catch (Exception e ){
            // This method helps to trace the exception.
            e.printStackTrace();

        }
    }


    // Delete operation for Bank Application using Delete method

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Enables you to write formatted data to text-output stream
        PrintWriter out=resp.getWriter();
        // Initialize 0 to deleteStatus
        int deleteStatus = 0;
        Integer phno = Integer.valueOf(req.getParameter("phno"));
        // Create an object for service
        bankservice bs = new bankserviceimpl();
        try {
            deleteStatus =  bs.deletebankservice(phno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String json = gson.toJson(deleteStatus);
        out.println(json);
        // This if-else block is used to check whether data is inserted successfully or not
        if(deleteStatus>=1) {
            log.info("successfully deleted" + deleteStatus);

        }
        else
            log.info("failed to delete"+deleteStatus);
    }
}
