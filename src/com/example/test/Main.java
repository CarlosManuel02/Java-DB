package com.example.test;

import java.sql.SQLException;

public class Main {
    // connect to tha MySQL database Users and save the user in the database using the saveInDatabase() method
    public static void main(String[] args) throws SQLException {
        Users.database = "Users";
        Users user = new Users("John", "john@gmail.com", "123456", "123456789", "123 Main Street", "New York", "NY", 12345);
        Users user2 = new Users("Emely", "emely@gmail.com", "123456", "124456789", "123 Main Street", "New York", "NY", 12345);
        user.saveInDatabase();

        user.setPassword("654321");
        user.updateInDatabase();
        user2.saveInDatabase();

    }
}
