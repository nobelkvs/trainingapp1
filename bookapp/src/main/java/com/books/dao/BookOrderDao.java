package com.books.dao;

import java.util.List;

import com.books.model.Book;
import com.books.model.Order;


public interface BookOrderDao {
	public int createOrder(Order o);
	public List<Order> retriveOrders();
	public List<Book> retriveBooks();
	public List<Book> retriveBooksBySearch(String name) ;
	public List<Book> retriveBooksBySearchAuthor(String name);
	public int deleteOrder(int order_id);
}
