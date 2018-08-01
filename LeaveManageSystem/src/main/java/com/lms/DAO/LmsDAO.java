package com.lms.DAO;

import com.lms.Model.LmsModel;
import java.sql.SQLException;
import java.util.List;

public interface LmsDAO {
    int createLms(LmsModel lms) throws SQLException;

     List<LmsModel> retrieveByEmpName() throws SQLException;
}
