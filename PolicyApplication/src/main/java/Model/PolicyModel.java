package Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This is a model class to hold the policy details.
 * In this class we used lombok library which automatically provides setters and getters by providing some annotations.
 */
@Setter
@Getter
public class PolicyModel {

    private Integer policyId;
    private Long policyAmount;
    private String policyHolderName;
    private String address;
    private  String email;
}
