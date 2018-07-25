package com.contacts.service;

import com.contacts.entity.Contacts;

import java.sql.SQLException;
import java.util.List;

public interface ContactService {
    int createContactService(Contacts contacts) throws SQLException, ClassNotFoundException;

    int deleteContactService(int [] contactId) throws SQLException, ClassNotFoundException;

    List<Contacts> getContactService() throws SQLException, ClassNotFoundException;
}
