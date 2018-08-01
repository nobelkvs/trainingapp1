package com.company.model;

//Model Class
public class Company {

   //Fields 
   private int id;
   private String name;
   private Long employees;
   private String headOffice;


    //Getters and Setters 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getEmployees() {
        return employees;
    }

    public void setEmployees(Long employees) {
        this.employees = employees;
    }

    public String getHeadOffice() {
        return headOffice;
    }

    public void setHeadOffice(String headOffice) {
        this.headOffice = headOffice;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                ", headOffice='" + headOffice + '\'' +
                '}';
    }
}
