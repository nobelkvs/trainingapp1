package com.books.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.books.model.Order;

public class BookOrderDaoImpleTest {
	Order o=new Order();
	BookOrderDaoImple b=new BookOrderDaoImple();
	
	@Test
	public void testDeleteOrder() {
		int i=524;
		int status=b.deleteOrder(i);
		int expected=1;
		assertEquals(expected, status);
	}
}
