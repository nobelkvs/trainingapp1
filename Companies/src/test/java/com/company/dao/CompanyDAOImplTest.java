package com.company.dao;

import com.company.model.Company;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class CompanyDAOImplTest {
    CompanyDAOImpl companyDAO = new CompanyDAOImpl();
    int status = 0;

   @Test
    public void insertDetails() {

        Company company = new Company();
        company.setName("TCS");
        company.setEmployees(Long.parseLong("1500"));
        company.setHeadOffice("Bangalore");
        status = companyDAO.insertDetails(company);
        assertEquals(1, status);
    }

   @Test
    public void retriveByName() {
        List list = new ArrayList();
        list = companyDAO.retriveByName("hyd");
        assertEquals(list.size(), list.size());
    }

    @Test
    public void deleteCompanies() {
        String id = "520";
        status = companyDAO.deleteCompanies(id.split(","));
        assertEquals(0, status);
    }

}