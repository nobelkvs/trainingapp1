package com.bookOrder.dao;

import com.bookOrder.controller.OrderController;
import com.bookOrder.model.Order;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class OrderDAOImplTest extends OrderController {

    // Initialize log4j
    static final Logger log = Logger.getLogger(OrderController.class);

    OrderDAOImpl odi = new OrderDAOImpl();

    // Test case to test delete method
    @Test
    public void testDeleteOrder() {

        int orderId = 8;

        try {
            int deleteStatus = odi.deleteOrder(orderId);
            assertEquals(0, deleteStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    // Junit test case to test create

    /**
     * @throws SQLException
     */
    @Test
    public void testCreateOrder() throws SQLException {
        Order o = new Order();
        o.setOrderDate("2018-08-28");
        o.setOrderBy("nikita");
        o.setBookId(5);
        o.setQuantity(16);
        int insertStatus = odi.createOrder(o);
        assertEquals(1, insertStatus);

    }

    // Junit test case to test retrieve method

    /**
     * @throws SQLException
     */
    @Test
    public void testGetOrder() throws SQLException {

        String authorName = "forouzan";
        List li = odi.getOrder(authorName);
        log.info(li);

    }

    // Method to test retrieve

    /**
     * @throws SQLException
     */
    @Test
    public void testEmptyGetOrder() throws SQLException {
        String authorName = " ";
        List list = odi.getOrder(authorName);
        assertEquals(0, list.size());
    }

}