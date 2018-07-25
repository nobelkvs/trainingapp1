package com.contacts.dao;

import com.contacts.entity.Contacts;

import java.sql.SQLException;
import java.util.List;

public interface ContactDao {
    int createContactDao(Contacts contacts) throws SQLException, ClassNotFoundException;

    int deleteContactDao(int [] contactId) throws SQLException, ClassNotFoundException;

    List<Contacts> getContactDao() throws SQLException, ClassNotFoundException;
}
