package DAO;

import Model.PolicyModel;

import static org.junit.Assert.*;

public class PolicyDAOTest {

    PolicyDAO policyDaoObj = new PolicyDAO();
    @org.junit.Test(expected = NullPointerException.class)
    public void createPolicy() {

        PolicyModel policyModelObj = new PolicyModel();
        policyModelObj.setEmail("email");
        policyModelObj.setAddress("Address");
        policyModelObj.setPolicyHolderName("name");
        policyDaoObj.createPolicy(policyModelObj);

    }

    @org.junit.Test
    public void retrivePolicy() {
        PolicyModel policy = policyDaoObj.retrivePolicy("");
        assertNull(policy.getPolicyId());
    }

    @org.junit.Test
    public void deletePolicy() {
        int x = policyDaoObj.deletePolicy(0);
        assertEquals(0,0);
    }

    @org.junit.Test
    public void updatePolicy() {
        policyDaoObj.updatePolicy("",0);
        assertEquals(0,0);
    }
}