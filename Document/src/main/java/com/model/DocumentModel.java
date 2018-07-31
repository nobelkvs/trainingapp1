package com.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;


@Setter
@Getter
@ToString
/**
 * This is Model class which holds the details of the Document.Here,we are using Lombok for Setter and Getter methods
 */
public class DocumentModel implements Serializable {
    private int id;
    private String title;
    private String relatedContacts;
    private String relatedDeals;
    private String owner;
    private LocalDate createdDate;

}
