package com.account.service;

import com.account.Dao.AccountDaoImpl;
import com.account.Dao.accountDao;
import com.account.model.Account;

import java.sql.SQLException;
import java.util.List;

/**
 * implementing the the method defined in AccountService.java interface
 * using createAccountService method for inserting the data
 * using retrieveByName method fot retrieving the data from database
 * using deleteAccount method for deleting the account detail
 */
public class AccountServiceImpl implements AccountService {
    accountDao Accountdao = null;

/**
 * implementation of create method
 @return status
 */
    public int createAccountService(Account account) throws SQLException {
        Accountdao = new AccountDaoImpl();
        int status = Accountdao.createAccountDao(account);
        return status;
    }

/**
* implementing retrieve method
* @var Fname for retrieving 
* @return status

 */
    public List<Account> retriveByName(String Fname) {
        accountDao Accountdao = null;

        Accountdao=new AccountDaoImpl();
        List<Account> Status=Accountdao.retrieveAccountDao(Fname);
        return Status;
    }

/**
* implementing the delete method
* @var Id passing for deleting the account detail
* @return deletestatus

 */
    public int deleteAccount(String[] Id) throws SQLException {
        accountDao Accountdao = null;

        Accountdao=new AccountDaoImpl();
        int deleteStatus=Accountdao.deleteAccount(Id);
        return deleteStatus;
    }


}
