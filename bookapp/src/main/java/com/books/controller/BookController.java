package com.books.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.books.model.Order;
import com.books.service.BookOrderServiceImpl;
import com.google.gson.Gson;

@WebServlet("/BookController")
public class BookController extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	static final Logger log=Logger.getLogger(BookController.class);

	/**
     * @param		req
     * @param		resp
     * @exception	IOException	
     * @exception	ServletException, IOException
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("BookController started");

		int createStatus = 0;
		PrintWriter out = resp.getWriter();
		
		Order order = new Order();
		String order_by = req.getParameter("customer");
		String order_book_name = req.getParameter("bookname");
		int order_quantity = Integer.parseInt(req.getParameter("quantity"));
		LocalDate order_date =  LocalDate.now();
		String email = req.getParameter("email");
		String imgsrc= req.getParameter("image");
		
		log.info("Setting values to Object Order");
		order.setOrder_by(order_by);
		order.setOrder_book_name(order_book_name);
		order.setOrder_quantity(order_quantity);
		order.setEmail(email);
		order.setOrder_date(order_date.toString());
		
		createStatus = new BookOrderServiceImpl().createOrder(order);
		
		if(createStatus == 1) {
			log.info("Order placed successfully");
			
			String to=email;
			String subject="AGILE CART";
			String someHtmlMessage= "<h2>Hi "+order_by+",</h2>"
					+ "<h3>Your order placed successfully</h3>"
					+ "<table border='1'><tr><th>Item</th><th>Item Name</th><th>Quantity</th></tr><tr><td><img src="+imgsrc+" width='20%'></td><td>"+order_book_name+"</td><td>"+order_quantity+"</td></tr></table>";

			Mailer.send(to, subject, someHtmlMessage);
			out.println("sucess");
		}else
			out.println("fail");
		
		out.close();
	}
	
	
	/**
     * @param		req
     * @param		resp
     * @exception	IOException	
     * @exception	ServletException, IOException
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Order> orders  = new BookOrderServiceImpl().retriveTotalOrders();
		
		
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		
		out.println(gson.toJson(orders));
	}
	
	
	/**
     * @param		req
     * @param		resp
     * @exception	IOException	
     * @exception	ServletException, IOException
     */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		int deleteStatus = 0;
		
		log.info("Getting Delete order id's");
		String delete_array = req.getParameter("delete_array");	
		
		String a[] = delete_array.split(",");
		int cc[] = new int[a.length];
		int j = 0;
		
		for(String i : a) {
			int order_id = Integer.parseInt(i);
			deleteStatus = new BookOrderServiceImpl().deleteOrder(order_id);
			cc[j] = order_id;
		}
		
		Gson gson = new Gson();
			
		if(deleteStatus == 1) {
			out.println(gson.toJson(cc));
			log.info("Sending Deleted rows in database to UI");
		}
		else
			out.println("fail");

	}
}