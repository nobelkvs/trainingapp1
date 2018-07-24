package com.note.service;

import com.note.controller.NoteServlet;
import com.note.dao.NoteDao;
import com.note.dao.NoteDaoImpl;
import com.note.model.NoteModel;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NoteServiceImpl  implements NoteService
{

        NoteDao nd=null;
        int status=0;
        Logger log=Logger.getLogger(NoteServiceImpl.class);
        public int createNoteService(NoteModel noteModel)
        {
                log.info("into  create service");
                log.info(noteModel);
                //object creation for dao and calling method
                nd=new NoteDaoImpl();
                status=nd.createNoteDao(noteModel);

                return status;
        }

        public int deleteNoteService(String[] arr)
        {
                nd=new NoteDaoImpl();
                log.info("in service delete");
                log.info(arr);
            //object creation for dao and calling method
                status = nd.deleteNoteDao(arr);
                return status;
        }

        public List<NoteModel> retrieveByNoteNameService()
        {
            nd=new NoteDaoImpl();
            log.info("in service retrieve");
           // log.info(name);
            List<NoteModel> list = new ArrayList<NoteModel>();
            //object creation for dao and calling method
            list = nd.retrieveByNoteNameDao();
            return list;
        }
}
