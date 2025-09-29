//Takes Expense objects and turns them into SQL rows (and vice versa).
package moneycure.database;

import moneycure.model.*;
import java.sql.*;
import java.time.*;
import java.util.*;
import java.util.logging.*;

public class ExpenseDAO {

    // ===== FIELDS =====
    private static final Logger LOGGER = Logger.getLogger(ExpenseDAO.class.getName());

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
            LOGGER.info("Expense added successfully.");

            return rows > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,"Error adding expense",e);
            return false;
        }
    }

    // ===== READ DATA (all or recent) =====
    public List<Expense> getExpenses(Integer limit){
        List<Expense> list = new ArrayList<>();

        String sql = "SELECT date, category, amount, notes FROM expenses " +
                     "ORDER BY date DESC, id DESC";

        if(limit != null){ sql += " LIMIT ?"; }

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            if(limit != null){ preparedStatement.setInt(1,limit); }

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
            LOGGER.log(Level.SEVERE,"Error retrieving expenses",e);
        }

        return list;
    }

    // totals per category
    public Map<String,Double> getMonthlyExpenses(Month month, int year){
        Map<String,Double> result = new HashMap<>();

        String sql = "SELECT category, SUM(amount) AS total " +
                     "FROM expenses " +
                     "WHERE strftime('%m', date) = ? " +
                     "AND strftime('%Y',date) = ? " +
                     "GROUP BY category";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1, String.format("%02d",month.getValue()));
            preparedStatement.setString(2,String.valueOf(year));

            try(ResultSet rs = preparedStatement.executeQuery()){
                while(rs.next()){
                    result.put(
                        rs.getString("category"),
                        rs.getDouble("total")
                    );
                }
            }

        } catch(SQLException e){
            LOGGER.log(Level.SEVERE,"Error retrieving expenses",e);
        }

        return result;
    }
}