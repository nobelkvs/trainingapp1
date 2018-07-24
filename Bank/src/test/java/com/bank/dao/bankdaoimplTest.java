package com.bank.dao;

import com.bank.model.bankmodel;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

public class bankdaoimplTest {
    bankdao bd=new bankdaoimpl();
    @Test
    public void createbankDAO() {
        bankmodel bm=new bankmodel();
        bm.setName("Pooja");
        bm.setBranch("hitec city");
        bm.setAddress("bangalore");
        bm.setPhno(122);
        bm.setEmail("lo@outlook.com");
        int create=0;
        try{
            create=bd.createbankDAO(bm);

        } catch (SQLException e) {
            e.printStackTrace();
        }
            assertEquals(1,create);
    }

    @Test
    public void deletebankDAO() {
        String phno="103";
        int delete=0;
        try{
            delete=bd.deletebankDAO(Integer.valueOf(phno));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(0,delete);
    }

    @Test
    public void retrivebankDAO() {
        List bl= new ArrayList<bankmodel>();
        bl=bd.retrivebankDAO("hanamkonda");
        assertEquals(0,bl.size());
    }

}