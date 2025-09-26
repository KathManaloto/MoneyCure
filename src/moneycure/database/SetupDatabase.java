package moneycure.database;

import java.sql.*;
import java.util.logging.*;

public class SetupDatabase {

    private static final Logger LOGGER = Logger.getLogger(SetupDatabase.class.getName());

    // CREATE TABLES
    public static void createTables() {

        // EXPENSES TABLE
        String sqlExpenses =
            "CREATE TABLE IF NOT EXISTS expenses ("  +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "date TEXT NOT NULL, "                   +
            "category TEXT NOT NULL, "               +
            "amount REAL NOT NULL, "                 +
            "notes TEXT)";

        // BUDGET TABLE
        String sqlBudget =
            "CREATE TABLE IF NOT EXISTS budget ("    +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "date TEXT NOT NULL, "                   +
            "category TEXT NOT NULL, "               +
            "amount REAL NOT NULL, "                 +
            "notes TEXT)";

        String sqlIncome =
                "CREATE TABLE IF NOT EXISTS income ("     +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, "  +
                "date TEXT NOT NULL, "                    +
                "incomeSource TEXT NOT NULL, "            +
                "amount REAL NOT NULL, "                  +
                "notes TEXT)";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("Using DB file at: " +
                    new java.io.File("moneyCure.db").getAbsolutePath());

            stmt.execute(sqlExpenses);
            stmt.execute(sqlBudget);
            stmt.execute(sqlIncome);

            System.out.println("Checked: 'expenses', 'budget' and 'income' tables are ready!");

        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
            LOGGER.log(Level.SEVERE,"Error creating tables",e);
        }
    }
}
