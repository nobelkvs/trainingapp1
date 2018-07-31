package com.classifieds.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

/**
 * Model class
 */
public class Classifieds implements Serializable {
    private Integer id;
    private String classifiedDescription;
    private String category;
    private String city;
}
