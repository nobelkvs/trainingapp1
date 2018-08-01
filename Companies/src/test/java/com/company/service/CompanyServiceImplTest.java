package com.company.service;

import com.company.model.Company;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class CompanyServiceImplTest {

    @Test
    public void retrieveCompanyDetailsByName() {
        List list =  new CompanyServiceImpl().retrieveCompanyDetailsByName("chennai");
        assertEquals(list.size(),list.size());
    }

    @Test
    public void createCompany() {
        Company company = new Company();
        company.setName("TCS");
        company.setEmployees(Long.parseLong("1500"));
        company.setHeadOffice("Bangalore");
        int status = new CompanyServiceImpl().createCompany(company);
        assertEquals(1,status);
    }

    @Test
    public void deleteMultipleComp() {
        String str = "1,2";
       int status = new CompanyServiceImpl().deleteMultipleComp(str.split(","));
       assertEquals(0,status);
    }

}