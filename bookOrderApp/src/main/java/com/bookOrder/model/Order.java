package com.bookOrder.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Model class has private properties, setter and getter methods
 */
@Getter
@Setter
@ToString
public class Order implements Serializable {

    private Integer orderId;
    private String orderDate;
    private String orderBy;
    private Integer bookId;
    private Integer quantity;
    private String bookName;
    private String authorName;

}
