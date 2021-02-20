package com.contacts.service;

import com.contacts.dao.ContactDao;
import com.contacts.dao.ContactDaoImpl;
import com.contacts.entity.Contacts;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    private final static Logger logger = Logger.getLogger(ContactServiceImpl.class);

    @Override
    public int createContactService(Contacts contacts) throws SQLException, ClassNotFoundException {
        ContactDao contactDaoImpl = ContactDaoImpl.getInstance();
        int createStatus;
        createStatus = contactDaoImpl.createContactDao(contacts);
        return createStatus;
    }

    @Override
    public int deleteContactService(int [] contactId) throws SQLException, ClassNotFoundException {
        logger.debug("Id to delete from database. "+ Arrays.toString(contactId));
        ContactDao contactDaoImpl = ContactDaoImpl.getInstance();
        int createStatus;
        createStatus = contactDaoImpl.deleteContactDao(contactId);
        return createStatus;
    }

    @Override
    public List<Contacts> getContactService() throws SQLException, ClassNotFoundException {
        ContactDao contactDaoImpl = ContactDaoImpl.getInstance();
        List<Contacts> contactsList;
        contactsList = contactDaoImpl.getContactDao();

        return contactsList;
    }
}
