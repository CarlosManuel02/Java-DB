# Java-DB


Java-DB is a simple database file for Java. It is designed to be easy to use and to be able to store any kind of data. It is also designed to be able to store data in a file or in a database.
___
## Usage

### Creating a DataBase instance

To create a database, you need to create a new instance of the `DataBase` class. To do that you need to pass the name of the `database`, the `user` and the `password`.
example:
```java
DataBase db = new DataBase("dbName", "user", "password");
```
the target has to be a MySQL database.

___
### Executing a query

To execute a query, you need to call the `executeQuery` method of the `DataBase` class. This method takes a `String` as a parameter, which is the query to execute. The query can be any SQL query using the `MySQL` syntax.
The `executeQuery` also writes the query to the `log` file using the `writeInFile` method, the `log` file is located in the same path as the project.

example:
```java
db.executeQuery("SELECT * FROM table");
```

