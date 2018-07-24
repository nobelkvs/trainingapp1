package Servlet;

import Model.PolicyModel;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This is the Controller which acts as a servlet.
 * It receives the request from the user and gives the response back.
 */
public class Controller extends HttpServlet {

    /**
     * doPost method get all the requests from the user and activates corresponding method based on the action parameter
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String action = req.getParameter("action");
        ControllerImpl controllerImplObj = new ControllerImpl();
        if (action.equals("create")) {
            int status = controllerImplObj.createPolicy(req, res);
            if(status > 0){
                out.println("Policy is created successfully your policy Id is : " + status);
            }else{
                out.println("Policy is not created, please try again...");
            }
        } else if (action.equals("retrive")) {
            PolicyModel policy = controllerImplObj.retrivePolicy(req, res);
            Gson gson = new Gson();
            String obj = gson.toJson(policy);
            out.println(obj);

        } else if (action.equals("delete")) {
            int status = controllerImplObj.deletePolicy(req, res);
            if(status > 0){
                out.println( status + "  Policies Deleted successfully");
            }else{
                out.println("Policy is not deleted, please try again...");
            }

        } else if(action.equals("retriveall")){
            List<PolicyModel> policiesList = controllerImplObj.retriveAllPolicies(req, res);
            Gson gson = new Gson();
            String obj = gson.toJson(policiesList);
            out.println(obj);
        } else if (action.equals("update")) {
            int status = controllerImplObj.updatePolicy(req, res);
            if(status > 0){
                out.println("updated successfully");
            }else{
                out.println("Policy is not updated, please try again...");
            }

        } else {
            out.println("Not able to do any operation please check the input.");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

}
