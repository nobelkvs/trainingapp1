package com.books.service;

import java.util.List;

import com.books.dao.BookOrderDaoImple;
import com.books.model.Book;
import com.books.model.Order;

public class BookOrderServiceImpl implements BookOrderService {
	
	public int createOrder(Order o) {
		return new BookOrderDaoImple().createOrder(o);
	}

	public List<Order> retriveTotalOrders() {
		return new BookOrderDaoImple().retriveOrders();
	}

	public List<Book> retriveBooks() {
		return new BookOrderDaoImple().retriveBooks();

	}

	public List<Book> retriveBooksBySearch(String name) {
		return new BookOrderDaoImple().retriveBooksBySearch(name);
	}

	public List<Book> retriveBooksBySearchAuthor(String name) {
		return new BookOrderDaoImple().retriveBooksBySearchAuthor(name);
	}

	public int deleteOrder(int order_id) {
		return new BookOrderDaoImple().deleteOrder(order_id);
	}
	
}
