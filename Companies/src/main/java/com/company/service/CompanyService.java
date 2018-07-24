package com.company.service;

import com.company.model.Company;

import java.util.List;

public interface CompanyService {

    //Service interface for retrieving
    List retrieveCompanyDetailsByName(String HeadOffice);

    //Service interface for inserting
    int createCompany(Company company);

    //Service interface for deleting
    int deleteMultipleComp(String[] s);

}
