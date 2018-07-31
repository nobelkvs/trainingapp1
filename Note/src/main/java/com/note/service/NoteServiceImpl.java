package com.note.service;

import com.note.dao.NoteDao;
import com.note.dao.NoteDaoImpl;
import com.note.model.NoteModel;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NoteServiceImpl implements NoteService {

    NoteDao nd = null;
    int status = 0;
    Logger log = Logger.getLogger(NoteServiceImpl.class);

    /**
     * create method in service,calls the dao method
     *
     * @param noteModel
     * @return
     */

    public int createNote(NoteModel noteModel) {
        log.info("into  create service");
        log.info("object : " + noteModel);
        //object creation for dao and calling method
        nd = new NoteDaoImpl();
        status = nd.createNote(noteModel);

        return status;
    }

    /**
     * delete method for service,calls the dao method
     *
     * @param arr
     * @return
     */

    public int[] deleteNote(String[] arr) {
        int[] deletearr = new int[arr.length];
        nd = new NoteDaoImpl();
        log.info("in service delete");
        log.info("array : " + arr);
        //object creation for dao and calling method
        try {
            //x=arr.length;
            deletearr = nd.deleteNote(arr);
            //status = nd.deleteNoteDao(arr);

        } catch (Exception e) {
            log.error("sql exception " + e);
            e.printStackTrace();

        }
        return deletearr;
        //return status;
    }

    /**
     * retrieve method in service,calls the dao method
     *
     * @return
     */

    public List<NoteModel> retrieveByNoteName() {
        nd = new NoteDaoImpl();
        log.info("in service retrieve");
        // log.info(name);
        List<NoteModel> list = new ArrayList<NoteModel>();
        //object creation for dao and calling method
        list = nd.retrieveByNoteName();
        return list;
    }
}
