package com.note.dao;

import com.note.model.NoteModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NoteDaoImplTest {
    NoteDao nd = new NoteDaoImpl();
    int status = 0;

    @Test
    public void createNoteDao() {
        NoteModel nm = new NoteModel();
        nm.setSubject("testnote");
        nm.setOwner("kpreddy");
        nm.setNote("hello this is junit testing");
        status = nd.createNoteDao(nm);
        assertEquals(0, status);

    }

    @Test
    public void deleteNoteDao() {
        String id = "25";
        String[] arr = id.split(",");
        status = nd.deleteNoteDao(arr);
        assertEquals(0, status);
    }

    @Test
    public void retrieveByNoteNameDao() {

        List<NoteModel> list = new ArrayList<NoteModel>();
        list = nd.retrieveByNoteNameDao();
        assertEquals(list.size(), list.size());
    }
}