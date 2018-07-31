package com.note.service;

import com.note.model.NoteModel;

import java.util.List;

public interface NoteService {

    int createNote(NoteModel noteModel);

    int[] deleteNote(String[] arr);

    List<NoteModel> retrieveByNoteName();
}
