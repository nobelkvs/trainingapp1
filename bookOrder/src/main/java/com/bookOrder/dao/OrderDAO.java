package com.bookOrder.dao;

import com.bookOrder.model.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface for OrderDAOImpl class. It has methods to create, update, delete and retrieve orders
 */
public interface OrderDAO {
    int createOrder(Order o) throws SQLException;

    List<Order> getOrder(String authorName) throws SQLException;

    int updateOrder(Order o) throws SQLException;

    int deleteOrder(int id) throws SQLException;

}
