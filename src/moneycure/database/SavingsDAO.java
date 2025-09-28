package moneycure.database;

import moneycure.model.*;
import java.sql.*;
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
}
