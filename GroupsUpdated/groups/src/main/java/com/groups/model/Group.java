package com.groups.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Group implements Serializable {
    private Integer gid;
    private String groupName;
    private String Owner;
    private String sendAs;
    private String feedback;
}
