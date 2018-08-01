package com.bank.model;
public class BankModel {
    // Creating attributes for Bank Application
    private String name;
    private String branch;
    private String address;
    private Integer phno;
    private String email;

    /**
     *
     * @return
     */
    // Write get and set methods for the attributes
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhno() {
        return phno;
    }
    public void setPhno(Integer phno) {
        this.phno = phno;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
