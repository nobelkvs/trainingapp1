package com.lms.Model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;


@Getter @Setter @ToString
public class LmsModel implements Serializable {
    private Integer EmpId;
    private String FromDate;
    private String ToDate;
    private String LeaveType;
   // private String LeaveComm;
    private String Comments;
    private Integer No_Of_Days;


}
