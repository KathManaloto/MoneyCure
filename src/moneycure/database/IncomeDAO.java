package moneycure.database;

import moneycure.model.*;
import java.sql.*;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;

public class IncomeDAO {

    private static final Logger LOGGER = Logger.getLogger(IncomeDAO.class.getName());

    public boolean addIncome(Income income){
        String sql = "INSERT INTO income (\"date\", incomeSource, amount, notes) VALUES (?,?,?,?)";

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1, income.getDate());
            preparedStatement.setString(2, income.getIncomeCombo());
            preparedStatement.setDouble(3, income.getAmount());
            preparedStatement.setString(4, income.getNotes());

            int rows = preparedStatement.executeUpdate();
            LOGGER.info("Income added successfully,");

            return rows > 0 ;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,"Error adding income",e);
            return false;
        }
    }

    public Map<String, Double> getMonthlyIncome(Month month, int year){

        Map<String, Double> incomes = new HashMap<>();
        String sql = "SELECT incomeSource, SUM(amount) FROM income " +
                     "WHERE strftime('%m', date) = ? " +
                     "AND strftime('%Y', date) = ? " +
                     "GROUP BY incomeSource";
    }
}
