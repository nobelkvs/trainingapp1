package DAO;

import Model.PolicyModel;
import Utility.DBConnection;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the PolicyDAO class which has three methods to create, retrieve and to delete a policy.
 * When a method is activated from this class it connects to the database and makes the changes.
 */
public class PolicyDAO {
	static final Logger log = Logger.getLogger(PolicyDAO.class);
	Connection con = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	/**
	 * This is a create policy method which receives the policy model object
	 * and insert the values of that object to policy table.
	 * @param policyModelObj
	 * @return returns a integer value i.e id of the policy
	 */
	public int createPolicy(PolicyModel policyModelObj) {
		log.info("Inside create policy method in policy dao");
		final String QUERY = "insert into policy (policy_amount,policy_holdername,address,email) VALUES(?,?,?,?)";
		//Getting the Connection
		con = DBConnection.getConnection();
		int id = 0;
		try {
			pst = con.prepareStatement(QUERY);
			//Setting the values to the query parameters
			pst.setLong(1, policyModelObj.getPolicyAmount());
			pst.setString(2, policyModelObj.getPolicyHolderName());
			pst.setString(3, policyModelObj.getAddress());
			pst.setString(4, policyModelObj.getEmail());
			pst.executeUpdate();
			//Getting the auto generated values
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (SQLException e) {
			log.error(e);
			String exception= e.toString();
			if(exception.contains("Duplicate")) {
				id = -1;
			}else {
				id = -2;
			}
		}
		return id;
	}

	/**
	 * This is a retrivePolicy method which receives the email id as a string.
	 * it returns the policy that matches the email id in the table
	 * @param email
	 * @return PolicyModel object 
	 */

	public PolicyModel retrivePolicy(String email) {
		log.info("Inside retrieve policy method in policy dao");
		final String QUERY = "select * from Policy_schema.policy where email = ?";
		PolicyModel policyModelObj = new PolicyModel();
		con = DBConnection.getConnection();
		try {
			pst = con.prepareStatement(QUERY);
			pst.setString(1,email);
			rs = pst.executeQuery();
			if (rs.next()) {
				policyModelObj.setPolicyId(rs.getInt(1));
				policyModelObj.setPolicyAmount((long) rs.getInt(2));
				policyModelObj.setPolicyHolderName(rs.getString(3));
				policyModelObj.setAddress(rs.getString(4));
				policyModelObj.setEmail(rs.getString(5));
			}
		} catch (SQLException e) {
			policyModelObj = null;
			log.error(e);
		}

		return policyModelObj;
	}

	/**
	 * This is a deletePolicy method which receives the policy id and delete that policy information from table.
	 * @param id
	 * @return status
	 */
	public int deletePolicy(int id) {
		log.info("Inside delete policy method in policy dao");
		final String QUERY = "delete from policy where policy_id = ?";
		con = DBConnection.getConnection();
		int status = 0;
		try {
			pst = con.prepareStatement(QUERY);
			pst.setInt(1, id);
			status = pst.executeUpdate();

		} catch (SQLException e) {
			status = -1;
			log.error(e);
		}
		return status;
	}

	/**
	 * This is the retrive all policies method which gives list of all the policies in the table.
	 * @return policiesList
	 */
	public List<PolicyModel> retriveAllPolicies(){
		log.info("Inside retrieve all policy method in policy dao");
		final String QUERY = "select * from policy";
		con = DBConnection.getConnection();
		List <PolicyModel> policiesList = new ArrayList<PolicyModel>();
		try {
			pst = con.prepareStatement(QUERY);
			rs = pst.executeQuery();
			while(rs.next()){
				PolicyModel policyModelObj = new PolicyModel();
				policyModelObj.setPolicyId(rs.getInt(1));
				policyModelObj.setPolicyAmount((long) rs.getInt(2));
				policyModelObj.setPolicyHolderName(rs.getString(3));
				policyModelObj.setAddress(rs.getString(4));
				policyModelObj.setEmail(rs.getString(5));
				policiesList.add(policyModelObj);
			}
		} catch (SQLException e) {
			policiesList = null;
			log.error(e);
		}
		return policiesList;
	}

	/**
	 * this is the update policy method we can update the address of a particular policy.
	 * @param address
	 * @return status
	 */
	public int updatePolicy(String address,int id){
		log.info("Inside update policy method in policy dao");
		final String QUERY = "update policy set address = ? where policy_id = ?";
		con = DBConnection.getConnection();
		int status = 0;
		try {
			pst = con.prepareStatement(QUERY);
			pst.setString(1,address);
			pst.setInt(2,id);
			status = pst.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
		return status;
	}

}
