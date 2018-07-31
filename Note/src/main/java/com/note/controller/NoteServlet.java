package com.note.controller;

import com.google.gson.Gson;
import com.note.model.NoteModel;
import com.note.service.NoteService;
import com.note.service.NoteServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


import java.util.ArrayList;
import java.util.List;

public class NoteServlet extends HttpServlet {
    int status = 0;
    Logger log = Logger.getLogger(NoteServlet.class);

    //options function
    //

    /**
     *
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("in options servlet");
        super.doOptions(req, resp);
    }

    //post

    /**
     * gets the data from ui and calls the service to create a note
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        PrintWriter out = res.getWriter();
        //to get data
        String subject = req.getParameter("subject");
        String note = req.getParameter("note");
        String owner = req.getParameter("owner");
        //checking the data
        log.info(subject);
        log.info(note);
        log.info(owner);
        //creating a mobel object and binding data to object
        NoteModel noteModel = new NoteModel();
        noteModel.setSubject(subject);
        noteModel.setOwner(owner);
        noteModel.setNote(note);
        //out.print(noteModel);
        try {
            //creating service object and calling it
            NoteService ns = new NoteServiceImpl();
            status = ns.createNote(noteModel);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();

        }
        //checking the status
        if (status > 0)
            out.print("sucessfully inserted");
        else
            out.print("failed to insert");


    }

    /**
     * method to retrieve data from database and pass to ui
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("in do get");
        //String name = req.getParameter("sub");
        PrintWriter out = resp.getWriter();
        //creating service object
        NoteService ns = new NoteServiceImpl();
        //out.print(name);
        //creating Gson object to convert list into json
        Gson gson = new Gson();
        List<NoteModel> tlist = new ArrayList<NoteModel>();
        try {
            log.info("try");

            //calling service method
            tlist = ns.retrieveByNoteName();

            //converting list to json
            String obj = gson.toJson(tlist);
            // out.println("next");
            out.print(obj);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();


        }
        log.info("done");


    }

    /**
     * method to perform delete operation
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    //delete method
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("in delete servlet");
        PrintWriter out = resp.getWriter();

        //to get data from ui
        String multipleDelete = req.getParameter("id");
        log.info(multipleDelete);
        //String userrole = req.getParameter("userrole");
        String[] multidelete = multipleDelete.split(",");
//        for (int i = 0; i < multidelete.length; i++) {
//            out.print(multidelete[i]);
//        }

        //service object creation and calling method
        NoteService ns = new NoteServiceImpl();

        int[] deletearr = ns.deleteNote(multidelete);

        for (int i = 0; i < deletearr.length; i++) {
            if (deletearr[i] > 0)
              //  out.println(multidelete[i] + "deleted sucessfully");
                out.print("deleted sucessfully");
            else
                //out.println(multidelete[i] + "deleted sucessfully");
                out.print("deleted sucessfully");
//
//        int count=ns.deleteNoteService(multidelete);
//        if(count==multidelete.length)
//            out.print("deleted sucessfully");
//        else
//               out.print("failed to delete");


        }


//        if (count == multidelete.length)
//            out.print("deleted sucessfully");
//
    }
}


