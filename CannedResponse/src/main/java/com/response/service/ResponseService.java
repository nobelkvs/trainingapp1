package com.response.service;

import com.response.model.Response;
import com.response.dao.ResponseDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * ResponseService Class for Sending to and from Database
 *
 * @see com.response.dao
 */

public class ResponseService implements ResponseServiceInterface {
    ResponseDAO responseDAO = new ResponseDAO();

    /**
     * @param response
     * @return
     * @throws SQLException createResponseService() to send data from Controller to DAO And to get Response from DAO
     * @var status holds the status which is returned from DAO
     */
    public int createResponseService(Response response) throws SQLException {
        int status = responseDAO.createResponseDAO(response);
        return status;
    }



    /**
     * @param delete_id
     * @return status
     * @throws SQLException deleteResponseService() to send response id to DAO To delete that Response and to get Response Back from DAO
     * @var status holds the status which is returned from DAO
     */
    @Override
    public int deleteResponseService(String[] delete_id) throws SQLException {
        int status = responseDAO.deleteResponseDAO(delete_id);

        return status;
    }

    /**
     * @param label
     * @return listResponse returns list of objects which are returned from DAO
     * @throws SQLException
     */

    @Override
    public List<Response> retrieveResponseService(String label) throws SQLException {
        List<Response> listResponse = responseDAO.retrieveResponseDAO(label);
        return listResponse;
    }

    @Override
    public List<Response> retrieveResponseServiceMarkPublic(String label) throws SQLException {
        List<Response> listResponseMarkPublic = responseDAO.retrieveResponseDAOMarkPublic(label);
        return listResponseMarkPublic;
    }
}
