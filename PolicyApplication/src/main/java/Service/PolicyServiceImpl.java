package Service;

import DAO.PolicyDAO;
import Model.PolicyModel;

import java.util.List;

/**
 * This is PolicyServiceImpl class which implements PolicyService.
 */
public class PolicyServiceImpl implements PolicyService {

    PolicyDAO policyDaoObj = new PolicyDAO();

    /**
     * This is  createPolicy method which calls the createPolicy method in the PolicyDao.
     * @param policyModelObj
     * @return
     */
    public int createPolicy(PolicyModel policyModelObj) {
        int status = policyDaoObj.createPolicy(policyModelObj);
        return status;
    }

    /**
     * This is  retrivePolicy method which calls the retrivPolicy method in the PolicyDao.
     * @param email
     * @return
     */
    public PolicyModel retrivePolicy(String email) {
        PolicyModel policyModelObj = policyDaoObj.retrivePolicy(email);
        return policyModelObj;
    }

    /**
     * This is  deletePolicy method which calls the deletePolicy method in the PolicyDao.
     * @param id
     * @return
     */
    public int deletePolicy(int id) {
        int status = policyDaoObj.deletePolicy(id);
        return status;
    }

    /**
     * This is the update address method which calls the update policy method in the policyDao.
     * @param email
     * @param id
     * @return
     */
    public int updateAddress( String email, int id){
        int status = policyDaoObj.updatePolicy(email,id);
        return status;
    }

    /**
     * This is the retrive all policies method which calls the retrive all policies method in the policyDao.
     * @return
     */
    public List<PolicyModel> retriveAllPolicies(){
        List<PolicyModel> policiesList = policyDaoObj.retriveAllPolicies();
        return policiesList;
    }
}
