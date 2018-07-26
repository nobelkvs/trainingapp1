package com.bookOrder.dao;

import com.bookOrder.model.Order;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class OrderDAOImplTest {

    OrderDAOImpl odi=new OrderDAOImpl();

    // test case to test delete method
    @Test
    public void testDeleteOrder() {

        int orderId=8;

        try {
            int deleteStatus= odi.deleteOrderDAO(orderId);
            assertEquals(0,deleteStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    // junit test case to test create
    @Test
    public void testCreateOrder() throws SQLException {
        Order o=new Order();
       // o.setOrderId(3);
        o.setOrderDate("2018-08-19");
        o.setOrderBy("nidhi");
        o.setBookId(4);
        o.setQuantity(16);
       int insertStatus= odi.createOrderDAO(o);
       assertEquals(1,insertStatus);

    }

    // junit test case to test retrieve method
    @Test
    public void testGetOrder()throws SQLException{

        String authorName="forouzan";
        odi.getOrderDAO(authorName);

    }

    // method ton test retr
    @Test
    public void testEmptyGetOrder()throws SQLException {
        List list= new ArrayList();
        String authorName=" ";
        list=odi.getOrderDAO(authorName);
        assertEquals(0,list.size());

    }

}