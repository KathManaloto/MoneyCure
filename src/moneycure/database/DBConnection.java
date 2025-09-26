package moneycure.database;

import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:moneyCure.db";

    public static Connection getConnection() throws SQLException {

        java.io.File dbFile = new java.io.File("moneyCure.db");
        System.out.println("Connecting to DB at: " + dbFile.getAbsolutePath());

        return DriverManager.getConnection(URL);
    }
}
