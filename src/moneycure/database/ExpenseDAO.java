//Handles all database access for expenses -
//Takes Expense objects and turns them into SQL rows (and vice versa).
package moneycure.database;

import moneycure.model.*;
import java.sql.*;
import java.time.Month;
import java.util.*;

public class ExpenseDAO {

    // ===== CREATE/ADD DATA =====
    public boolean addExpense(Expense expense) {

        String sql = "INSERT INTO expenses (date, category, amount, notes) VALUES (?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, expense.getDate());
            preparedStatement.setString(2, expense.getCategory());
            preparedStatement.setDouble(3, expense.getAmount());
            preparedStatement.setString(4, expense.getNotes());

            int rows = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rows);
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===== READ DATA (all or recent) =====
    public List<Expense> getExpenses(Integer limit){
        List<Expense> list = new ArrayList<>();

        String sql = "SELECT date, category, amount, notes FROM expenses " +
                "ORDER BY date DESC, id DESC";

        if(limit != null){
            sql += " LIMIT ?";
        }

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            if(limit != null){
                preparedStatement.setInt(1,limit);
            }

            try(ResultSet rs = preparedStatement.executeQuery()){

                while(rs.next()){
                    list.add(new Expense(
                        rs.getString("date"),
                        rs.getString("category"),
                        rs.getDouble("amount"),
                        rs.getString("notes")
                    ));
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    // ===== READ DATA (Grouped by category, filtered by month) =====
    public Map<String,Double> getMonthlyExpenses(String yearMonth){

        Map<String,Double> result = new HashMap<>();

        String sql = "SELECT category, SUM(amount) AS total " +
                "FROM expenses " +
                "WHERE strftime('%Y-%m',date) = ?" +
                "GROUP BY category";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1,yearMonth);

            try(ResultSet rs = preparedStatement.executeQuery()){
                while(rs.next()){
                    result.put(
                            rs.getString("category"),
                            rs.getDouble("total")
                    );
                }
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public List<Expense> getExpensesByMonth(Month month, int year){
        String sql = "SELECT category, amount FROM expenses " +
                "WHERE strftime('%m',\"date\") = ? " +
                "AND strftime('%Y',\"date\") = ?";

        List<Expense> expenses = new ArrayList<>();

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setString(1,String.format("%02d",month.getValue()));
            preparedStatement.setString(2, String.valueOf(year));

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                expenses.add(new Expense(
                        rs.getString("category"),
                        rs.getDouble("amount")));
            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return expenses;
    }
}