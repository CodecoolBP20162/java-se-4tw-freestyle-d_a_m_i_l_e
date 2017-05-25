package com.codecool.yourcookbook.connection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * <h1>JDBCConnection class</h1> for setting up connection to SQL database
 */
public abstract class JDBCConnection {

    private final String DATABASE = "jdbc:postgresql://localhost:5432/yourcookbook";
    private final String DB_USER = getUserData(1);
    private final String DB_PASSWORD = getUserData(2);

    /**
     * Creates a connection to the given database
     *
     * @return a connection to the given database
     * @throws SQLException If a database access error occurs
     */
    public java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    /**
     * Generates a statement that queries the database
     *
     * @param query a String to be used as a query
     * @return the generated result set
     * @throws SQLException If database access error occurs or other errors
     */
    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

    /**
     * Reads the username and password from the given file
     *
     * @param textLine Lines of the file
     * @return the data as a String from the user.txt where username and password are saved
     */
    public String getUserData(int textLine) {
        String UserOrPassword = null;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("src/main/resources/connection/user.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        int counter = 0;
        try {
            while ((line = in.readLine()) != null) {
                counter++;
                if (counter == textLine) {
                    UserOrPassword = line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UserOrPassword;
    }

}