package com.example.test;


import lombok.Data;




@Data
public class Users {


    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String state;
    private int zip;

    public Users(String name, String email, String password, String phone, String address, String city, String state, int zip) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }




}
