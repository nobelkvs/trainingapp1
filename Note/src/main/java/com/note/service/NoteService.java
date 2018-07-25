package com.note.service;

import com.note.model.NoteModel;

import java.util.List;

public interface NoteService {

    public int createNoteService(NoteModel noteModel);

    public int deleteNoteService(String[] arr);

    public List<NoteModel> retrieveByNoteNameService();
}
