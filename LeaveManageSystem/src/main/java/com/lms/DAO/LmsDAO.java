package com.lms.DAO;

import com.lms.Model.LmsModel;
import java.sql.SQLException;
import java.util.List;

public interface LmsDAO {
    int createLmsDAO(LmsModel lms) throws SQLException;
    //int updateLmsDAO(modelTicket ticket) throws SQLException;
    int deleteLmsDAO(LmsModel ticketId) throws SQLException;
    public List<LmsModel> retrieveByEmpId(int EmpId) throws SQLException;
}
