package com.account.service;

import com.account.Dao.AccountDaoImpl;
import com.account.model.Account;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class AccountServiceImplTest {
    Account account=new Account();
    AccountDaoImpl Adao=new AccountDaoImpl();
    @Test
    public void testcreateAccount() {

        account.setId(1);
        account.setFname("aman");
        account.setLname("dixit");
        account.setBankname("SBI");
        account.setBranch("madhapur");
        account.setAddress("hitech city");
        account.setPhone(141413);

        int status= 0;
        try {
            status = Adao.createAccountDao(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1,status);
    }

    @Test
    public void testretriveByName() {


        String Fname="aman";
        List list=Adao.retrieveAccountDao(Fname);
       // assertEquals(5,list.size());

    }

    @Test
    public void testdeleteAccount() {
        String id="1 2";
        String multiple[]=id.split(" ");
        try {
            int status=Adao.deleteAccount(multiple);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //assertEquals(0,status);

    }
}