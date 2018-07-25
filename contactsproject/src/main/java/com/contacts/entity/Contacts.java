package com.contacts.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Contacts {
    private int contactId;
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private Long phoneNumber;
    private String tags;
    private String company;
}
