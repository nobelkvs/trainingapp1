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
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;

/**
 * Servlet class for Investment Application,which takes takes and passes values to the UI
 *
 * @see com.service.InvestService
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
     * @see com.service.InvestService
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /**
         * Creating a Object of application(model) to send values to service layer
         */
        Invest invest = new Invest();
        /**
         * req.getParameter() is used to take values from the UI text fields
         */
        String First_Name = req.getParameter("First_Name");
        String Last_Name = req.getParameter("Last_Name");
        int Principal = Integer.parseInt(req.getParameter("Principal"));
        int Annual_rate = Integer.parseInt(req.getParameter("Annual_rate"));
        int No_years = Integer.parseInt(req.getParameter("No_years"));
        int Periods = Integer.parseInt(req.getParameter("Periods"));
        invest.setFirst_Name(First_Name);
        invest.setLast_Name(Last_Name);
        invest.setPrincipal(Principal);
        invest.setAnnual_rate(Annual_rate);
        invest.setNo_years(No_years);
        invest.setPeriods(Periods);
        PrintWriter out = res.getWriter();
        service = (InvestServiceInt) new InvestService();
        insertstatus = service.createInvestService(invest);
        /**
         * out.println() is used to pass the message to the UI
         */
        if (insertstatus >= 1)
            out.println("INSERTED SUCCESSFULLY");
        else
            out.println("INSSERTION FAILED");
    }

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     * @var deletestatus for returning status of the deletion of data
     * @see com.service.InvestService
     *  doDelete(,) method for deleting the given data
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        service = (InvestServiceInt) new InvestService();
        int id = Integer.parseInt(req.getParameter("User_id"));
        PrintWriter out = res.getWriter();
        int deletestatus = 0;
        deletestatus = service.deleteInvestService(id);
        out.println(deletestatus);
    }

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @list used to get the entire data consisting of first name
     * @see com.service.InvestService
     * doGet(,) is used for retrival of data from the database
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service = (InvestServiceInt) new InvestService();
        PrintWriter out = resp.getWriter();
        /**
         * Gson is used for conversion to json data
         */
        Gson gson = new Gson();
        String First_Name = req.getParameter("First_Name");
        try {
            log.info("IN GET BLOCK");
            List<Invest> list_first = service.retrieveByFirstService(First_Name);
            String jp = gson.toJson(list_first);
            out.println(jp);
            log.info(list_first);
        } catch (Exception e) {
            out.println("RETRIEVAL FAILED");
        }
    }


}



