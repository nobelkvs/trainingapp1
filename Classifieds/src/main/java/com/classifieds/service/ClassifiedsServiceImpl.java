package com.classifieds.service;

import com.classifieds.DAO.ClassifiedsDAO;
import com.classifieds.DAO.ClassifiedsDAOImpl;
import com.classifieds.model.Classifieds;

import java.sql.SQLException;
import java.util.List;

/**
 * service class
 */

public class ClassifiedsServiceImpl implements ClassifiedsService {

    //creating dao object

    ClassifiedsDAO cd=null;

    /**
     * service create method to send object to dao and return status to controller
     * @param obj
     * @see com.classifieds.DAO
     * @return insertStatus
     * @throws SQLException
     */
    public int createClassifiedsService(Classifieds obj) throws SQLException {

        cd = new ClassifiedsDAOImpl();

        int insertStatus = cd.createClassifiedsDAO(obj);

        //returning status to controller

        return insertStatus;
    }

    /**
     * service method to retrieve data and return list of retrieved data to controller
     * @param city
     * @see com.classifieds.DAO
     * @return list
     * @throws SQLException
     */
    public List<Classifieds> retrieveClassifiedsService(String city) throws SQLException {

        cd = new ClassifiedsDAOImpl();

        List<Classifieds> list = cd.retrieveClassifiedsDAO(city);

        //returns list of matched records

        return list;
    }

    /**
     * service method for deleting data and returning status to contoller
     * @param id
     * @see com.classifieds.DAO
     * @return deleteStatus
     * @throws SQLException
     */
    public int deleteClassifiedsService(String[] id) throws SQLException {

        cd= new ClassifiedsDAOImpl();

        int deleteStatus = cd.deleteClassifiedsDAO(id);

        return deleteStatus;
    }
}
