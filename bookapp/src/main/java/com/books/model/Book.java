package com.books.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * 
 * @author vinay
 * @Entity Book
 */
public class Book {
	private int bookId;
	private String bookName;
	private String bookAuthor;
	private int bookUnitPrice;
	private int BookAvailable;
	private String BookLink;
}
