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
        String title = request.getParameter("title");
        String relatedContacts = request.getParameter("relatedContact");
        String relatedDeals = request.getParameter("relatedDeal");
        String owner = request.getParameter("owner");


        DocumentModel document = new DocumentModel();
        document.setTitle(title);
        document.setRelatedContacts(relatedContacts);
        document.setRelatedDeals(relatedDeals);
        document.setOwner(owner);
        document.setCreatedDate(localdate);

        DocumentService service = new DocumentServiceImpl();
        status = service.createDocumentService(document);
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
        documentmodel = service.retreiveDocumentService();
        return documentmodel;
    }

    /**
     * This is delete method which takes id as an input and pass that id to the service
     *
     * @param request
     * @param response
     * @return status
     */
    public int deleteMultipleDocument(HttpServletRequest request,HttpServletResponse response){
        String id=request.getParameter("id");
        String[] sid=id.split(",");

            for(int i=0;i<sid.length;i++){
            DocumentService service=new DocumentServiceImpl();
            int eid=Integer.parseInt(sid[i]);
            status=service.deleteMultipleDocument(eid);

            }
        return status;
        }
    }

