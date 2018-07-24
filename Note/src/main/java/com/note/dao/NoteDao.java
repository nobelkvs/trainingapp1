package com.note.dao;

import com.note.model.NoteModel;

import java.util.List;

public interface NoteDao
{
    public int createNoteDao(NoteModel noteModel);
    public int deleteNoteDao(String[] arr);
    public List<NoteModel> retrieveByNoteNameDao();
}
