package com.bookOrder.dao;

import com.bookOrder.controller.OrderController;
import com.bookOrder.model.Order;
import com.bookOrder.utils.MysqlConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    // Create Logger instance
    static final Logger log = Logger.getLogger(OrderController.class);

    // Queries to create, update, delete and retrieve data from data base
    private static final String SQL_INSERT_INTO_ORDER = "insert into book_order.order(order_date,order_by,book_id,quantity) values(?,?,?,?)";
    private static final String SQL_DELETE_ORDER = "DELETE FROM book_order.order WHERE order_id= ?";
    private static final String SQL_GET_ORDER = "SELECT o.order_id,o.order_date,o.quantity,b.book_name, b.author_name FROM book_order.order o, book_order.book b WHERE b.book_id=o.book_id and b.author_name=? order by order_date desc";
    private static final String SQL_UPDATE_ORDER = "UPDATE book_order.order SET quantity=? WHERE order_id=?";

    // Create connection instance
    MysqlConnection c = new MysqlConnection();

    /**
     * Method to create an order into order table
     *
     * @param o
     * @return insertStatus
     * @throws SQLException
     */
    @Override
    public int createOrder(Order o) throws SQLException {
        log.info("inside create order dao---");

        Connection conn = null;

        log.info("object passed to dao is..." + o);
        int insertStatus = 0;

        // Create connection to database
        try {
            conn = c.getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            log.error("error while connecting:" + e);
        }


        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(SQL_INSERT_INTO_ORDER);

            ps.setString(1, o.getOrderDate());
            ps.setString(2, o.getOrderBy());
            ps.setInt(3, o.getBookId());
            ps.setInt(4, o.getQuantity());

            // Executing prepareStatement
            insertStatus = ps.executeUpdate();

            log.info("after inserting into database--" + insertStatus);
            conn.commit();

        } catch (Exception e) {
            log.error("error is---" + e);
        }
        return insertStatus;

    }


    /**
     * Method to retrieve an order from order table and book_name from book table
     *
     * @param authorName
     * @return list
     * @throws SQLException
     */
    @Override
    public List<Order> getOrder(String authorName) throws SQLException {


        log.info("entered getOrderDAO method...");

        // Create ArrayList instance
        List list = new ArrayList<>();


        Connection conn = null;

        // create connection to database
        try {
            conn = c.getConnection();
        } catch (Exception e) {
            log.error("error in getOrderDAO..." + e);
        }

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            // Create prepareStatement to retrieve order
            ps = conn.prepareStatement(SQL_GET_ORDER);

            log.info("inside get DAO--- author name is:  " + authorName);

            ps.setString(1, authorName);

            // Execute prepare statement query
            rs = ps.executeQuery();


            while (rs.next()) {
                Order o = new Order();

                o.setOrderId(rs.getInt(1));
                o.setOrderDate(rs.getString(2));
                o.setQuantity(rs.getInt(3));
                o.setBookName(rs.getString(4));
                o.setAuthorName(rs.getString(5));

                list.add(o);
                log.info("order details are:" + o);
            }

        } catch (Exception e) {
            log.error("error in getTicketDAO method..." + e);
        }


        return list;


    }


    /**
     * Method to update quantity in order table
     *
     * @param o
     * @return updateStatus
     * @throws SQLException
     */
    @Override
    public int updateOrder(Order o) throws SQLException {


        log.info("inside update order dao---");

        Connection conn = null;
        int updateStatus = 0;

        // Create connection to database
        try {
            conn = c.getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            log.error("error while connecting to database..." + e);
        }

        PreparedStatement ps = null;
        try {

            ps = conn.prepareStatement(SQL_UPDATE_ORDER);

            ps.setInt(1, o.getQuantity());
            ps.setInt(2, o.getOrderId());

            updateStatus = ps.executeUpdate();

            log.info("after updating into database--" + updateStatus);
            conn.commit();

        } catch (Exception e) {
            log.error("error is---" + e);
        }
        return updateStatus;

    }


    // Method to delete order in order table based on order_id

    /**
     * @param orderId
     * @return deleteStatus
     * @throws SQLException
     */
    @Override
    public int deleteOrder(int orderId) throws SQLException {
        log.info("inside delete order dao---");

        Connection conn = null;
        int deleteStatus = 0;

        // Create connection to database
        try {
            conn = c.getConnection();
            conn.setAutoCommit(false);
        } catch (Exception e) {
            log.error("error while connecting to database:  " + e);
        }

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(SQL_DELETE_ORDER);
            ps.setInt(1, orderId);
            deleteStatus = ps.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            log.error("error is---" + e);
        }

        return deleteStatus;
    }
}
