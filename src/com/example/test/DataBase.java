package com.example.test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DataBase {
    private Connection connection = null;
    private String sql = null;
    private Statement statement = null;
    private final String database;

    public DataBase(String database){
        this.database = database;
    }

    // Save the instance of the user Class in the database
    public void saveInDatabase(String query) throws SQLException {
        // connect to the database

        openConnection();

        // The SQL query to insert the user in the database
        sql = query;
        assert connection != null;
        statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("User saved in the database Users");

        closeConnection();
    }


    // Update the user in the database
    public void updateInDatabase(String query) throws SQLException {
        // connect to the database
        openConnection();

        // The SQL query to update the user in the database
        sql = query;
        assert connection != null;
        statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("User updated in the database Users");

        closeConnection();

    }


    // Delete the user from the database
    public void deleteFromDatabase(String query) throws SQLException {
        // connect to the database
        openConnection();


        // The SQL query to delete the user from the database
        sql = query;

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
