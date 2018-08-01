package com.bookOrder.controller;

import com.bookOrder.model.Order;
import com.bookOrder.service.OrderServiceImpl;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class OrderControllerTest {

    static final Logger log = Logger.getLogger(OrderController.class);

    OrderServiceImpl osi = new OrderServiceImpl();

    @Test
    public void testCreateController() {
        Order o = new Order();

        String bookName = "java";
        o.setOrderDate("2018-08-01");
        o.setOrderBy("neha");
        // o.setBookId(5);
        o.setQuantity(13);
        int insertStatus = 0;
        try {
            insertStatus = osi.createOrder(o, bookName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1, insertStatus);
    }

    @Test
    public void testGetController() {
        String authorName = "galvin";
        List li = null;
        try {
            li = osi.getOrder(authorName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        log.info(li);
    }

    @Test
    public void testDuplicateGetOrder() {
        String authorName = "chand";
        List li = null;
        try {
            li = osi.getOrder(authorName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(0, li.size());


    }

    @Test
    public void testDeleteController() {
        int orderId = 132;

        try {
            int deleteStatus = osi.deleteOrder(orderId);
            assertEquals(0, deleteStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}