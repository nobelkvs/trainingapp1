package com.lms.Service;

import com.lms.Model.LmsModel;
import java.util.List;

public interface LmsService {


    public int createLmsService(LmsModel lms);

    public int updateLmsService(LmsModel lms);

    public List<LmsModel> retrieveByEmpId(int EmpId);

    public int deleteLmsService(int EmpId);
}

