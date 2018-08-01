package com.bank.controller;

import com.bank.model.BankModel;
import com.bank.service.BankService;
import com.bank.service.BankServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BankControllerServletTest {
    BankService bs=new BankServiceImpl();
    @Test
    public void doPost() throws SQLException {
        String name="YAshwanth";
        String branch="dekalb";
        String address="hyd";
        Integer phno=505050500;
        String email="yl@gmail.com";
        BankModel bm=new BankModel();
        bm.setName(name);
        bm.setBranch(branch);
        bm.setAddress(address);
        bm.setPhno(phno);
        bm.setEmail(email);
        int create=bs.createbankservice(bm);
        assertEquals(0,create);
    }

    @Test
    public void doGet() {
        String branch="madhapur";
        List list=bs.retrivebybname(branch);
        assertEquals(list.size(),list.size());
    }

    @Test
    public void doDelete() throws SQLException {
        int delete=bs.deletebankservice(519519514);
        assertEquals(0,delete);
    }
}