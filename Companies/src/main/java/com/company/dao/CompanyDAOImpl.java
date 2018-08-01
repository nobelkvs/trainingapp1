package com.company.dao;

import com.company.model.Company;
import com.company.utils.MysqlConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class for CompanyDAO
 */

public class CompanyDAOImpl implements CompanyDAO {
    static final Logger log = Logger.getLogger(CompanyDAOImpl.class);

    private static final String QUERY_TO_INSERT = "INSERT into company(name, employees, head_office) values(?,?,?)";
    private static final String QUERY_TO_RETRIVE = "SELECT * FROM company WHERE head_office = ?";
    private static final String QUERY_TO_DELETE = "DELETE FROM company WHERE company_id = ?";

    MysqlConnection mysqlConnection = new MysqlConnection();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    /**
     * Inserting details into database
     * @param company
     * @return
     */

    public int insertDetails(Company company) {
        int insertStatus = 0;

        try {
            //Getting the connection from MysqlConnection class
            connection = mysqlConnection.getConnection();

            preparedStatement = connection.prepareStatement(QUERY_TO_INSERT);

            //Setting the positional parameters
            preparedStatement.setString(1, company.getName());
            preparedStatement.setLong(2, company.getEmployees());
            preparedStatement.setString(3, company.getHeadOffice());

            //Executing query
            insertStatus = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertStatus;
    }


    /**
     *Retrieve details from database
     * @param HeadOffice
     * @return
     */
    public List retriveByName(String HeadOffice) {
        List list = new ArrayList();
        log.info("inside retrive in dao impl");
        try {
            //Getting the connection from MysqlConnection class
            connection = mysqlConnection.getConnection();
            preparedStatement = connection.prepareStatement(QUERY_TO_RETRIVE);

            //Setting the positional parameters
            preparedStatement.setString(1, HeadOffice);

            //Executing query
            resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                log.info("inside while");
                log.info(resultSet);
                Company company = new Company();

                //Setting values to the model and adding it to the list
                company.setId(resultSet.getInt(1));
                company.setName(resultSet.getString(2));
                company.setEmployees(resultSet.getLong(3));
                company.setHeadOffice(resultSet.getString(4));
                list.add(company);
                log.info("list :" + list);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }


        return list;
    }




    /**
     *Method to deleting the records from database
     * @param id
     * @return
     */
    public int deleteCompanies(String[] id) {
        int deleteStatus = 0;
        log.info("inside delete in dao impl");
        try {
            //Getting the connection from MysqlConnection class
            connection = mysqlConnection.getConnection();

            //Setting the positional parameters and executing the query
            for (int i = 0; i < id.length; i++) {
                preparedStatement = connection.prepareStatement(QUERY_TO_DELETE);
                preparedStatement.setInt(1, Integer.parseInt(id[i]));
                deleteStatus = preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.warn(e);
        }
        log.info("status :" + deleteStatus);


        return deleteStatus;
    }


}
