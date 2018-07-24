package com.controller;

import com.dao.InvestDAO;
import com.model.admin_model;
import com.service.InvestServiceInt;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Admin extends HttpServlet {
    static final Logger log = Logger.getLogger(com.controller.controller.class);
    int status = 0;
    InvestServiceInt service = null;

    /**
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     * @var insertstatus for returning status of the verification of user
     * doPost(,) method for creating a new investment
     * @see com.service.InvestService
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
        status = dao.checkAdmin(admin);
        out.println(status);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /**
         * get method
         */
    }
}
