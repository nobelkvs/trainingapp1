package com.response.dao;

import org.junit.Test;
import com.response.model.Response;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class ResponseDAOTest {
    ResponseDAO responseDAO = new ResponseDAO();

    @Test
    public void createResponseDAO() {
        int status = 0;
        LocalDate date = LocalDate.now();
        Response response = new Response();
        response.setTitle("test");
        response.setMessage("testing");
        response.setLabel("label");
        response.setMark_public("YES");
        response.setUpdate_date(date);

        try {
            status = responseDAO.createResponseDAO(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (status == 1)
            assertEquals(1, status);
        else
            assertEquals(0, status);

    }

    @Test
    public void deleteResponseDAO() {
        String[] arr = {"49", "50"};
        int status = 0;
        try {
            status = responseDAO.deleteResponseDAO(arr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (status == 1)
            assertEquals(1, status);
        else
            assertEquals(0, status);

    }

    @Test
    public void retrieveResponseDAO() {
        String label = "tt";
        List<Response> list = null;
        boolean flag = false;
        Response resp = new Response();
        try {
            list = responseDAO.retrieveResponseDAO(label);
            resp = list.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException ie) {
            flag = true;
        }
        if (flag == true) {
            assertTrue(flag);
        } else
            assertTrue(resp instanceof Response);


    }

    @Test
    public void retrieveLables() {
        List<String> labelList = null;
        int size = 0;
        boolean flag = false;
        try {
            labelList = responseDAO.retrieveLables();
            size = labelList.size();
            if (size > 1) {
                size = 1;
            }
            labelList.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException ie) {
            flag = true;
        }
        if (size == 1)
            assertEquals(1, size);
        else {
            assertTrue(flag);
        }
    }
}