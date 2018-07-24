package com.bookOrder.dao;

import com.bookOrder.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    int createOrderDAO(Order o) throws SQLException;

   List<Order> getOrderDAO(String authorName) throws SQLException;

   int updateOrderDAO(Order o) throws SQLException;

    int deleteOrderDAO(int id) throws SQLException;

}
