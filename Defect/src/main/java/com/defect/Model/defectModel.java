package com.defect.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Model of Defect Application
 * using setter and getter of lombok for set and get methods
 */
@Setter @Getter @ToString
public class defectModel {

    Integer Id;
    String description;
    String category;
    String assignedTo;
    String priority;
    String status;
}
