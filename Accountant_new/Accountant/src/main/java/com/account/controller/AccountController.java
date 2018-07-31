package com.account.controller;

import com.account.model.Account;
import com.account.service.AccountService;
import com.account.service.AccountServiceImpl;
import com.google.gson.Gson;
import com.sun.tracing.dtrace.FunctionName;
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
 * doPost method is use for insertion of data in the database
 * doGet Method is use to retrieval of data
 * doDelete method is use to delete the data
 */
public class AccountController extends HttpServlet {
    static final Logger log = Logger.getLogger(AccountController.class);


    /**
     * @param req for creating the request
     * @param res for getting the responce
     * @return status for confirmation of the operation
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int status = 0;
        PrintWriter out = res.getWriter();

        //getting the parameters which are passed by the user 
        String Fname = req.getParameter("Fname");
        log.info(Fname);

        String Lname = req.getParameter("Lname");
        log.info(Lname);
        String Bankname = req.getParameter("Bankname");
        String Branch = req.getParameter("Branch");
        String Address = req.getParameter("Address");
        int Phone = Integer.parseInt(req.getParameter("Phone"));

        // Setting the parameter
        Account account = new Account();
        account.setFname(Fname);
        account.setLname(Lname);
        account.setBankname(Bankname);
        account.setBranch(Branch);
        account.setAddress(Address);
        account.setPhone(Phone);
        AccountService aservice = new AccountServiceImpl();
        try {

            log.info("inside calling inseting details in account");
            // Using the service method to insert the data in the database
            status = aservice.createAccountService(account);
            log.info(account);
        } catch (SQLException e) {
            log.info(e);

            e.printStackTrace();
        }

        try {

            // Checking t for the status 
            if (status >= 1)
                log.info(status);
            else
                log.info(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e);

        }


    }

    /**
     * @var Fname is use to retrieve the data
     * using toJson() method to convert the list into Json format
     * @throw ServletException , IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("inside retrieving data from database");
        // Getting the parameter
        String Fname = req.getParameter("Fname");

        // Creating the list
        List<Account> list = new ArrayList<Account>();
        PrintWriter out = resp.getWriter();
        AccountService accountService = new AccountServiceImpl();
        try {
            Gson gson = new Gson();

            // Retrieving the details from database
            list = accountService.retriveByName(Fname);

            // Getting the list in Key value pair using json object
            String json = gson.toJson(list);
            out.println(json);
            log.info(json);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e);
       }

    }

    /**
     * @return using deletestatus for confirmation of operation
     * @var using Id for deleting the particular account
     * @throw ServletException , IOException
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("inside deleting function");
        PrintWriter out = resp.getWriter();
        int deleteStatus = 0;

        // Fetching the parameter entered 
        String Id = req.getParameter("Id");

        // If there are multiple parameters which are seperated by commmas it will split this 
        String multiple[] = Id.split(",");

        AccountService accountService = new AccountServiceImpl();
        try {

            log.info("calling delete account service method");
            // Call the delete method ----------------------------
            deleteStatus = accountService.deleteAccount(multiple);
            log.info(deleteStatus);
        } catch (SQLException e) {
            e.printStackTrace();
            log.warn(e);

        }
        if (deleteStatus >= 1) {
            out.print(deleteStatus);
            log.info("successfully deleted");
        }
        else {
            out.print(deleteStatus);
            log.warn("");
        }
    }


}
