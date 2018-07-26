package Service;

import DAO.PolicyDAO;
import Model.PolicyModel;

import java.util.List;

import org.apache.log4j.Logger;

/**
 * This is PolicyServiceImpl class which implements PolicyService.
 */
public class PolicyServiceImpl implements PolicyService {
	
	static final Logger log = Logger.getLogger(PolicyDAO.class);
    PolicyDAO policyDaoObj = new PolicyDAO();

    /**
     * This is  createPolicy method which calls the createPolicy method in the PolicyDao.
     * @param policyModelObj
     * @return an int value i.e status of the policy
     */
    public int createPolicy(PolicyModel policyModelObj) {
    	log.info("Inside create policy method in policy service implementation");
        int status = policyDaoObj.createPolicy(policyModelObj);
        return status;
    }

    /**
     * This is  retrivePolicy method which calls the retrivPolicy method in the PolicyDao.
     * @param email
     * @return policyModelObj 
     */
    public PolicyModel retrivePolicy(String email) {
    	log.info("Inside retrieve policy method in policy service implementation");
        PolicyModel policyModelObj = policyDaoObj.retrivePolicy(email);
        return policyModelObj;
    }

    /**
     * This is  deletePolicy method which calls the deletePolicy method in the PolicyDao.
     * @param id
     * @return status 
     */
    public int deletePolicy(int id) {
    	log.info("Inside delete policy method in policy service implementation");
        int status = policyDaoObj.deletePolicy(id);
        return status;
    }

    /**
     * This is the update address method which calls the update policy method in the policyDao.
     * @param email
     * @param id
     * @return status
     */
    public int updateAddress( String email, int id){
    	log.info("Inside update policy method in policy service implementation");
        int status = policyDaoObj.updatePolicy(email,id);
        return status;
    }

    /**
     * This is the retrieve all policies method which calls the retrieve all policies method in the policyDao.
     * @return policiesList
     */
    public List<PolicyModel> retriveAllPolicies(){
    	log.info("Inside retrieve all policcies method in policy service implementation");
        List<PolicyModel> policiesList = policyDaoObj.retriveAllPolicies();
        return policiesList;
    }
}
