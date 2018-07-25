package com.bookOrder.service;

import com.bookOrder.controller.OrderController;
import com.bookOrder.dao.BookDAOImpl;
import com.bookOrder.dao.OrderDAOImpl;
import com.bookOrder.model.Order;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    // initialize log4j
    static final Logger log = Logger.getLogger(OrderController.class);

    int insertStatus;
    int updateStatus;
    int deleteStatus;

    // method to create order
    @Override
    public int createOrder(Order o, String bName) throws SQLException {
        log.info("inside Order Service");

        // create  instance of book DAO
        BookDAOImpl bdi = new BookDAOImpl();

        // get book_id from book table
        int bId = bdi.getBookId(bName);
        log.info("in service....   book id returned from book dao is--" + bId);
        if (bId == 0) {
            insertStatus = 0;
        } else {

            // bind book_id to order object
            o.setBookId(bId);
            OrderDAOImpl odi = new OrderDAOImpl();

            // call create order method of OrderDAOImpl
            insertStatus = odi.createOrderDAO(o);
        }

        return insertStatus;
    }

    // method to retrieve details
    @Override
    public List<Order> getOrder(String authorName) throws SQLException {
        OrderDAOImpl odi = new OrderDAOImpl();

        // call getOrderDAO method to get order
        List<Order> li = odi.getOrderDAO(authorName);
        log.info("retrieved data is..." + li);

        return li;
    }

    // method to update order table
    @Override
    public int updateOrder(Order o) throws SQLException {

        OrderDAOImpl odi = new OrderDAOImpl();

        updateStatus = odi.updateOrderDAO(o);
        log.info("updated records are......" + updateStatus);

        return updateStatus;
    }


    // method to delete order
    @Override
    public int deleteOrder(int orderId) throws SQLException {
        OrderDAOImpl odi = new OrderDAOImpl();

        // call deleteOrderDAO method of OrderDAOImpl class
        deleteStatus = odi.deleteOrderDAO(orderId);
        log.info("deleted records are..." + deleteStatus);
        return deleteStatus;
    }
}
