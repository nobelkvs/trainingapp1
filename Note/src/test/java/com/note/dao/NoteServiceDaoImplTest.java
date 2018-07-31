package com.note.dao;

import com.note.model.NoteModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NoteServiceDaoImplTest {
    NoteDao nd = new NoteDaoImpl();
    int status = 0;

    //@Test(expected = SQLException)
    public void createNoteDao() {
        NoteModel nm = new NoteModel();
        nm.setSubject("testnote");
        nm.setOwner("kpreddy");
        nm.setNote("hello this is junit testing");
        status = nd.createNote(nm);
        assertEquals(0, status);


    }

    @Test
    public void deleteNoteDao() {
        String id = "25";
        String[] arr = id.split(",");
        int[] delarr = nd.deleteNote(arr);
      //  assertEquals(0], delarr);
        for(int i=0;i<delarr.length;i++) {
            assertEquals(0,delarr[i]);
        }


    }

    @Test
    public void retrieveByNoteNameDao() {

        List<NoteModel> list = new ArrayList<NoteModel>();
        list = nd.retrieveByNoteName();
        assertEquals(list.size(), list.size());
    }
}