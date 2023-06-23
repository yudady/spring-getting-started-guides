package io09.model;


import lombok.Data;

@Data
public class Employee {

    private long id;
    private String name;
    private String email;
    private String country;
    private int age;
    private String role;

}