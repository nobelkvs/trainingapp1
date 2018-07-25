
package com.books.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * 
 * @author vinay
 * @Entity order
 */
public class Order{
	private int order_id;
	private String order_by;
	private String order_book_name;
	private int order_quantity;
	private String order_date;
}