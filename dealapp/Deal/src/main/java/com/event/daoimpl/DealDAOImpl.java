package com.event.daoimpl;

import com.event.dao.DealDAO;
import com.event.model.ModelDeal;
import com.event.util.Util;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealDAOImpl implements DealDAO {

    /* Sql querries for CURD operations */
    private static  final String CREATE_DEAL_QUERRY="insert into deal(deal_name,owner_name,deal_value,probability,customer_name,customer_contact) values(?,?,?,?,?,?)";
    private static  final String RETRIVE_DEAL_QUERRY="select deal_id,deal_name,owner_name,deal_value,probability,customer_name,customer_contact from deal where deal_name=?";
    private static final String DELETE_DEAL_QUERRY="delete from deal where deal_id=?";
    private static final String UPDATE_DEAL_DETAILS = "update deal set deal_value=?,probability=? where deal_id=?";

    /* Logger instance */
    static final Logger log=Logger.getLogger(DealDAOImpl.class);

    /* Initialising connection,preparedstatement and resultset objects with null values */
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    private int status;

    /**
     * This is CreateDAO method which connects to data base to insert data and return String type
     * @param deal
     * @return status
     */
    public String create(ModelDeal deal) {
            log.info("entered into create DAO");
        try {

            /* Getting SQL connection and prepared statement */
            con=Util.getConnected();
            pstmt=con.prepareStatement(CREATE_DEAL_QUERRY);

            /* Getting values */
           String dealname=deal.getDealName();
            String ownername=deal.getOwnerName();
            String dealvalue=deal.getDealValue();
            int probability=deal.getProbability();
            String customername=deal.getCustomerName();
            String customercontact=deal.getCustomerContact();

            /* Setting positional parameters */
            pstmt.setString(1,dealname);
            pstmt.setString(2,ownername);
            pstmt.setString(3,dealvalue);
            pstmt.setInt(4,probability);
            pstmt.setString(5,customername);
            pstmt.setString(6,customercontact);

            /* Executing querry */
            int status=pstmt.executeUpdate();
            log.info(status > 0?"deal created":"deal failed to create");

        } catch (SQLException e) {
            log.error("error in create DAO: "+ e);
        }

        return "****Deal created successfully****";
    }


    /**
     * This is a retriveDAO method which connects to data base to retrive data and returns List
     * @param name
     * @return List
     */
    @Override
    public List<ModelDeal> retrive(String name) {
        log.info("entered into retrive DAO");

        /* List declaration */
        List list=new ArrayList();
        try {
            /* Getting SQL connection and prepared statement */
            con=Util.getConnected();
            pstmt=con.prepareStatement(RETRIVE_DEAL_QUERRY);

            /* Setting positional parameters */
            pstmt.setString(1,name);

            /* Executing querry */
            rs=pstmt.executeQuery();

            /* Retriving results from resultset object and adding to list */
            while(rs.next()){
                ModelDeal deal=new ModelDeal();
                deal.setDealId(rs.getInt("deal_id"));
                deal.setDealName(rs.getString("deal_name"));
                deal.setOwnerName(rs.getString("owner_name"));
                deal.setDealValue(rs.getString("deal_value"));
                deal.setProbability(rs.getInt("probability"));
                deal.setCustomerName(rs.getString("customer_name"));
                deal.setCustomerContact(rs.getString("customer_contact"));
                /* Adding deal object to List */
                list.add(deal);
            }
        } catch (SQLException e) {
            log.error("error in retrive DAO: "+ e);
        }
        return list;
    }


    /**
     * This method deleteDAO which connects to data base to delete data and return status
     * @param id
     * @return status
     */
    @Override
    public int delete(int id) {
        log.info("entered into delete DAO");
        try {
            /* Getting SQL connection and prepared statement */
            con = Util.getConnected();
            pstmt = con.prepareStatement(DELETE_DEAL_QUERRY);

            /* Setting positional parameters */
            pstmt.setInt(1,id);

            /* Executing querry */
             status=pstmt.executeUpdate();
            log.info(status > 0?"deal Deleted":"deal failed to Delete");

        }catch (SQLException e){
            log.error("error in delete DAO: "+ e);
        }

        return status;
    }


    /**
     * This method updateDAO which connects to data base to update data and return status
     * @param deal
     * @return status
     */
    @Override
    public int update(ModelDeal deal) {
        log.info("entered into update DAO");
        try {
            /* Getting SQL connection and prepared statement */
            con = Util.getConnected();
            pstmt = con.prepareStatement(UPDATE_DEAL_DETAILS);
            String dealValue = deal.getDealValue();
            int probability = deal.getProbability();
            int dealId = deal.getDealId();

            /* Setting positional parameters */
            pstmt.setString(1, dealValue);
            pstmt.setInt(2, probability);
            pstmt.setInt(3, dealId);

            /* Executing querry */
            status = pstmt.executeUpdate();
            log.info(status > 0?"deal Updated":"deal failed to Update");

        } catch (SQLException e) {
            log.error("error in update DAO: " + e);
        }
        return status;
    }
}
