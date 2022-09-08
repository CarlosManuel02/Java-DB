package com.example.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DataBase {
    private Connection connection = null;
    private final String database;
    private final String user;
    private final String password;

    public DataBase(String database, String user, String password) {
        this.database = database;
        this.user = user;
        this.password = password;
    }


    public void executeQuery(String query) throws SQLException {

        openConnection();

        assert connection != null;
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        System.out.println("Query executed successfully ");
        // return a ResultSet object
         ResultSet rs = statement.executeQuery(query);
        System.out.println(rs);
        // return a ResultSetMetaData object


        closeConnection();

        writeInFile("the command " + query + " was executed successfully ");

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
            File file = new File(database+ "-log.txt");
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

    public void writeInFile(String str) {
        createFile();

        try {
            FileWriter myWriter = new FileWriter("Users.txt", true);
            myWriter.write(str + "\nin the database " + database + " at " + java.time.LocalDateTime.now() + "\n\n");
            System.out.println("Successfully wrote to the file.");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeInFile(Users[] strs) {
        createFile();

        try {
            FileWriter myWriter = new FileWriter("Users.txt", true);
            for (Users user : strs) {
                myWriter.write(user.toString() + "\nsaved in the database " + database + " at " + java.time.LocalDateTime.now() + "\n\n");
            }
            System.out.println("Successfully wrote to the file.");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
