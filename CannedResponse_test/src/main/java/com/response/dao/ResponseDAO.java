package com.response.dao;

import com.response.dbconnection.DBConnection;
import com.response.model.Response;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * ResponseDAO Class for Creating,Retrieving , Deleting from Database
 * Only DB Logic
 *
 * @var SQL_INSERT_INTO_RESPONSE for Inserting Response Data To Database
 * @var SQL_DELETE_BY_TITLE for a deleting a Response entry from Database Based on Response ID
 * @var SQL_GET_BY_LABEL for Getting a List Rows from database based on label(Relted to Response )
 */
public class ResponseDAO implements ResponseDAOInterface {
    static final Logger log = Logger.getLogger(ResponseDAO.class);
    private static final String SQL_INSERT_INTO_RESPONSE = "insert into Response(title,message,label,mark_public,updated_date) values(?,?,?,?,?)";
    private static final String SQL_DELETE_BY_TITLE = "delete from Response where id_response = ?";
    private static final String SQL_GET_BY_LABEL = "select * from Response where label = ?";
    private static final String SQL_GET_LABELS = "select DISTINCT label from Response order by label";
    private static final String SQL_GET_BY_LABEL_MARKED = "select * from Response where label =? and mark_public = ?";
    /**
     * DBConnetion Class have a method for creating a connection with Database
     */
    DBConnection dbConnection = new DBConnection();

    /**
     * @param response
     * @return
     * @throws SQLException
     * @var insertStatus contains postive int values if data inserted into database successfully else negative value
     */

    @Override
    public int createResponseDAO(Response response) throws SQLException {
        Connection con = null;
        int insertStatus = 0;
        try {
            con = dbConnection.getDBConnection();
        } catch (Exception e) {
            log.error("Can not Connect to data" + e);
        }
        PreparedStatement ps = null;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_INSERT_INTO_RESPONSE);
            ps.setString(1, response.getTitle());
            ps.setString(2, response.getMessage());
            ps.setString(3, response.getLabel());
            ps.setString(4, response.getMark_public());
            ps.setDate(5, Date.valueOf(response.getUpdate_date()));
            insertStatus = ps.executeUpdate();
            con.commit();
            log.info("Values are set to statement  from CreateResponse");
        } catch (Exception e) {
            log.error(e);
            try {
                con.rollback();
            } catch (SQLException e1) {

                log.info(e1);
            }
        }
        log.info(insertStatus);
        return insertStatus;

    }

    /**
     * @param delete_id
     * @return
     * @throws SQLException
     * @var deleteStatus holds postive int values if row deleted from database successfully else it holds a negative value
     */

    @Override
    public int deleteResponseDAO(String[] delete_id) throws SQLException {
        Connection con = null;
        int deleteStatus = 0;
        try {
            con = dbConnection.getDBConnection();
        } catch (Exception e) {
            log.error(e);
        }
        PreparedStatement ps = null;
        try {
            for (int i = 0; i < delete_id.length; i++) {
                ps = con.prepareStatement(SQL_DELETE_BY_TITLE);

                ps.setInt(1, Integer.parseInt(delete_id[i]));

                deleteStatus = ps.executeUpdate();
                log.info("Delete Status from DAO" + deleteStatus);
            }
        } catch (Exception e) {
            log.error(e);
            try {
                con.rollback();
            } catch (SQLException e1) {

                log.info(e1);
            }
        }


        return deleteStatus;
    }

    /**
     * @param label
     * @return
     * @throws SQLException
     * @var listResponses holds the retrieved data from Database on a Label
     */
    @Override
    public List<Response> retrieveResponseDAO(String label) throws SQLException {

        List<Response> listResponses = new ArrayList<Response>();
        Connection con = null;
        try {
            con = dbConnection.getDBConnection();
            } catch (Exception e) {
                log.info("Can not Connect" + e);
            }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            log.info("this is try block -- dao");
            // con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_GET_BY_LABEL);
            log.info(SQL_GET_BY_LABEL);
            ps.setString(1, label);
            rs = (ResultSet) ps.executeQuery();
            log.info(rs);
            while (rs.next()) {

                Response response = new Response();
                response.setResponse_id(rs.getInt(1));
                response.setTitle(rs.getString(2));
                response.setMessage(rs.getString(3));
                response.setLabel(rs.getString(4));
                response.setMark_public(rs.getString(5));
                response.setUpdate_date(LocalDate.parse(rs.getString(6)));

                listResponses.add(response);
            }

        } catch (Exception e) {
            log.error(e);
        }
        log.info("this is from Dao retrieve labels ");
        log.info(listResponses);
        return listResponses;

    }

    @Override
    public List<String> retrieveLables() throws SQLException {
        List<String> listLables = new ArrayList<String>();
        Connection con = null;
        try {
            con = dbConnection.getDBConnection();
        } catch (Exception e) {
            log.info("Can not Connect" + e);
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_GET_LABELS);
            rs = (ResultSet) ps.executeQuery();
            while (rs.next()) {

                listLables.add(rs.getString(1));
            }

        } catch (Exception e) {
            log.error(e);
        }
        log.info("this is the list of lables");
        log.info(listLables);
        return listLables;
    }

    @Override
    public List<Response> retrieveResponseDAOMarkPublic(String label) throws SQLException {
        List<Response> listResponses = new ArrayList<Response>();
        log.info(label);
        Connection con = null;
        String mark_public;
        try {
            con = dbConnection.getDBConnection();
        } catch (Exception e) {
            log.info("Can not Connect" + e);
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            log.info("this is try block -- dao");
            // con.setAutoCommit(false);
            ps = con.prepareStatement(SQL_GET_BY_LABEL_MARKED);
            //log.info(SQL_GET_BY_LABEL);
            mark_public = "YES";
            ps.setString(1, label);
            ps.setString(2,mark_public);
            rs = (ResultSet) ps.executeQuery();
            log.info(rs);
            while (rs.next()) {

                Response response = new Response();
                response.setResponse_id(rs.getInt(1));
                response.setTitle(rs.getString(2));
                response.setMessage(rs.getString(3));
                response.setLabel(rs.getString(4));
                response.setMark_public(rs.getString(5));
                response.setUpdate_date(LocalDate.parse(rs.getString(6)));

                listResponses.add(response);
            }
            log.info("this is responses marked public");
            log.info(listResponses);

        } catch (Exception e) {
            log.error(e);
        }
        log.info("this is from Dao retrieve labels ");
        log.info(listResponses);
        return listResponses;

    }


}
