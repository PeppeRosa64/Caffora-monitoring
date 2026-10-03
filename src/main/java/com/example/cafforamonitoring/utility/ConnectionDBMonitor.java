package com.example.cafforamonitoring.utility;

import java.sql.*;

public class ConnectionDBMonitor {
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

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