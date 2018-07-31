package com.controller;

import com.google.gson.Gson;
import com.model.DocumentModel;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This is controller class which takes action field as an input and performs the operation based upon that action
 */
public class DocumentController extends HttpServlet {

    static final Logger log = Logger.getLogger(DocumentController.class);

    /**
     * doPost method takes request and response as an input arguments
     * @param request
     * @param response
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        //Initialising status variable
        int status = 0;
        PrintWriter out = response.getWriter();
        //Taking input action from UI
        String action = request.getParameter("action");
        if (action.equals("create")) {
            DocumentControllerImpl controllerImpl = new DocumentControllerImpl();
            status = controllerImpl.createDocument(request, response);
            if (status >= 1)
                out.println("document is created successfully and your id is"+ "="+status);
            else
                out.println("document is not created");

        } else if (action.equals("retreive")) {
            DocumentControllerImpl controllerImpl = new DocumentControllerImpl();
            List<DocumentModel> documentmodel = controllerImpl.retreiveDocument(request, response);
            //Converting java object to json object
            Gson gson=new Gson();
            String obj=gson.toJson(documentmodel);
            out.println(obj);
           /*for (DocumentModel document : documentmodel) {
                out.println(document);
                }*/
        }

        else if (action.equals("delete")) {
            //Creating DocumentControllerImpl object
            DocumentControllerImpl controller = new DocumentControllerImpl();
            //Calling deleteMultipleDocument method using DocumentControllerImpl object
            status=controller.deleteMultipleDocument(request,response);
            if (status >= 1)
                out.println("document is deleted successfully");
            else
                out.println("");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
