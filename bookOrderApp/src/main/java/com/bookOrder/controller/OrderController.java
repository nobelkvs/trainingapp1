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

    // Initialize log4j
    static final Logger log = Logger.getLogger(OrderController.class);

    // Initialize
    int retrieveStatus = 0;
    int updateStatus = 0;
    int deleteStatus = 0;
    int result = 0;

    // Method to create order by getting request

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        log.info("entered create controller...");

        // Get input values from ui through ajax
        String order_by = req.getParameter("orderBy");

        String book_name = req.getParameter("bookName");
        int bQuantity = Integer.parseInt(req.getParameter("quantity"));

        // Create local object to generate date
        LocalDate order_date = LocalDate.now();

        // Create Order class object
        Order o = new Order();

        // Binding values to order object
        o.setOrderDate(String.valueOf(order_date));
        o.setOrderBy(order_by);
        o.setQuantity(bQuantity);

        // Create OrderServiceImpl class instance
        OrderServiceImpl osi = new OrderServiceImpl();

        try {

            // Call createOrder method in orderServiceImpl class
            int insertStatus = osi.createOrder(o, book_name);
            log.info("no. of rows inserted...." + insertStatus);
            out.println(insertStatus);
        } catch (SQLException e) {
            log.error("error in order controller" + e);
        }

    }


    /**
     * Method to retrieve data from order table by getting request object
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        log.info("entered get controller...");

        PrintWriter out = resp.getWriter();

        String name = req.getParameter("authorName");
        log.info("author name is..." + name);

        // Create OrderServiceImpl instance.
        OrderServiceImpl osi = new OrderServiceImpl();
        try {

            // Assign returned order records to list
            List list = osi.getOrder(name);

            // Create Gson instance
            Gson gson = new Gson();

            // Convert list to json
            String str = gson.toJson(list);

            if (list.isEmpty()) {
                log.info("list is empty... No orders available");
            } else {
                out.println(str);
                log.info("retrieved list is ..." + list);
            }
        } catch (SQLException e) {
            log.error("error in get controller..." + e);
        }

    }


    /**
     * Method to update order by getting request from request object
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();

        try {

            // Convert input data to json
            JSONObject json = getJsonData(new BufferedReader(new InputStreamReader(req.getInputStream())).readLine());

            log.info("entered update controller---");


            int id = json.getInt("OrderId");
            int uQuantity = json.getInt("quantity");

            log.info("order id is---" + id);
            log.info("quantity is----" + uQuantity);

            // Create Order class instance
            Order o = new Order();

            // Binding values to
            o.setOrderId(id);
            o.setQuantity(uQuantity);

            // Create OrderServiceImpl instance
            OrderServiceImpl osi = new OrderServiceImpl();
            try {

                // Call updateOrder method of OrderServiceImpl class
                updateStatus = osi.updateOrder(o);
                log.info("no.of rows updated..." + updateStatus);
            } catch (SQLException e) {
                log.error("error in update controller..." + e);
            }

            log.info(updateStatus >= 1 ? "successfully updated.." : "no rows updated.. ");

        } catch (JSONException e) {
            log.error("error in update controller..." + e);
        }
        out.println(updateStatus);

    }

    /**
     *
     * @param data
     * @return json
     * @throws JSONException
     */
    private JSONObject getJsonData(String data) throws JSONException {
        JSONObject json = new JSONObject(data);
        return json;
    }


    /**
     * Method to delete order by getting request from ui
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter out = resp.getWriter();

        // Get input from ui
        String id = req.getParameter("orderId");

        // Splitting the id string
        String[] idArr = id.split(",");

        // Iterating through the id array
        for (int i = 0; i < idArr.length; i++) {

            int order_id = Integer.parseInt(idArr[i]); // converting string to number
            OrderServiceImpl osi = new OrderServiceImpl(); // create OrderServiceImpl instance
            try {

                // Call deleteOrder method
                deleteStatus = osi.deleteOrder(order_id);
                log.info("no.of rows deleted---" + deleteStatus);
            } catch (SQLException e) {
                log.error("error while inserting..." + e);
            }

            log.info(deleteStatus >= 1 ? "successfully deleted" : "no orders to delete");
        }
        out.print(deleteStatus);

    }


}