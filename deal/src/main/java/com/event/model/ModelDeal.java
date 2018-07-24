package com.event.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class ModelDeal {

    public  Integer dealId;
    public String dealName;
    public String ownerName;
    public String dealValue;
    public Integer probability;
    public String customerName;
    public String customerContact;
}
