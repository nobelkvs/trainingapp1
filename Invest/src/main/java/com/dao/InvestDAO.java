package com.dao;

import com.utils.MysqlConnection;
import com.model.Invest;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * InvestDAO class is class that interacts with database directly for creation, retrival, deletion of data
 * Only DB logics are written here
 * @var SQL_INSERT_INTO_INVEST for inserting a new investment data into database
 * @var SQL_DELETE_INVEST_BY_USER_ID for deletion of data on given USER ID
 * @var SQL_GET_INVEST_BY_First_Name for retrival of data form database based on First Name
 */
public class InvestDAO implements InvestDAOint {
    private static final String SQL_INSERT_INTO_INVEST = "insert into User(First_Name,Last_Name,Principal,Annual_rate,No_years,Periods) values(?,?,?,?,?,?)";
    private static final String SQL_DELETE_INVEST_BY_USER_ID = "DELETE FROM User WHERE User_id=?";
    private static final String SQL_GET_INVEST_BY_First_Name = "SELECT * FROM User WHERE First_Name=?";
    private static final String SQL_GET_INVEST = "SELECT * FROM User";
    /**
     * MysqlConnection class is used to establish a connenction to database
     */
    MysqlConnection c = new MysqlConnection();
    /**
     * @var log is used for storing information depending on execution of class
     */
    static final Logger log=Logger.getLogger(InvestDAO.class);

    /**
     *
     * @param invest contains entire data
     * @return the status of insertion to the Service , if value is greater than or equal to 1 then data in inserted
     * or else data is not executed
     */
    public int createInvestDAO(Invest invest) {
        Connection conn = null;
        int insertStatus = 0;
        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.info("can not connect");
        }
        log.info("Connection id" + conn);
        //prepare statement
        PreparedStatement ps = null;
        try {

            conn.setAutoCommit(false);
            ps = conn.prepareStatement(SQL_INSERT_INTO_INVEST);
            ps.setString(1,invest.getFirst_Name());
            ps.setString(2, invest.getLast_Name());
            ps.setInt(3, invest.getPrincipal());
            ps.setInt(4, invest.getAnnual_rate());
            ps.setInt(5, invest.getNo_years());
            ps.setInt(6,invest.getPeriods());
            insertStatus = ps.executeUpdate();
            conn.commit();
        } catch (Exception e1) {
            log.error(e1);
            e1.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e2) {
                log.error(e2);
                log.error("under DAO");
                e2.printStackTrace();
            }
        }
        return insertStatus;
    }

    /**
     * @param User_id represents the id which is to be deleted
     * @return the status of deletion, if value is greater than or equal to 1, then User_id is deleted
     * else not deleted
     */
    public int deleteInvestDAO(int User_id)  {
        // get the Connection
        Connection conn = null;
        int deleteStatus = 0;
        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.error("can not connect");
        }
        System.out.print(conn);
        PreparedStatement ps = null;
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(SQL_DELETE_INVEST_BY_USER_ID);
            ps.setInt(1, User_id);
            deleteStatus = ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            log.info(e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                log.info("under DAO...");
                e1.printStackTrace();
            }
        }
        return deleteStatus;
    }

    /**
     * @param First_name represents the First Name based on which retrival should be done
     * @return the entire list based on given name to Service class
     */

    public List<Invest> retrieveByFirstDAO(String First_name)  {

        List<Invest> investList = new ArrayList<Invest>();
        // get the Connection
        Connection conn = null;
        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.info("can not connect");
        }
        System.out.print(conn);
        //prepare statement
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_GET_INVEST_BY_First_Name);
        ps.setString(1, First_name);
            rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                Invest tc = new Invest();
                tc.setUser_id(rs.getInt(1));
                tc.setFirst_Name(rs.getString(2));
                tc.setLast_Name(rs.getString(3));
                tc.setPrincipal(rs.getInt(4));
                tc.setAnnual_rate(rs.getInt(5));
                tc.setNo_years(rs.getInt(6));
                tc.setPeriods(rs.getInt(7));
                investList.add(tc);
            }
            log.info(investList);
            return investList;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {

                log.error("under DAO...");
                e1.printStackTrace();
            }
        }
        return investList;

    }


    /**
     * It is used to get all the data and displays it in UI
     * @return
     */
    public List<Invest> retrieveDetailsDAO(){
        List<Invest> list_details = new ArrayList<Invest>();
        // get the Connection
        Connection conn = null;
        try {
            conn = c.getConnect();
        } catch (Exception e) {
            log.info("can not connect");
        }
        System.out.print(conn);
        //prepare statement
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(SQL_GET_INVEST);
            rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {
                Invest tc = new Invest();
                tc.setUser_id(rs.getInt(1));
                tc.setFirst_Name(rs.getString(2));
                tc.setLast_Name(rs.getString(3));
                tc.setPrincipal(rs.getInt(4));
                tc.setAnnual_rate(rs.getInt(5));
                tc.setNo_years(rs.getInt(6));
                tc.setPeriods(rs.getInt(7));
                list_details.add(tc);
            }
            log.info(list_details);
            return list_details;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {

                log.error("under DAO...");
                e1.printStackTrace();
            }
        }
        return list_details;
    }

}
