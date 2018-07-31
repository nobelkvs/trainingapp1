package com.controller;

import com.model.DocumentModel;
import com.service.DocumentService;
import com.service.DocumentServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * This is Controller Implementation class which takes input from UI and pass to
 * the service
 */
public class DocumentControllerImpl {
    static final Logger log = Logger.getLogger(DocumentControllerImpl.class);
    int status = 0;

    /**
     * This is create method which takes inputs bind with the object and pass to
     * service
     *
     * @param request
     * @param response
     * @return status
     */
    public int createDocument(HttpServletRequest request, HttpServletResponse response) {
        LocalDate localdate;
        localdate = LocalDate.now();
        //Taking input parameters from UI
        String title = request.getParameter("title");
        String relatedContacts = request.getParameter("relatedContact");
        String relatedDeals = request.getParameter("relatedDeal");
        String owner = request.getParameter("owner");

        //Binding the taken inputs with the DocumentModel object
        DocumentModel document = new DocumentModel();
        document.setTitle(title);
        document.setRelatedContacts(relatedContacts);
        document.setRelatedDeals(relatedDeals);
        document.setOwner(owner);
        document.setCreatedDate(localdate);
        //Creating DocumentServiceImpl object
        DocumentService service = new DocumentServiceImpl();
        //Calling the creatDocumentService method by using the object
        status = service.createDocument(document);
        log.info("insertion status:"+ status);
        return status;

    }

    /**
     * This is retrieve method which returns a list
     *
     * @param request
     * @param response
     * @return list
     */

    public List<DocumentModel> retreiveDocument(HttpServletRequest request, HttpServletResponse response) {
        List<DocumentModel> documentmodel = null;
        DocumentService service = new DocumentServiceImpl();
        documentmodel = service.retreiveDocument();
        log.info("retrieved list:"+documentmodel);
        return documentmodel;
    }

    /**
     * This is delete method which takes id as an input and pass that id to the service
     *
     * @param request
     * @param response
     * @return status
     */
    public int deleteMultipleDocument(HttpServletRequest request, HttpServletResponse response) {
        //Taking  multiple ids as input parameter from UI
        String id = request.getParameter("id");
        //Seperating the ids and storing into an array
        String[] sid = id.split(",");

        for (int i = 0; i < sid.length; i++) {
            DocumentService service = new DocumentServiceImpl();
            int eid = Integer.parseInt(sid[i]);
            //passing id as parameter to the deleteMultipleDocument method
            status = service.deleteMultipleDocument(eid);
            log.info("delete status:"+status);
        }
        return status;
    }
}

