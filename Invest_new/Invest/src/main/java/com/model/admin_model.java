package com.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serializable;

@Setter
@Getter
@ToString
/**
 * provides getters and setters methods for all the attributes of investement application
 */
public class admin_model implements Serializable {

    private String uname;
    private String password;
}