package com.bookOrder.service;

import com.bookOrder.controller.OrderController;
import com.bookOrder.dao.BookDAOImpl;
import com.bookOrder.dao.OrderDAOImpl;
import com.bookOrder.model.Order;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * This class implements OrderService class methods
 */
public class OrderServiceImpl implements OrderService {

    // Create log4j instance
    static final Logger log = Logger.getLogger(OrderController.class);

    int insertStatus;
    int updateStatus;
    int deleteStatus;


    /**
     * Method used to get book_id from bookDAO and  pass that
     *
     * @param o
     * @param bName
     * @return insertStatus
     * @throws SQLException
     */
    @Override
    public int createOrder(Order o, String bName) throws SQLException {
        log.info("entered Order Service");

        // Create  instance of book DAO
        BookDAOImpl bdi = new BookDAOImpl();

        // Get book_id from book table
        int bId = bdi.getBookId(bName);
        log.info("in service....   book id returned from book dao is--" + bId);
        if (bId == 0) {
            insertStatus = 0;
        } else {

            // Bind book_id to order object
            o.setBookId(bId);

            // Create OrderDAOImpl instance
            OrderDAOImpl odi = new OrderDAOImpl();

            // Call createOrder method of OrderDAOImpl
            insertStatus = odi.createOrder(o);
        }

        return insertStatus;
    }


    /**
     * Method to retrieve details from DAO
     *
     * @param authorName
     * @return orderList
     * @throws SQLException
     */
    @Override
    public List<Order> getOrder(String authorName) throws SQLException {
        OrderDAOImpl odi = new OrderDAOImpl();

        // Call getOrderDAO method to get order
        List<Order> orderList = odi.getOrder(authorName);
        log.info("retrieved data is..." + orderList);

        return orderList;
    }

    // Method to pass update_order details to OrderDAO

    /**
     * @param o
     * @return updateStatus
     * @throws SQLException
     */
    @Override
    public int updateOrder(Order o) throws SQLException {

        // Create OrderDAOImpl instance
        OrderDAOImpl odi = new OrderDAOImpl();

        // Call updateOrderDAO method of OrderDAOImpl class
        updateStatus = odi.updateOrder(o);
        log.info("updated records are......" + updateStatus);

        return updateStatus;
    }


    /**
     * Method to delete order from database
     *
     * @param orderId
     * @return deleteStatus
     * @throws SQLException
     */
    @Override
    public int deleteOrder(int orderId) throws SQLException {

        // Create OrderDAOImpl class instance
        OrderDAOImpl odi = new OrderDAOImpl();

        // Call deleteOrderDAO method of OrderDAOImpl class
        deleteStatus = odi.deleteOrder(orderId);
        log.info("deleted records are..." + deleteStatus);
        return deleteStatus;
    }
}
