package Service;
import Model.PolicyModel;
import java.util.List;

/**
 * This is PolicyService interface which has the create, retrive, delete abstract methods.
 */
public interface PolicyService {
    int createPolicy(PolicyModel policyModelObj);
    PolicyModel retrivePolicy(String email);
    int deletePolicy(int id);
    int updateAddress( String email, int id);
    List<PolicyModel> retriveAllPolicies();
}
