package com.company.service;

import com.company.dao.CompanyDAO;
import com.company.dao.CompanyDAOImpl;
import com.company.model.Company;

import java.util.List;

/**
 * implementation class for CompanyService
  
 */
public class CompanyServiceImpl implements CompanyService {

    //service implementation for retrieve
    public List retrieveCompanyDetailsByName(String HeadOffice) {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        List list = companyDAO.retriveByName(HeadOffice);
        return list;

    }

    //service implementation for inserting
    public int createCompany(Company company) {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        int insertStatus = companyDAO.insertDetails(company);
        return insertStatus;
    }

    //service implementation for deleting
    public int deleteMultipleComp(String[] id) {
        CompanyDAO companyDAO = new CompanyDAOImpl();
        int deleteStatus = companyDAO.deleteCompanies(id);
        return deleteStatus;
    }
}
