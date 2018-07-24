package com.account.service;

import com.account.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountService {
    int createAccountService(Account account)throws SQLException;
    List<Account> retriveByName(String Fname);
    int deleteAccount(String[] Id) throws SQLException;
}
