package com.event.daoimpl;

import com.event.dao.DAODeal;
import com.event.model.ModelDeal;
import com.event.util.Util;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAODealImpl implements DAODeal {
    //sql querries for CURD operations
    private static  final String CREATE_DEAL_QUERRY="insert into deal(deal_name,owner_name,deal_value,probability,customer_name,customer_contact) values(?,?,?,?,?,?)";
    private static  final String RETRIVE_DEAL_QUERRY="select deal_id,deal_name,owner_name,deal_value,probability,customer_name,customer_contact from deal where deal_name=?";
    private static final String DELETE_DEAL_QUERRY="delete from deal where deal_id=?";
    private static final String UPDATE_DEAL_DETAILS = "update deal set deal_value=?,probability=? where deal_id=?";
    //logger instance
    static final Logger log=Logger.getLogger(DAODealImpl.class);
    //initialising connection,preparedstatement and resultset objects with null values
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    private int i;

    //create deal method returns a string mesg
    public String createDAO(ModelDeal deal) {
            log.info("entered into create DAO");
        try {

            //getting SQL connection and prepared statement
            con=Util.getConnected();
            pstmt=con.prepareStatement(CREATE_DEAL_QUERRY);

            //getting values
           String dealname=deal.getDealName();
            String ownername=deal.getOwnerName();
            String dealvalue=deal.getDealValue();
            int probability=deal.getProbability();
            String customername=deal.getCustomerName();
            String customercontact=deal.getCustomerContact();

            //setting positional parameters
            pstmt.setString(1,dealname);
            pstmt.setString(2,ownername);
            pstmt.setString(3,dealvalue);
            pstmt.setInt(4,probability);
            pstmt.setString(5,customername);
            pstmt.setString(6,customercontact);

            //executing querry
            int i=pstmt.executeUpdate();
            if(i > 0){
                log.info("deal created");
            }else{
                log.info("deal failed to create");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("error in create DAO: "+ e);
        }

        return "****Deal created successfully****";
    }


    //retriving deal method returns list
    @Override
    public List<ModelDeal> retriveDAO(String name) {
        log.info("entered into retrive DAO");
        List list=new ArrayList();
        try {
            //getting SQL connection and prepared statement
            con=Util.getConnected();
            pstmt=con.prepareStatement(RETRIVE_DEAL_QUERRY);

            //setting positional parameters
            pstmt.setString(1,name);
            rs=pstmt.executeQuery();

            //retriving results from resultset object and adding to list
            while(rs.next()){
                ModelDeal deal=new ModelDeal();
                deal.setDealId(rs.getInt("deal_id"));
                deal.setDealName(rs.getString("deal_name"));
                deal.setOwnerName(rs.getString("owner_name"));
                deal.setDealValue(rs.getString("deal_value"));
                deal.setProbability(rs.getInt("probability"));
                deal.setCustomerName(rs.getString("customer_name"));
                deal.setCustomerContact(rs.getString("customer_contact"));
                list.add(deal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("error in retrive DAO: "+ e);
        }
        return list;
    }


    //deleting deal method returns string mesg
    @Override
    public int deleteDAO(int id) {
        log.info("entered into delete DAO");
        try {
            //getting SQL connection and prepared statement
            con = Util.getConnected();
            pstmt = con.prepareStatement(DELETE_DEAL_QUERRY);

            //setting positional parameters
            pstmt.setInt(1,id);
             i=pstmt.executeUpdate();


        }catch (SQLException e){
            log.error("error in delete DAO: "+ e);
        }

        return i;
    }


    //updating deal method returns string mesg
    @Override
    public int updateDAO(ModelDeal deal) {
        String mesg = null;
        log.info("entered into update DAO");
        int i = 0;
        try {
            //getting SQL connection and prepared statement
            con = Util.getConnected();
            pstmt = con.prepareStatement(UPDATE_DEAL_DETAILS);
            String dealValue = deal.getDealValue();
            int probability = deal.getProbability();
            int dealId = deal.getDealId();

            //setting positional parameters
            pstmt.setString(1, dealValue);
            pstmt.setInt(2, probability);
            pstmt.setInt(3, dealId);
            i = pstmt.executeUpdate();


        } catch (SQLException e) {
            log.error("error in update DAO: " + e);
        }
        return i;
    }
}
