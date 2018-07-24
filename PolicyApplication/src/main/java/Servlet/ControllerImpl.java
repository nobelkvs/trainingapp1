package Servlet;

import DAO.PolicyDAO;
import Model.PolicyModel;
import Service.PolicyService;
import Service.PolicyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This is a controllerImpl class which receives the request and response from the controller
 * and acts based on the request.
 */

public class ControllerImpl  {

    PolicyService policyServiceObj = new PolicyServiceImpl();

    /**
     * This is a createPolicy method which receives the create request and gives corresponding response back.
     * @param req
     * @param res
     * @return  returns an integer value of status.
     * @throws ServletException
     */
    public int createPolicy(HttpServletRequest req, HttpServletResponse res)throws ServletException {

        Long policyAmount = Long.parseLong(req.getParameter("policyAmount"));
        String policyHolderName = req.getParameter("policyHolderName");
        String address = req.getParameter("address");
        String email = req.getParameter("email");

        PolicyModel policyModelObj = new PolicyModel();
        policyModelObj.setPolicyAmount(policyAmount);
        policyModelObj.setPolicyHolderName(policyHolderName);
        policyModelObj.setAddress(address);
        policyModelObj.setEmail(email);

        int status = policyServiceObj.createPolicy(policyModelObj);
        return status;
    }

    /**
     * This is retrivePolicy method which receives the retrieve request and gives corresponding response back.
     * @param req
     * @param res
     * @return it returns a policyModelObj
     * @throws ServletException
     */
    public PolicyModel retrivePolicy (HttpServletRequest req, HttpServletResponse res)throws ServletException {

        String email = req.getParameter("email");

        PolicyModel policyModelobj = policyServiceObj.retrivePolicy(email);
        return policyModelobj;
    }

    /**
     * This is deletePolicy method which receives delete request and delete that policy and gives the response back.
     * @param req
     * @param res
     * @return it returns an integer value of the delete status.
     * @throws ServletException
     */
    public int deletePolicy (HttpServletRequest req, HttpServletResponse res)throws ServletException {

        String ids = req.getParameter("ids");
        String [] policyIds = ids.split(",");
        int status = 0;
        for(String s : policyIds){
        	int id = Integer.parseInt(s);
        	status += policyServiceObj.deletePolicy(id);
        }
        return status;
    }

    /**
     * This is the updatePolicy method which receives update request and update that policy and gives the response back.
     * @param req
     * @param res
     * @return returns the integer value of the status.
     * @throws ServletException
     */
    public int updatePolicy (HttpServletRequest req, HttpServletResponse res)throws ServletException {

        int id = Integer.parseInt(req.getParameter("id"));
        String address = req.getParameter("address");
        int status = policyServiceObj.updateAddress(address,id);
        return status;
    }

    /**
     * This is the retriveAllPolicies method which receives request and gives all the policies as a response.
     * @param req
     * @param res
     * @return it returns the list of policyModelObj
     * @throws ServletException
     */
    public List<PolicyModel> retriveAllPolicies (HttpServletRequest req, HttpServletResponse res)throws ServletException {

        List<PolicyModel> policiesList = policyServiceObj.retriveAllPolicies();
        return policiesList;
    }

}
