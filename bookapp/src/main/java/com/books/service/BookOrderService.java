package com.books.service;

import java.util.List;

import com.books.model.Book;
import com.books.model.Order;


public interface BookOrderService {
	public int createOrder(Order o);
	public List<Order> retriveTotalOrders(); 
	public List<Book> retriveBooks(); 
	public int deleteOrder(int order_id) ;
	public List<Book> retriveBooksBySearchAuthor(String name);
	public List<Book> retriveBooksBySearch(String name);
	
}
