package com.account.controller;

import com.account.model.Account;
import com.account.service.AccountServiceImpl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class AccountControllerTest {

     Account account=new Account();
     AccountServiceImpl Aimpl=new AccountServiceImpl();
    @Test
    public void doPost() {
        account.setId(1);
        account.setFname("aman");
        account.setLname("dixit");
        account.setBankname("SBI");
        account.setBranch("hyd");
        account.setAddress("hitch city");
        account.setPhone(244124);
        try {
            int status=Aimpl.createAccountService(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doGet() {

        String Fname="aman";
        List<Account> list=Aimpl.retriveByName(Fname);
        //assertEquals(5,list.size());
    }

    @Test
    public void doDelete() {
        String Id="1 2";
        String multiple[]=Id.split(" ");
        try {
            int deltestatus=Aimpl.deleteAccount(multiple);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}