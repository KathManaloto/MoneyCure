package moneycure.database;

import moneycure.model.*;
import java.sql.*;
import java.time.Month;
import java.util.logging.*;

public class SavingsDAO {

    private final Logger LOGGER = Logger.getLogger(SavingsDAO.class.getName());

    public boolean addSavings(Savings savings) {
        String sql = "INSERT INTO savings (date, savingsType, amount, notes) VALUES (?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, savings.getDate());
            preparedStatement.setString(2, savings.getSavingsType());
            preparedStatement.setDouble(3, savings.getSavingsAmount());
            preparedStatement.setString(4, savings.getSavingsNotes());

            int rows = preparedStatement.executeUpdate();
            LOGGER.info("Savings added successfully.");

            return rows > 0;

        } catch (SQLException e) {

            LOGGER.log(Level.SEVERE, "Error adding savings", e);
            return false;
        }
    }

    public double getMonthlySavings(Month month, int year){
        String sql = "SELECT SUM(amount) FROM savings " +
                        "WHERE strftime('%m',date) = ? " +
                        "AND strftime('%Y', date) = ? ";

        double totalSavings = 0.0;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1, String.format("%02d", month.getValue()));
            preparedStatement.setString(2, String.valueOf(year));

            try(ResultSet rs = preparedStatement.executeQuery()){
                if(rs.next()){
                    totalSavings = rs.getDouble(1);
                }
            }
        }catch (SQLException e){
            LOGGER.log(Level.SEVERE,"Error fetching total savings",e);
        }

        return totalSavings;
    }
}
