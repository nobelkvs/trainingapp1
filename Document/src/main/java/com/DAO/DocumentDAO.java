package com.DAO;

import com.model.DocumentModel;
import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This is DocumentDAO class which gets the connection from MyDbConnection class
 * and performs create,retrieve,delete operations
 */
public class DocumentDAO {
    static final Logger log = Logger.getLogger(DocumentDAO.class);
    int status = 0;
    MyDbConnection md = new MyDbConnection();
    Connection conn = null;

    /**
     * This is create method which takes object as an input and insert that document
     * into database
     *
     * @param document
     * @return status
     */
    public int createDocument(DocumentModel document) {
        //This is non-select query to insert the values into database
        String query = "INSERT INTO Document_schema.document_table(Title,Related_contacts,related_deal,owner,created_date) Values(?,?,?,?,?)";
        ResultSet rs = null;
        PreparedStatement ps = null;
        int id = 0;
        try {
            conn = md.getConnection();
        } catch (Exception e) {
            log.error(e);
        }
        try {
            ps = conn.prepareStatement(query);
            //Setting the values for positional parameters
            ps.setString(1, document.getTitle());
            ps.setString(2, document.getRelatedContacts());
            ps.setString(3, document.getRelatedDeals());
            ps.setString(4, document.getOwner());
            ps.setDate(5, Date.valueOf(document.getCreatedDate()));
            //Executing the query
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {
            log.error(e);
        }
        return id;
    }

    /**
     * This is delete method which deletes the record from database based on given id
     *
     * @param id
     * @return status
     */
    public int deleteMultipleDocument(int id) {
        //This is the non-select query whichi is used to delete the record based on id
        String query = "DELETE FROM Document_schema.document_table WHERE id=?";
        int deleteStatus = 0;
        try {
            conn = md.getConnection();
        } catch (Exception e) {
            log.error(e);
        }
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            deleteStatus = ps.executeUpdate();
        } catch (SQLException s) {
            log.error(s);
        }
        return deleteStatus;
    }

    /**
     * This is retrieve methods which retrieves the data from database
     *
     * @return list
     */
    public List<DocumentModel> retreiveDocument() {
        //This is a select query to retrieve data from data base
        String query = "SELECT id,Title,Related_contacts,owner,created_date FROM Document_schema.document_table Order By owner ";
        try {
            //Getting the Connection from MyDbConnection
            conn = md.getConnection();
        } catch (Exception e) {
            log.error(e);
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<DocumentModel> documentmodel = new ArrayList<DocumentModel>();
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                DocumentModel document = new DocumentModel();
                document.setId(rs.getInt(1));
                document.setTitle(rs.getString(2));
                document.setRelatedContacts(rs.getString(3));
                document.setOwner(rs.getString(4));
                document.setCreatedDate(LocalDate.parse(rs.getString(5)));
                documentmodel.add(document);
            }

        } catch (SQLException e) {
            log.error(e);
        }
        return documentmodel;
    }

}
