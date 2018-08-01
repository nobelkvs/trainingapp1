package com.account.service;

import com.account.Dao.AccountDaoImpl;
import com.account.Dao.accountDao;
import com.account.controller.AccountController;
import com.account.model.Account;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * implementing the the method defined in AccountService.java interface
 * using createAccountService method for inserting the data
 * using retrieveByName method fot retrieving the data from database
 * using deleteAccount method for deleting the account detail
 */
public class AccountServiceImpl implements AccountService {


static final Logger log=Logger.getLogger(AccountController.class);
/**
 * implementation of create method
 * @return status
 */
    public int createAccountService(Account account) throws SQLException {
        log.info("inside creating account");
        accountDao Accountdao = new AccountDaoImpl();
        int status = Accountdao.createAccountDao(account);
        return status;
    }

/**
* implementing retrieve method
* @var Fname for retrieving 
* @return status

 */
    public List<Account> retriveByName(String Fname) {
        log.info("inside retrieve account detail");
        accountDao Accountdao = new AccountDaoImpl();
        List<Account> Status=Accountdao.retrieveAccountDao(Fname);
        return Status;
    }

/**
* implementing the delete method
* @var Id passing for deleting the account detail
* @return deletestatus

 */
    public int deleteAccount(String[] Id) throws SQLException {
        log.info("inside delete account detail");
        accountDao Accountdao = new AccountDaoImpl();
        int deleteStatus=Accountdao.deleteAccount(Id);
        return deleteStatus;
    }


}
