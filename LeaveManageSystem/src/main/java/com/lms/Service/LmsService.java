package com.lms.Service;

import com.lms.Model.LmsModel;
import java.util.List;

public interface LmsService {


    public String createLms(LmsModel lms);

    public String updateLms(LmsModel lms);

    public List<LmsModel> retrieveByEmpName(String EmpName);

    public int deleteLms(String[] array);
}

