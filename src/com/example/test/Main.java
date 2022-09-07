package com.example.test;

import java.sql.SQLException;

public class Main {
    // connect to tha MySQL database Users and save the user in the database using the saveInDatabase() method
    public static void main(String[] args) throws SQLException {
        DataBase db = new DataBase("Users");

        Users user = new Users("John", "john@gmail.com", "123456", "123456789", "123 Main Street", "New York", "NY", 12345);
        Users user2 = new Users("Emely", "emely@gmail.com", "123456", "124456789", "123 Main Street", "New York", "NY", 12345);

        db.saveInDatabase("INSERT INTO Users (name, email, passaword, phone, address, city, state, zip) VALUES ('" + user.getName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getPhone() + "', '" + user.getAddress() + "', '" + user.getCity() + "', '" + user.getState() + "', " + user.getZip() + ")");

        user.setPassword("654321");
        db.updateInDatabase("UPDATE Users SET name = '" + user.getName() + "', email = '" + user.getEmail() + "', passaword = '" + user.getPassword() + "', phone = '" + user.getPhone() + "', address = '" + user.getAddress() + "', city = '" + user.getCity() + "', state = '" + user.getState() + "', zip = " + user.getZip() + " WHERE email = '" + user.getEmail() + "'");

        db.saveInDatabase("INSERT INTO Users (name, email, passaword, phone, address, city, state, zip) VALUES ('" + user2.getName() + "', '" + user2.getEmail() + "', '" + user2.getPassword() + "', '" + user2.getPhone() + "', '" + user2.getAddress() + "', '" + user2.getCity() + "', '" + user2.getState() + "', " + user2.getZip() + ")");
    }
}

