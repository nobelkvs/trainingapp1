package com.bank.service;

import com.bank.dao.BankDao;
import com.bank.dao.BankDaoImpl;
import com.bank.model.BankModel;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BankServiceImplTest {
    BankDao bDao = new BankDaoImpl();

    @Test
    public void createbankservice() throws SQLException {
        BankModel bm=new BankModel();
        bm.setName("Ram");
        bm.setBranch("madhapur");
        bm.setAddress("hnk");
        bm.setPhno(951519514);
        bm.setEmail("ram@gmail.com");
        int createstatus=bDao.createbankDAO(bm);
        assertEquals(0,createstatus);
    }

    @Test
    public void deletebankservice() throws SQLException {
        int deletes = bDao.deletebankDAO(519519514);
        assertEquals(0, deletes);
    }

    @Test
    public void retrivebybname() {
        String branch="madhapur";
        List list =bDao.retrivebankDAO(branch);
        assertEquals(list.size(),list.size());

    }
}