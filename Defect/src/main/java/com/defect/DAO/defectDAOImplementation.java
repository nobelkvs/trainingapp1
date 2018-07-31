package com.defect.DAO;

import com.defect.Model.defectModel;
import com.defect.util.MySQLConnection;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * defectDAOImplementation of Defect Application
 */
public class defectDAOImplementation implements defectDAO {

    int status = 0;

    // Create MySQLConnection object for DataBase connection
    MySQLConnection MSC = new MySQLConnection();

    // Create instance of logger in defectDAOImplementation class
    static final Logger log = Logger.getLogger(defectDAOImplementation.class);

    Connection connection;

    /**
     * method implementation for createDefectDAO
     * @param dm
     * @return
     */
    public int createDefect(defectModel dm) {

        log.info("In Create Defect DAO");

        // Call getConnection method from MySQLConnection class
        connection = MSC.getConnection();

        try {
            // Set setAutoCommit to false so not to commit after every command
            connection.setAutoCommit(false);

            // Enable to send SQL command and insert data to database
            PreparedStatement ps = connection.prepareStatement("insert into defect(defectDescription,category,assignedTo,priority,status) values(?,?,?,?,?)");

            // Set the values to be inserted to the database
            ps.setString(1,dm.getDescription());
            ps.setString(2,dm.getCategory());
            ps.setString(3,dm.getAssignedTo());
            ps.setString(4,dm.getPriority());
            ps.setString(5,dm.getStatus());

            // Execute query for insertion
            status =  ps.executeUpdate();

            // Call to commit the changes
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
            try {
                // Update to the database
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                log.error(e);
            }
        }

        return status;

    }

    /**
     * method implementation for retrieving defects by Assigned To
     * @param assignedTo
     * @return
     */
    public List<defectModel> retrievingByAssignedTo(String assignedTo) {

        log.info("In Retrieve by assigned to Defect DAO");

        List<defectModel> dmList = new ArrayList<defectModel>();

        // Call getConnection method from MySQLConnection class
        connection = MSC.getConnection();

        try {

            // Enable to send SQL command and receive data from database
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM defect WHERE assignedTo=?");

            // Set the value to the database
            ps.setString(1,assignedTo);

            // Execute query of retrieving values from the database and returning object to ResultSet parameter
            ResultSet rs = ps.executeQuery();

            // Retrieve the next row of a SQL ResultSet, and returns whether there is a next row
            while(rs.next()){

                // Create an object for defectModel
                defectModel dm = new defectModel();

                // Retrieve values from the database
                dm.setId(rs.getInt(1));
                dm.setDescription(rs.getString(2));
                dm.setCategory(rs.getString(3));
                dm.setAssignedTo(rs.getString(4));
                dm.setPriority(rs.getString(5));
                dm.setStatus(rs.getString(6));

                // Add the retrieved values to list
                dmList.add(dm);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
        }

        return dmList;
    }

    /**
     * method implementation for deleting multiple defects
     * @param Id
     * @return
     */
    public int deleteDefect(String[] Id) {

        log.info("In Delete Defect DAO");

        // Call getConnection method from MySQLConnection class
        connection = MSC.getConnection();

        try {

            for(int i = 0 ; i < Id.length ; i++) {

                // Enable to send SQL command and deleting data from database
                PreparedStatement ps = connection.prepareStatement("DELETE FROM defect WHERE Id=?");

                // Set the value to be deleted
                ps.setInt(1, Integer.parseInt(Id[i]));

                // Execute query for updating database
                status = ps.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error(e);
        }

        return status;

    }
}
