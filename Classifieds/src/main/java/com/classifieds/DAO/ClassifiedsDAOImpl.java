package com.classifieds.DAO;

import com.classifieds.model.Classifieds;
import com.classifieds.service.ClassifiedsService;
import com.classifieds.service.ClassifiedsServiceImpl;
import com.classifieds.utils.MySqlConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class with methods to insert , retrieve and delete data to and from the database
 */
public class ClassifiedsDAOImpl implements ClassifiedsDAO {

    static final Logger log = Logger.getLogger(ClassifiedsDAOImpl.class);

    //query to insert values into table
    private static final String SQL_INSERT_INTO_Classifieds = "insert into classifiedTable(classifiedDescription,category,city) values(?,?,?)";

    //query to retrieve values from table
    private static final String SQL_RETRIEVE_Classifieds = "select * from classifiedTable where city=?";

    //query to delete table values
    private static final String SQL_DELETE_Classifieds = "delete from classifiedTable where id=?";

    //defining connection
    MySqlConnection c = new MySqlConnection();


    /**
     * Dao create method to insert records and return status
     *
     * @param classifieds
     * @return insertStatus
     * @throws SQLException
     */
    public int createClassifiedsDAO(Classifieds classifieds) throws SQLException {

        Connection con = null;

        int insertStatus = 0;

        try {

            //establishing connection
            con = c.getConnect();

        } catch (Exception e) {

            log.error(e);

            log.info("can not connect");
        }

        PreparedStatement ps = null;
        try {

            //AutoCommit is set to false so that changes should not be reflected until all the fields are entered

            con.setAutoCommit(false);

            ps = con.prepareStatement(SQL_INSERT_INTO_Classifieds);

            ps.setString(1, classifieds.getClassifiedDescription());
            ps.setString(2, classifieds.getCategory());
            ps.setString(3, classifieds.getCity());

            insertStatus = ps.executeUpdate();

            //As autocommit is set to false we have to commit the changes once all the fields are entered

            con.commit();

        } catch (Exception e) {

            log.error(e);

        }
        //It will returns the status weather the records are inserted or not

        return insertStatus;
    }

    /**
     * Dao retrive method to get the table data and return list of matched data from database
     *
     * @param city
     * @return list
     * @throws SQLException
     */
    public List<Classifieds> retrieveClassifiedsDAO(String city) throws SQLException {

        Connection con = null;

        List<Classifieds> list = new ArrayList<Classifieds>();

        try {

            //establishing connection
            con = c.getConnect();

        } catch (Exception e) {

            log.error(e);

            log.info("can not connect");
        }

        PreparedStatement ps = null;

        //defining resultset for retrieving table data
        ResultSet rs = null;

        try {

            ps = con.prepareStatement(SQL_RETRIEVE_Classifieds);

            ps.setString(1, city);

            rs = ps.executeQuery();

            while (rs.next()) {

                Classifieds cs = new Classifieds();
                cs.setId(rs.getInt(1));
                cs.setClassifiedDescription(rs.getString(2));
                cs.setCategory(rs.getString(3));
                cs.setCity(rs.getString(4));

                list.add(cs);
            }
        } catch (SQLException e) {

            log.error(e);

        }


        return list;
    }

    /**
     * Dao delete method to delete the records in database and return status
     *
     * @param id
     * @return deleteStatus
     * @throws SQLException
     */
    public int deleteClassifiedsDAO(String[] id) throws SQLException {

        Connection con = null;

        int deleteStatus = 0;

        try {

            //establishing connection
            con = c.getConnect();

        } catch (Exception e) {

            log.error(e);

            log.info("can not connect");
        }

        PreparedStatement ps = null;

        try {

            //AutoCommit is set to false so that changes should not be reflected until all the commands are entered

            con.setAutoCommit(false);

            //for multiple deletion

            for (int j = 0; j < id.length; j++) {

                ps = con.prepareStatement(SQL_DELETE_Classifieds);

                ps.setInt(1, Integer.parseInt(id[j]));

                deleteStatus = ps.executeUpdate();
            }

            //As autocommit is set to false we have to commit the changes once all the commands are entered

            con.commit();

        } catch (SQLException e) {

            log.error(e);
        }


        return deleteStatus;
    }
}
