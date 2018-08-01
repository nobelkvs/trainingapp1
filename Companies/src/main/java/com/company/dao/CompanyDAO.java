package com.company.dao;

import com.company.model.Company;

import java.util.List;

public interface CompanyDAO {
    //DAO interface for inserting
    int insertDetails(Company company);

    //DAO interface for retrieving
    List retriveByName(String HeadOffice);

    //DAO interface for deleting
    int deleteCompanies(String[] id);
}
