package com.account.Dao;

import com.account.model.Account;

import java.sql.SQLException;
import java.util.List;

/**
* Defining method for createAccountDao
* Defining method for retrieveAccountDao
* Defining method for deleteAccount
 */
public interface accountDao {
    int createAccountDao(Account account) throws SQLException;
    List<Account> retrieveAccountDao(String Fname);
    int deleteAccount(String[] Id)throws  SQLException;
}
