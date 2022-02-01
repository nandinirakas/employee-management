package com.employee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:2020/employee";
    private static final String DATABASE_NAME = "postgres";
    private static final String DATABASE_PASSWORD = "root123";
    
    public Connection getConnection() {
        Connection connection = null;

        try {
          connection = DriverManager.getConnection(JDBC_URL, DATABASE_NAME, DATABASE_PASSWORD);
        } catch (SQLException exception) {
          System.out.println("Exception");
        }
        return connection;
    }
}
