package com.account.Dao;

import com.account.model.Account;
import org.junit.Test;

import java.sql.Array;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class AccountDaoImplTest {

    AccountDaoImpl adi=new AccountDaoImpl();

    @Test
    public void testDeleteAccount()throws SQLException
    {

        String Id="1 2";
        String multiple[]=Id.split(" ");

        try{

            adi.deleteAccount(multiple);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
@Test
    public void testCreateAccount()throws SQLException{
        Account account=new Account();
        account.setFname("ankit");
        account.setLname("dixit");
        account.setBankname("banglore");
        account.setBranch("marathali");
        account.setAddress("baglore");
        account.setPhone(1234);

        adi.createAccountDao(account);
        try {
            adi.createAccountDao(account);
        }
        catch(SQLException e)
        {
         System.out.println("error");
        }
    }

    @Test
    public void testRetrieveAccount()throws SQLException
    {
        String Fname="aman";



           List list = adi.retrieveAccountDao(Fname);
           assertEquals(0,list.size());


    }
}