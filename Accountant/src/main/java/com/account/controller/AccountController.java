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
    static final Logger log=Logger.getLogger(AccountController.class);


/**
* @param req for creating the request
* @param resp for getting the responce
* @throws IOException
* @return status for confirmation of the operation
 */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int status = 0;
        PrintWriter out = res.getWriter();

        //getting the parameters which are passed by the user 
        String Fname = req.getParameter("Fname");
        log.info(Fname);

String Lname=req.getParameter("Lname");
        log.info(Lname);
String Bankname=req.getParameter("Bankname");
String Branch=req.getParameter("Branch");
String Address=req.getParameter("Address");
int Phone =Integer.parseInt( req.getParameter("Phone"));

        // Setting te parameter
        Account account=new Account();
        account.setFname(Fname);
        account.setLname(Lname);
        account.setBankname(Bankname);
        account.setBranch(Branch);
        account.setAddress(Address);
        account.setPhone(Phone);
        AccountService aservice=new AccountServiceImpl();
        try {

            // Using the service method to insert the data in the database
            status = aservice.createAccountService(account);
            log.info(account);
        } catch (SQLException e) {
            System.out.print(e);
            out.println(e);
            e.printStackTrace();
        }

        try {

            // Checking t for the status 
            if (status >= 1) 
            out.print(status);
            else 
            out.print(status);
        } 
        catch (Exception e) {
            out.println(e);
            e.printStackTrace();
        }


    }

    /**
    * @var Fname is use to retrieve the data
    * using toJson() method to convert the list into Json format 
    * @throw ServletException , IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Getting the parameter
        String Fname= req.getParameter("Fname");

        // Creating the list
        List<Account> list = new ArrayList<Account>();
        PrintWriter out = resp.getWriter();
        AccountService accountService=new AccountServiceImpl();
        try {
            Gson gson = new Gson();

            // Retrieving the details from database
            list=accountService.retriveByName(Fname);

            // Getting the list in Key value pair using json object
            String json = gson.toJson(list);
            
        }
        catch (Exception e ){
            e.printStackTrace();
            
        }

    }

    /**
    * @var using Id for deleting the particular account
    * @throw ServletException , IOException
    * @return using deletestatus for confirmation of operation
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter out=resp.getWriter();
        int deleteStatus = 0;
        
        // Fetching the parameter entered 
        String Fname = req.getParameter("Id");

        // If there are multiple parameters which are seperated by commmas it will split this 
        String multiple[] = Fname.split(",");

        AccountService accountService=new AccountServiceImpl();
        try {

            // Call the delete method ----------------------------
            deleteStatus =  accountService.deleteAccount(multiple);
        } catch (SQLException e) {
            e.printStackTrace();
            out.println(e);
        }
        if(deleteStatus>=1)
            out.print(deleteStatus);
        else
            out.print(deleteStatus);
    }


}
