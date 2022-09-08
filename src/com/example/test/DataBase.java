package com.example.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private Connection connection = null;
    private String sql = null;
    private Statement statement = null;
    private final String database;
    private final String user;
    private final String password;

    public DataBase(String database, String user, String password) {
        this.database = database;
        this.user = user;
        this.password = password;
    }

    // Save the instance of the user Class in the database
    public void saveInDatabase(String user, String query) throws SQLException {
        // connect to the database

        openConnection();

        sql = query;
        assert connection != null;
        statement = connection.createStatement();

        statement.executeUpdate(sql);
        System.out.println("User saved in the database Users");

        writeInFile(user);

        closeConnection();
    }


    // Update the user in the database
    public void updateInDatabase(String query) throws SQLException {
        // connect to the database
        openConnection();

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
                System.out.println("Connection to the database " + database + " closed");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private void createFile() {
        try {
            File file = new File("Users.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            System.out.println("Path: " + file.getAbsolutePath());
            System.out.println("Size: " + file.length() + " bytes");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeInFile(String user) {
        createFile();

        try {
            FileWriter myWriter = new FileWriter("Users.txt", true);
            myWriter.write(user + "\nsaved in the database " + database + " at " + java.time.LocalDateTime.now() + "\n\n");
            System.out.println("Successfully wrote to the file.");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeInFile(Users[] users) {
        createFile();

        try {
            FileWriter myWriter = new FileWriter("Users.txt", true);
            for (Users user : users) {
                myWriter.write(user.toString() + "\nsaved in the database " + database + " at " + java.time.LocalDateTime.now() + "\n\n");
            }
            System.out.println("Successfully wrote to the file.");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
