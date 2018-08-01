package com.bank.controller;
import com.google.gson.Gson;
import com.bank.model.BankModel;
import com.bank.service.BankService;
import com.bank.service.BankServiceImpl;
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

public class BankControllerServlet extends HttpServlet {
    // Creating the instance of Logger
    static final Logger log=Logger.getLogger(BankControllerServlet.class);

    /**
     *
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
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
        BankModel bm=new BankModel();
        // Binding the values into the object
        bm.setName(name);
        bm.setBranch(branch);
        bm.setAddress(address);
        bm.setPhno(phno);
        bm.setEmail(email);
        // Create an object for BankServiceImpl
        BankService bs=new BankServiceImpl();
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

    /**
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    // Retrieve operation for Bank Application using Get method
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // The request.getParameter() method here is used to retrieve the input values
        String branch = req.getParameter("branch");

        List<BankModel> list = new ArrayList<BankModel>();
        // Enables you to write formatted data to text-output stream
        PrintWriter out = resp.getWriter();
        // Create an object for BankServiceImpl
        BankService bs =  new BankServiceImpl();
        try {
            list = bs.retrivebybname(branch);
            // Gson is used to convert Java Objects into their JSON representation
            Gson gson=new Gson();
            String json=gson.toJson(list);
            out.print(json);

        }catch (Exception e ){
            // This method helps to trace the exception.
            e.printStackTrace();

        }
    }

    /**
     * Delete operation for Bank Application using Delete method
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Enables you to write formatted data to text-output stream
        PrintWriter out=resp.getWriter();
        // Initialize 0 to deleteStatus
        int deleteStatus = 0;
        Integer phno = Integer.valueOf(req.getParameter("phno"));
        // Create an object for service
        BankService bs = new BankServiceImpl();
        try {
            deleteStatus =  bs.deletebankservice(phno);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String json = gson.toJson(deleteStatus);
        //out.println(json);
        // This if-else block is used to check whether data is inserted successfully or not
        if(deleteStatus>=1) {
            out.println(1);
            log.info("successfully deleted" + deleteStatus);
        }
        else
            log.info("failed to delete"+deleteStatus);
    }
}
