package com.response.dao;
import com.response.model.Response;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface DAO
 * Methods::
 * createResponseDAO for Creating a Respose
 * deleteResponseDAO for Deleting a Response from a DB
 * retrieveResponseDAO for Retrieveing list of rows
 */
public interface ResponseDAOInterface {
    int createResponseDAO(Response response) throws SQLException;
    int deleteResponseDAO(String[] delete_id) throws SQLException;
    List<Response> retrieveResponseDAO(String label) throws SQLException;
    List<String> retrieveLables() throws SQLException;
    List<Response> retrieveResponseDAOMarkPublic(String label) throws SQLException;
}
