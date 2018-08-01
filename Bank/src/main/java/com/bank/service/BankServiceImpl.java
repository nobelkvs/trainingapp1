package com.bank.service;
import com.bank.dao.BankDao;
import com.bank.dao.BankDaoImpl;
import com.bank.model.BankModel;
import java.sql.SQLException;
import java.util.List;
public class BankServiceImpl implements BankService {
    // Initialize dao object bd to null
    BankDao bd = null;

    /**
     *
     * @param obj
     * @return
     * @throws SQLException
     */
    // Method creation for createbankService and pass model object
    @Override
    public int createbankservice(BankModel obj) throws SQLException {
        bd = new BankDaoImpl();
        int status = bd.createbankDAO(obj);
        return status;

    }

    /**
     *
     * @param phno
     * @return
     * @throws SQLException
     */
    // Method creation for deletebankService and pass phone number through which we delete data
    public int deletebankservice(Integer phno) throws SQLException {
        bd=new BankDaoImpl();
        int status=bd.deletebankDAO(phno);
        return status;
    }

    /**
     *
     * @param branch
     * @return
     */
    // Method creation for retrievebankService and pass bank name through which we retrieve data
    public List<BankModel> retrivebybname(String branch) {
        bd=new BankDaoImpl();
        List<BankModel> status=bd.retrivebankDAO(branch);
        return status;
        }
}
