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

@WebServlet("/SearchAuthorController")
public class SearchAuthorController extends HttpServlet {
	

	private static final long serialVersionUID = 1L;
	static final Logger log=Logger.getLogger(SearchAuthorController.class);
	
	/**
     * @param		req
     * @param		resp
     * @exception	IOException	
     * @exception	ServletException, IOException
     */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		PrintWriter out = resp.getWriter();
		Gson gson = new Gson();
		
		log.info("Search Book By Author Controller started");
        String Name = req.getParameter("author_name");
        
		log.info("Passing author name to get book details");
		List<Book> books  = new BookOrderServiceImpl().retriveBooksBySearchAuthor(Name);
		
		log.info("Sending Books details to UI");
		out.println(gson.toJson(books));
	}
}