package com.classifieds.service;

import com.classifieds.model.Classifieds;

import java.sql.SQLException;
import java.util.List;

public interface ClassifiedsService {

    //Service method for inserting
    int createClassifieds(Classifieds obj) throws SQLException;

    //Service method for retrieving
    List<Classifieds> retrieveClassifieds(String city) throws SQLException;

    //Service method for deleting
    int deleteClassifieds(String[] id) throws SQLException;

}
