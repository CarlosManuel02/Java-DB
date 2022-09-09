# Java-DB


## Introduction

This is a simple database management system written in Java using `MySQL` as the backend. It uses a
custom serialization format to store the data on disk. It supports
basic SQL commands, such as `CREATE`, `SELECT`, `INSERT`, `UPDATE`,
`DELETE`, `DROP`, `ALTER`, `SHOW`, `DESCRIBE`, `USE`, `CREATE INDEX`,
`DROP INDEX`, and `CREATE VIEW`.

The database supports a subset of the SQL standard. The database is not meant for
production usage.


### Requirements

* Java 8 or newer
* MySQL 5.7 or newer
* MySQL Connector/J 8.0.11 or newer


### Installation Instructions

1. Install Java 8 or newer and MySQL 5.7 or newer.
2. Install MySQL Connector/J 8.0.11 or newer.
3. Clone this repository.
4. In the case of IntelliJ IDEA, open the project tab and select the `External Libraries` tab. right-click on the `External Libraries` tab and select `Open library settings`. Click on `Libraries` and then click on the `+` button. Select `Java` and then select the `DataBase.jar` file. Click `Apply` and then `OK`.
5. In the case of Eclipse, right-click on the project and select `Build Path` and then `Configure Build Path`. Click on the `Libraries` tab and then click on the `Add External JARs` button. Select the `DataBase.jar` file. Click `Apply` and then `OK`.
6. Read the usage instructions below.

___
___

## Methods

- `createDatabase(String dbName)` - Creates a database with the name `dbName`.
- `executeQuery(String query)` - Executes a query that the user inputs.
- `getData(String query, int column)` - Returns the data from the query as a `ResultSet`.
- `openConnection()` - Opens a connection to the database.
- `closeConnection()` - Closes the connection to the database.
- `createFile()` - Creates a file for the `log` of the database.
- `writeInFile(String text)` - Writes the query's that the user inputs in the `log` file.
- `writeInFile(Thing strs)` - Writes the query's that the user inputs in the `log` file.
___
## Usage

### Creating a DataBase instance

To create a database, you need to create a new instance of the `DataBase` class. To do that you need to pass the name of the `database`, the `user` and the `password`.
example:
```java
DataBase db = new DataBase("dbName", "user", "password");
```
the target has to be a **_MySQL_** database.

___
### Creating a Database

To create a database, you need to call the `createDatabase` method. This method takes a `String` as a parameter, which is the name of the database.
example:
```java
db.createDatabase("dbName");
```
___
### Executing a query

To execute a query, you need to call the `executeQuery` method of the `DataBase` class. This method takes a `String` as a parameter, which is the query to execute. The query can be any SQL query using the `MySQL` syntax.
The `executeQuery` also writes the query to the `log` file using the `writeInFile` method, the `log` file is located in the same path as the project.

example:
```java
db.executeQuery("SELECT * FROM table");
```
___
### Getting data from a query

To get data from a query, you need to call the `getData` method of the `DataBase` class. This method takes a `String` as a parameter, which is the query to execute, 
and an `int` that represents the number of the column to get the data from. The query can be any SQL query using the `MySQL` syntax.
The `getData` also writes the query to the `log` file using the `writeInFile` method, the `log` file is located in the same path as the project.

example:
```java
ResultSet rs = db.getData("SELECT * FROM table", 1);
```


### Opening a connection

To open a connection to the database, you need to call the `openConnection` method of the `DataBase` class. This method takes no parameters.
example:
```java
db.openConnection();
```
this method is called automatically when you run the `executeQuery` method.

___
### Closing a connection

To close a connection to the database, you need to call the `closeConnection` method of the `DataBase` class. This method takes no parameters.
example:
```java
db.closeConnection();
```
this method is called automatically when you run the `executeQuery` method.

___
### Creating a file

To create a file, you need to call the `createFile` method of the `DataBase` class. This method takes no parameters.
example:
```java
db.createFile();
```
The file is created in the same path as the project, the name of the file will be as the name of the `database` with the `Log.txt` extension.
This method is called automatically when you run the `writeInFile` method.
___
### Writing in a file

To write in a file, you need to call the `writeInFile` method of the `DataBase` class. This method takes a `String` as a parameter, which is the text to write in the file.
example:
```java
db.writeInFile("SELECT * FROM table");
```
___

## Dependencies

This project uses the `mysql-connector-java` dependency.
___

