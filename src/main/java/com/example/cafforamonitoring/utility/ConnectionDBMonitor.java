package com.example.cafforamonitoring.utility;

import java.sql.*;

public class ConnectionDBMonitor {
    private static final String URL = "jdbc:mariadb://localhost:3306/caffora_monitor_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    static {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException("Driver MariaDB non trovato!", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}