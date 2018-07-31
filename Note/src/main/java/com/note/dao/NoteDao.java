package com.note.dao;

import com.note.model.NoteModel;

import java.util.List;

public interface NoteDao {
    int createNote(NoteModel noteModel);

    int[] deleteNote(String[] arr);

    List<NoteModel> retrieveByNoteName();
}
