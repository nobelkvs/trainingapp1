package com.note.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;


@Setter@Getter@ToString
public class NoteModel implements Serializable
{

    private Integer id;
    private String subject;
    private String note;
    private String owner;

}
