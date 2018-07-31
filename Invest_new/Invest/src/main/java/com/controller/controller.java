package com.controller;

import com.google.gson.Gson;
import com.model.Invest;
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

/**
 * Servlet class for Investment Application,which takes takes and passes values to the UI
 *
 * @see InvestService
 */

public class controller extends HttpServlet {
    static final Logger log = Logger.getLogger(controller.class);
    int insertstatus = 0;
    InvestServiceInt service = null;

    /**
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     * @var insertstatus for returning status of the insertion of data
     * doPost(,) method for creating a new investment
     * @see InvestService
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /**
         * Creating a Object of application(model) to send values to service layer
         */
        Invest invest = new Invest();
        /**
         * req.getParameter() is used to take values from the UI text fields
         */
        String fname = req.getParameter("First_Name");
        String lname = req.getParameter("Last_Name");
        int principal = Integer.parseInt(req.getParameter("Principal"));
        int arate = Integer.parseInt(req.getParameter("Annual_rate"));
        int no_years = Integer.parseInt(req.getParameter("No_years"));
        int periods = Integer.parseInt(req.getParameter("Periods"));
        invest.setFname(fname);
        invest.setLname(lname);
        invest.setPrincipal(principal);
        invest.setArate(arate);
        invest.setNo_years(no_years);
        invest.setPeriods(periods);
        PrintWriter out = res.getWriter();
        service = (InvestServiceInt) new InvestService();
        insertstatus = service.createInvest(invest);
        /**
         * out.println() is used to pass the message to the UI
         */
        out.println(insertstatus);
    }

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     * @var deletestatus for returning status of the deletion of data
     * @see InvestService
     *  doDelete(,) method for deleting the given data
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        service = (InvestServiceInt) new InvestService();
        int id = Integer.parseInt(req.getParameter("User_id"));
        PrintWriter out = res.getWriter();
        int deletestatus = 0;
        deletestatus = service.deleteInvest(id);
        out.println(1);
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @list used to get the entire data consisting of first name
     * @see InvestService
     * doGet(,) is used for retrival of data from the database
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service = (InvestServiceInt) new InvestService();
        PrintWriter out = resp.getWriter();
        /**
         * Gson is used for conversion to json data
         */
        Gson gson = new Gson();
        String fname = req.getParameter("First_Name");
        try {
            log.info("IN GET BLOCK");
            List<Invest> list_first = service.retrieveByFirst(fname);
            String jp = gson.toJson(list_first);
            out.println(jp);
            log.info(list_first);
        } catch (Exception e) {
            out.println("RETRIEVAL FAILED");
        }
    }

}