package com.weather.monitoring.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/weatherdb"; // Adjust as needed
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = "Mayuri@1512"; // Replace with your DB password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
