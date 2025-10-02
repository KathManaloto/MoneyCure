package moneycure.database;

import moneycure.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class TransactionDAO {

    public List<Transaction> getAllTransactions(){

        List<Transaction> transactions = new ArrayList<>();

        try(Connection conn = DBConnection.getConnection()){

            // income
            String sqlIncome = "SELECT date, incomeSource, amount, notes FROM income";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlIncome);
                ResultSet rs = preparedStatement.executeQuery()){

                while(rs.next()){
                    transactions.add(new Transaction(
                        LocalDate.parse(rs.getString("date")),
                        "Income",
                        rs.getString("incomeSource"),
                        rs.getDouble("amount"),
                        rs.getString("notes")
                    ));
                }
            }

            // savings
            String sqlSavings = "SELECT date, savingsType, amount, notes FROM savings";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlSavings);
                ResultSet rs = preparedStatement.executeQuery()){

                while(rs.next()){
                    transactions.add(new Transaction(
                        LocalDate.parse(rs.getString("date")),
                            "Savings",
                        rs.getString("savingsType"),
                        rs.getDouble("amount"),
                        rs.getString("notes")
                    ));
                }
            }

            // expenses
            String sqlExpenses = "SELECT date, category, amount, notes FROM expenses";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlExpenses);
                ResultSet rs = preparedStatement.executeQuery()){

                while(rs.next()){
                    transactions.add(new Transaction(
                        LocalDate.parse(rs.getString("date")),
                        "Expenses",
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("notes")
                    ));
                }
            }

            // budget
            String sqlBudget = "SELECT date, category, amount, notes FROM budget";

            try(PreparedStatement preparedStatement = conn.prepareStatement(sqlBudget);
                ResultSet rs = preparedStatement.executeQuery()){

                while(rs.next()){
                    transactions.add(new Transaction(
                        LocalDate.parse(rs.getString("date")),
                        "Budget",
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("notes")
                    ));
                }
            }

        } catch (SQLException e){
            e.getLocalizedMessage();
        }

        return transactions;
    }
}