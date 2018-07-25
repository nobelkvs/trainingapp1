package com.books.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.books.model.Book;
import com.books.service.BookOrderServiceImpl;
import com.google.gson.Gson;

@WebServlet("/SearchBookController")
public class SearchBookController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	static final Logger log=Logger.getLogger(SearchBookController.class);

	/**
     * @param		req
     * @param		resp
     * @exception	IOException	
     * @exception	ServletException, IOException
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		log.info("SearchBookController started");
		
        String Name = req.getParameter("book_name");
        
		log.info("Passing book name to get book details");
		List<Book> books  = new BookOrderServiceImpl().retriveBooksBySearch(Name);

		
		
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		
		log.info("Sending Books details to UI");
		out.println(gson.toJson(books));
	}
}