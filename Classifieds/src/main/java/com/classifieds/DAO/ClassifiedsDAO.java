package com.classifieds.DAO;

import com.classifieds.model.Classifieds;

import java.sql.SQLException;

import java.util.List;

public interface ClassifiedsDAO {

    //dao method for insertion
    int createClassifieds(Classifieds classifieds) throws SQLException;

    //dao method for retrieving
    List<Classifieds> retrieveClassifieds(String city) throws SQLException;

    //dao method for deletion
    int deleteClassifieds(String[] id) throws SQLException;
}
