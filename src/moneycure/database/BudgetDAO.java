//Handles all database access for budget -
//Takes budget objects and turns them into SQL rows (and vice versa).
package moneycure.database;

import moneycure.model.*;

import java.sql.*;
import java.time.Month;
import java.util.*;
import java.util.logging.*;

public class BudgetDAO {

    private static final Logger LOGGER = Logger.getLogger(BudgetDAO.class.getName());

    // ===== CREATE/ADD DATA =====
    public boolean addBudget(Budget budget) {

        String sql = "INSERT INTO budget (date, category, amount, notes) VALUES (?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, budget.getDate());
            preparedStatement.setString(2, budget.getCategory());
            preparedStatement.setDouble(3, budget.getAmount());
            preparedStatement.setString(4, budget.getNotes());

            int rows = preparedStatement.executeUpdate();
            LOGGER.info("Budget added successfully");
            return rows > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,"Error adding budget",e);
            return false;
        }
    }

    // ===== READ DATA (all or recent) =====
    public List<Budget> getBudget(Integer limit) {
        List<Budget> list = new ArrayList<>();

        String sql = "SELECT date, category, amount, notes FROM budget " +
                "ORDER BY date DESC, id DESC";

        if (limit != null) {
            sql += " LIMIT ?";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            if (limit != null) {
                preparedStatement.setInt(1, limit);
            }

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    list.add(new Budget(
                            rs.getString("date"),
                            rs.getString("category"),
                            rs.getDouble("amount"),
                            rs.getString("notes")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //actual list of budget transactions
    public List<Budget> getBudgetsByMonth(Month month, int year) {
        String sql = "SELECT date, category, amount, notes FROM budget " +
                "WHERE strftime('%m', date) = ? " +
                "AND strftime('%Y', date) = ? " +
                "ORDER BY date ASC";

        List<Budget> budgets = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, String.format("%02d", month.getValue()));
            preparedStatement.setString(2, String.valueOf(year));

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                budgets.add(new Budget(
                        rs.getString("date"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("notes")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return budgets;
    }

    // totals per category
    public Map<String, Double> getMonthlyBudget(Month month, int year) {
        Map<String, Double> result = new HashMap<>();

        String sql = "SELECT category, SUM(amount) AS total " +
                "FROM budget " +
                "WHERE strftime('%m', date) = ? " +
                "AND strftime('%Y',date) = ? " +
                "GROUP BY category";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, String.format("%02d",month.getValue()));
            preparedStatement.setString(2,String.valueOf(year));

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                result.put(
                        rs.getString("category"),
                        rs.getDouble("total")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
