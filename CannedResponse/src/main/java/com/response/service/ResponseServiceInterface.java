package com.response.service;
import com.response.model.*;

import java.sql.SQLException;
import java.util.List;

/**
 * ResponseServiceInterface have create,delete and retrieve service abstract methods
 */
public interface ResponseServiceInterface{
     int createResponseService(Response response) throws SQLException;
     int deleteResponseService(String[] delete_id) throws SQLException;
     List<Response> retrieveResponseService(String label) throws SQLException;
     List<Response> retrieveResponseServiceMarkPublic(String label) throws SQLException;
}
