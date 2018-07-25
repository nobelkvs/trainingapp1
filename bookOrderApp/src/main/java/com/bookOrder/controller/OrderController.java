package com.bookOrder.controller;

import com.bookOrder.model.Order;
import com.bookOrder.service.OrderServiceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class OrderController extends HttpServlet {

    //initialize log4j
    static final Logger log = Logger.getLogger(OrderController.class);

    //initialize
    int retrieveStatus = 0;
    int updateStatus = 0;
    int deleteStatus = 0;
    int result = 0;

    // method to create order
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();

        // get input values from ui through ajax
        String order_by = req.getParameter("orderBy");

        String book_name = req.getParameter("bookName");
        int bQuantity = Integer.parseInt(req.getParameter("quantity"));
        LocalDate order_date = LocalDate.now();

        // create Order class object
        Order o = new Order();

        // binding values to order object
        o.setOrderDate(String.valueOf(order_date));
        o.setOrderBy(order_by);
        o.setQuantity(bQuantity);

        // create OrderServiceImpl class instance
        OrderServiceImpl osi = new OrderServiceImpl();

        try {
            // call createOrder method in orderServiceImpl class
            int insertStatus = osi.createOrder(o, book_name);
            log.info("no. of rows inserted...." + insertStatus);
            out.println(insertStatus);
        } catch (SQLException e) {
            log.error("error in order controller");
            e.printStackTrace();
        }

    }

    // method to retrieve data from order table
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        // create Gson instance
        Gson gson = new Gson();
        log.info("entered controller...");

        PrintWriter out = resp.getWriter();

        String name = req.getParameter("authorName");
        log.info("author name is..." + name);

        // create OrderServiceImpl instance.
        OrderServiceImpl osi = new OrderServiceImpl();
        try {

            // assign returned order records to list
            List list = osi.getOrder(name);

            // convert list to json
            String str = gson.toJson(list);

            if (list.isEmpty()) {
                log.info("list is empty... No orders available");
            }else{

                out.println(str);
                log.info("retrieved list is ..."+list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("error in get controller..." + e);
        }

    }

    // method to update order
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, IOException {

        PrintWriter out=resp.getWriter();

        try {

            // convert input data to json
            JSONObject json = getJsonData(new BufferedReader(new InputStreamReader(req.getInputStream())).readLine());

            log.info("inside update controller---");


            int id = json.getInt("OrderId");
            int uQuantity = json.getInt("quantity");

            log.info("order id is---" + id);
            log.info("quantity is----" + uQuantity);

            // create Order class instance
            Order o = new Order();

            // binding values to
            o.setOrderId(id);
            o.setQuantity(uQuantity);

            // create OrderServiceImpl instance
            OrderServiceImpl osi = new OrderServiceImpl();
            try {

                // call updateOrder method of OrderServiceImpl class
                updateStatus = osi.updateOrder(o);
                log.info("no.of rows updated..." + updateStatus);
            } catch (SQLException e) {
                log.error("error in update controller..." + e);
                e.printStackTrace();
            }

            if (updateStatus >= 1) {
                log.info("successfully updated..");
            } else {
                log.info("no rows updated.. please check the entered details");
            }
        } catch (JSONException e) {
            log.error("error in update controller..." + e);
            e.printStackTrace();
        }
        out.println(updateStatus);

    }

    private JSONObject getJsonData(String data) throws JSONException {
        JSONObject json = new JSONObject(data);
        return json;
    }

    //method to delete order
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out=resp.getWriter();

        // get input from ui
        String id = req.getParameter("orderId");

        String[] idArr=id.split(",");

        for(int i=0;i<idArr.length;i++){
            int order_id=Integer.parseInt(idArr[i]);
        OrderServiceImpl osi = new OrderServiceImpl();
        try {

            // call deleteOrder method
            deleteStatus = osi.deleteOrder(order_id);
            log.info("no.of rows deleted---" + deleteStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (deleteStatus >= 1) {
            log.info("successfully deleted");
        } else {
            log.info("no orders to delete");
        }
        }
        out.print(deleteStatus);

    }


}