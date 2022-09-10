package com.example.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class DataBase {
    private Connection connection; // connection to the database
    private final String database; // name of the database
    private final String user; // username
    private final String password; // password

    public DataBase(String database, String user, String password) {
        this.database = database;
        this.user = user;
        this.password = password;
        System.out.println("Connected to database: " + database);

        createFile(database);
        System.out.println("Database  log file created");
    }

    public static void main(String[] args) {
        System.out.println("Everything is ok!");
    }


    public void createDatabase(String dbName) throws SQLException { // create a new database with the name dbName and connect to it
        openConnection(); // connect to the MySQL database
        executeQuery("CREATE DATABASE " + dbName); // create a new database with the name dbName
        System.out.println("Database " + dbName + " created successfully"); // print a message to the console
        closeConnection(); // close the connection to the MySQL database
    }

    public void executeQuery(String query) throws SQLException { // execute a query

        openConnection();
        assert connection != null; // check if the connection is not null
        Statement statement = connection.createStatement();
        statement.executeUpdate(query); // execute the query
        System.out.println("Query executed successfully "); // print a message to the console

        closeConnection();

        writeInFile("the command '" + query + "' was executed successfully "); // write the query in the file

    }

    public void getData(String query) throws SQLException { // get the data from the column columns of the query
        openConnection();
        assert connection != null; // check if the connection is not null
        Statement statement = connection.createStatement(); // create a statement to execute the query
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        System.out.println("Query executed successfully "); // print a message to the console
        // return the result of the query
        int columns = rsmd.getColumnCount();
        System.out.println("DATA:");
        for (int i = 0; i < columns; i++) {
            System.out.print(rsmd.getColumnName(i + 1) + " ");
        }
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= columns; i++) {
                System.out.print(resultSet.getString(i) + " ");
            }
            System.out.println();
        }
        closeConnection();

        writeInFile("the command " + query + " was executed successfully "); // write the query in the file
    }

    // Create a method to connect to the MySQL database (Users)
    private void openConnection() { // connect to the MySQL database
        // connect to the database
        try { // try to connect to the database
            // connect way #1
            String url = "jdbc:mysql://localhost:3306/" + database; // url of the database

            connection = DriverManager.getConnection(url, user, password); // connect to the database
            if (connection != null) { // check if the connection is not null
                System.out.println("Connected to the database " + connection.getCatalog()); // print a message to the console
            }
        } catch (SQLException ex) { // catch the exception if the connection is not successful
            System.out.println("An error occurred. Maybe user/password is invalid");
        }
    }

    // Create a method to close the connection to the MySQL database (Users)
    private void closeConnection() { // close the connection to the MySQL database
        try {
            if (connection != null && !connection.isClosed()) { // check if the connection is not null and is not closed
                connection.close(); // close the connection to the database
                System.out.println("Connection to the database " + database + " closed");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void createFile(String database) {
        try {
            File file = new File(database + "Log.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            System.out.println("Path: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void writeInFile(String str) {

        try {
            FileWriter myWriter = new FileWriter(database + "Log.txt", true);
            myWriter.write(str + "\n-- in the database " + database + " at " + java.time.LocalDateTime.now() + "\n\n");
            System.out.println("Successfully wrote to the file.");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public <Things> void writeInFile(Things[] strs) {

        try {
            FileWriter myWriter = new FileWriter(database + "Log.txt", true);
            for (Things s : strs) {
                myWriter.write(s + "\n-- saved in the database " + database + " at " + java.time.LocalDateTime.now() + "\n\n");
            }
            System.out.println("Successfully wrote to the file.");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
