package com.controller;

import com.dao.InvestDAO;
import com.google.gson.Gson;
import com.model.Invest;
import com.model.admin_model;
import com.service.InvestService;
import com.service.InvestServiceInt;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Admin extends HttpServlet {
    static final Logger log = Logger.getLogger(com.controller.controller.class);

    /**
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     * doPost(,) method for creating a new investment
     * @see com.dao.InvestDAO
     */

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        admin_model admin = new admin_model();
        InvestDAO dao = new InvestDAO();
        /**
         * req.getParameter() is used to take values from the UI text fields
         */
        String Uname = req.getParameter("Uname");
        String password = req.getParameter("password");
        admin.setPassword(password);
        admin.setUname(Uname);

        PrintWriter out = res.getWriter();
        if(Uname.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin"))
        //out.println(,) dislpays the data in UI
        {
            out.println(1);
        }
        else
        {
            out.println(0);
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

            InvestService service = new InvestService();
            PrintWriter out = res.getWriter();
            /**
             * Gson is used for conversion to json data
             */
            Gson gson = new Gson();
            try {
                log.info("IN GET BLOCK");
                List<Invest> list_details = service.retrieveDetailsService();
                String jp = gson.toJson(list_details);
                //passes the data to UI in the form of Object
                out.println(jp);
                log.info(list_details);
            } catch (Exception e) {
                //if database is empty, then it return the belo statement
                out.println("NO DATA FOUND");
            }

    }
}