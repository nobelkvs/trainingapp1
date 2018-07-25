package com.contacts.dao;

import com.contacts.dbconnection.ContactDBConnection;
import com.contacts.entity.Contacts;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl implements ContactDao {

    // All standard sql query to add, delete, update and retrieve data from database.
    private static final String SQL_INSERT_INTO_CONTACTS_INFO = "insert into CONTACTS_INFO(FIRST_NAME, LAST_NAME, TITLE, COMPANY, EMAIL, PHONE_NUMBER, TAGS ) values(?,?,?,?,?,?,?)";
    private static final String SQL_RETRIEVE_INTO_CONTACTS_INFO = "select * from CONTACTS_INFO";
    private static final String SQL_DELETE_INTO_CONTACTS_INFO = "DELETE FROM CONTACTS_INFO WHERE ID IN ( ";
    private static final String SQL_UPDATE_INTO_CONTACTS_INFO = "UPDATE CONTACTS_INFO SET FIRST_NAME = ?, LAST_NAME = ?, TITLE = ?, COMPANY = ?, EMAIL = ?, PHONE_NUMBER= ?, TAGS = ? WHERE ID = ?";

    private final static Logger logger = Logger.getLogger(ContactDaoImpl.class);

    // Use first level cache data for retrieve operation if database table is not changed;
    private List<Contacts> cacheContactsList;

    // initialize class instance to null.
    private static ContactDaoImpl contactDaoImpl = null;

    /**
     * Private constructor to make it singleton class
     */
    private ContactDaoImpl(){}

    // Static method to return instance of this class using class name
    public static ContactDaoImpl getInstance(){
        if(contactDaoImpl == null){
            contactDaoImpl = new ContactDaoImpl();synchronized(ContactDaoImpl.class){
                if(contactDaoImpl==null){
                    contactDaoImpl = new ContactDaoImpl();
                }
            }
        }
        return contactDaoImpl;
    }

    /**
     * Processes requests for createContactDao
     * methods.
     * @param contacts contacts object
     * @throws SQLException if a sql exception occurs
     * @throws ClassNotFoundException if a classloader
     * not able to find a class in the classpath error occurs
     */
    @Override
    public int createContactDao(Contacts contacts) throws SQLException, ClassNotFoundException {
        Connection connection;

        // initialize createStatus to null.
        connection = ContactDBConnection.getConnect();
        PreparedStatement preparedStatement;
        if(contacts.getContactId()>0){
            preparedStatement = connection.prepareStatement(SQL_UPDATE_INTO_CONTACTS_INFO);
            preparedStatement.setInt(8, contacts.getContactId());
            logger.info("Data updated in database of id ."+contacts.getContactId());
            logger.info("Sql query: "+SQL_UPDATE_INTO_CONTACTS_INFO);
        } else {
            preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_CONTACTS_INFO);
            logger.info("Data added in database.");
            logger.info("Sql query: "+SQL_INSERT_INTO_CONTACTS_INFO);
        }

        // Add all data to prepared query
        preparedStatement.setString(1, contacts.getFirstName());
        preparedStatement.setString(2, contacts.getLastName());
        preparedStatement.setString(3, contacts.getTitle());
        preparedStatement.setString(4, contacts.getCompany());
        preparedStatement.setString(5, contacts.getEmail());
        preparedStatement.setLong(6, contacts.getPhoneNumber());
        preparedStatement.setString(7, contacts.getTags());

        int createStatus = preparedStatement.executeUpdate();

        // reset cacheContactsList to null after database table update.
        cacheContactsList = null;
        return createStatus;
    }
    /**
     * Processes requests for deleteContactDao
     * methods.
     * @param contactId contact id
     * @throws SQLException if a sql exception occurs
     * @throws ClassNotFoundException if a classloader
     * not able to find a class in the classpath error occurs
     */
    @Override
    public int deleteContactDao(int [] contactId) throws SQLException, ClassNotFoundException {
        Connection connection;

        int createStatus;
        connection = ContactDBConnection.getConnect();

        StringBuilder idList = new StringBuilder();

        for (int ignored: contactId) {
            if (idList.length() > 0) {
                idList.append(",");
            }
            idList.append("?");
        }

        PreparedStatement preparedStatement;

        String sqlDelete;
        sqlDelete = SQL_DELETE_INTO_CONTACTS_INFO + idList +")";
        preparedStatement = connection.prepareStatement(sqlDelete);

        for (int i = 0; i < contactId.length; i++) {
            preparedStatement.setInt(i+1,contactId[i]);
        }
        logger.info("Sql query: "+sqlDelete);

        // Set createStatus and return
        createStatus = preparedStatement.executeUpdate();
        // reset cacheContactsList to null after database table update.
        cacheContactsList = null;
        return createStatus;
    }

    /**
     * Processes requests for getContactDao
     * methods.
     * @throws SQLException if a sql exception occurs
     * @throws ClassNotFoundException if a classloader
     * not able to find a class in the classpath error occurs
     */
    @Override
    public List<Contacts> getContactDao() throws SQLException, ClassNotFoundException {
        Connection connection;

        List<Contacts> contactsList = new ArrayList<>();

        // if cacheContactsList is not null display cache data to avoid database interaction.
        if(null == cacheContactsList){
            connection = ContactDBConnection.getConnect();

            ResultSet rs;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_RETRIEVE_INTO_CONTACTS_INFO);
            rs = preparedStatement.executeQuery();

            int n=0;
            while (rs.next()) {

                // Create contacts object and set all fields from each result set
                Contacts contacts = new Contacts();
                contacts.setContactId(rs.getInt("ID"));
                contacts.setFirstName(rs.getString("FIRST_NAME"));
                contacts.setLastName(rs.getString("LAST_NAME"));
                contacts.setTitle(rs.getString("TITLE"));
                contacts.setCompany(rs.getString("COMPANY"));
                contacts.setEmail(rs.getString("EMAIL"));
                contacts.setPhoneNumber(Long.parseLong(rs.getString("PHONE_NUMBER")));
                contacts.setTags(rs.getString("TAGS"));

                // Add contacts object to array list
                contactsList.add(contacts);

                String n1 = rs.getString("Id");
                String n2 = rs.getString("FIRST_NAME");
                String n3 = rs.getString("LAST_NAME");
                String n4 = rs.getString("TITLE");
                String n5 = rs.getString("COMPANY");
                String n6 = rs.getString("EMAIL");
                String n7 = rs.getString("PHONE_NUMBER");
                String n8 = rs.getString("TAGS");
                logger.info("Row "+ ++n + "data --> "+n1 + " " + n2 + " " + n3 + " " + n4 + " " + n5 + " " + n6 + " " + n7 + " " + n8);
            }
            cacheContactsList = contactsList;
            logger.info("Data retrieve from database.");
            logger.info("Sql query: "+SQL_RETRIEVE_INTO_CONTACTS_INFO);
        } else {
            logger.info("Data retrieve from cache level.");
        }
        contactsList = cacheContactsList;

        return contactsList;
    }
}
