package com.example.test;


import lombok.Data;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

@Data
public class Users {
    private Connection connection = null;
    private String sql = null;
    private Statement statement = null;
    protected static String database = null;

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


    // Save the instance of the user Class in the database
    public void saveInDatabase() throws SQLException {
        // connect to the database

        openConnection();

        // The SQL query to insert the user in the database
        sql = "INSERT INTO Users (name, email, passaword, phone, address, city, state, zip) " +
                "VALUES ('" + this.name + "', '" + this.email + "', '" + this.password + "', '" + this.phone + "', '" + this.address + "', '" + this.city + "', '" + this.state + "', " + this.zip + ")";

        assert connection != null;
        statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("User saved in the database Users");

        closeConnection();
    }


    // Update the user in the database
    public void updateInDatabase() throws SQLException {
        // connect to the database
        openConnection();

        // The SQL query to update the user in the database
        sql = "UPDATE Users SET name = '" + this.name + "', email = '" + this.email + "', passaword = '" + this.password + "', phone = '" + this.phone + "', address = '" + this.address + "', city = '" + this.city + "', state = '" + this.state + "', zip = " + this.zip + " WHERE email = '" + this.email + "'";

        assert connection != null;
        statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("User updated in the database Users");

        closeConnection();

    }


    // Delete the user from the database
    public void deleteFromDatabase() throws SQLException {
        // connect to the database
        openConnection();


        // The SQL query to delete the user from the database
        sql = "DELETE FROM Users WHERE phone = '" + this.phone + "'";

        assert connection != null;
        statement = connection.createStatement();

        statement.executeUpdate(sql);

        System.out.println("User deleted from the database Users");


        closeConnection();
    }

    // Create a method to connect to the MySQL database (Users)
    private void openConnection() {
        // connect to the database
        try {
            // connect way #1
            String url = "jdbc:mysql://localhost:3306/" + database;
            String user = "root";
            String password = "noway123";

            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database " + connection.getCatalog());
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        }
    }

    // Create a method to close the connection to the MySQL database (Users)
    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection to the database "+ database +" closed");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
