package com.classifieds.DAO;

import com.classifieds.model.Classifieds;

import java.sql.SQLException;

import java.util.List;

public interface ClassifiedsDAO {

    //dao method for insertion
    int createClassifiedsDAO(Classifieds classifieds) throws SQLException;

    //dao method for retrieving
    List<Classifieds> retrieveClassifiedsDAO(String city) throws SQLException;

    //dao method for deletion
    int deleteClassifiedsDAO(String[] id) throws SQLException;
}
