package com.note.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

//getter and setter methods(lombok)
@Setter
@Getter
@ToString
//model class
public class NoteModel implements Serializable {

    private Integer id;
    private String subject;
    private String note;
    private String owner;

}
