package moneycure.database;

import java.sql.*;

public class SetupDatabase {

    public static void createTables() {

        String sqlExpenses =
                "CREATE TABLE IF NOT EXISTS expenses ("      +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "date TEXT NOT NULL, "                   +
                    "category TEXT NOT NULL, "               +
                    "amount REAL NOT NULL, "                 +
                    "notes TEXT)";

        String sqlBudget =
                "CREATE TABLE IF NOT EXISTS budget ("        +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "date TEXT NOT NULL, "                   +
                    "category TEXT NOT NULL, "               +
                    "amount REAL NOT NULL, "                 +
                    "notes TEXT)";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("Using DB file at: " +
                    new java.io.File("moneyCure.db").getAbsolutePath());

            stmt.execute(sqlExpenses);
            stmt.execute(sqlBudget);

            System.out.println("Checked: 'expenses' and 'budget' tables are ready!");

        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
