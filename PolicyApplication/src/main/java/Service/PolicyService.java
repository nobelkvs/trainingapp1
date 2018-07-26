package Service;
import Model.PolicyModel;
import java.util.List;

import org.apache.log4j.Logger;

import DAO.PolicyDAO;

/**
 * This is PolicyService interface which has the create, retrieve, delete abstract methods.
 * It has 4 unimplemented methods.
 */
public interface PolicyService {
	
    int createPolicy(PolicyModel policyModelObj);
    PolicyModel retrivePolicy(String email);
    int deletePolicy(int id);
    int updateAddress( String email, int id);
    List<PolicyModel> retriveAllPolicies();
}
