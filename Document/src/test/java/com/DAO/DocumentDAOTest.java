/*package com.DAO;

import com.model.DocumentModel;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class DocumentDAOTest {
    LocalDate localdate= LocalDate.now();
    @Test
    public void createDocumentDAO() {
        DocumentModel model=new DocumentModel();
        model.setTitle("Document76");
        model.setRelatedContacts("meghana");
        model.setRelatedDeals("business");
        model.setOwner("swetha");
        model.setCreatedDate(localdate);
        DocumentDAO dao=new DocumentDAO();
        int res=dao.createDocumentDAO(model);
        assertEquals(37,res);

    }

    @Test
    public void deleteMultipleDocument() {
        DocumentDAO dao= new DocumentDAO();
        int res= 0;
        try {
            res = dao.deleteMultipleDocument(31);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1,res);
    }

    @Test
    public void retreiveDocumentDAO() {
        DocumentDAO dao=new DocumentDAO();
        List<DocumentModel> list=dao.retreiveDocumentDAO();
        assertEquals(3,list.size());
    }
}*/