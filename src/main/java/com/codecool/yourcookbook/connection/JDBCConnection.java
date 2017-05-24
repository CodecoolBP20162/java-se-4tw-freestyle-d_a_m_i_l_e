package com.codecool.yourcookbook.connection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public abstract class JDBCConnection {

    private final String DATABASE = "jdbc:postgresql://localhost:5432/yourcookbook";
    private final String DB_USER = getUserData(1);
    private final String DB_PASSWORD = getUserData(2);

    public java.sql.Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }

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
                if(counter == textLine) {
                    UserOrPassword = line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return UserOrPassword;
    }

}