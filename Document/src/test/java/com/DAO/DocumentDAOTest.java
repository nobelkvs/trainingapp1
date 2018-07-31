package com.DAO;

import com.model.DocumentModel;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class DocumentDAOTest {
    LocalDate localdate = LocalDate.now();

    @Test
    public void createDocumentDAO() {
        DocumentModel model = new DocumentModel();
        model.setTitle("Document58");
        model.setRelatedDeals("business");
        model.setOwner("swathi");
        model.setRelatedContacts("munni");
        model.setCreatedDate(localdate);
        DocumentDAO dao = new DocumentDAO();
        int status=dao.createDocument(model);
        assertEquals(0,status);
    }

    @Test
    public void deleteMultipleDocument() {
        DocumentDAO dao = new DocumentDAO();
        int res = 0;

        res = dao.deleteMultipleDocument(49);

        assertEquals(0, res);
    }

    @Test
    public void retreiveDocumentDAO() {
        DocumentDAO dao = new DocumentDAO();
        List<DocumentModel> list = dao.retreiveDocument();
        DocumentModel model = new DocumentModel();
        assertNotNull(list.contains(model));
    }
}