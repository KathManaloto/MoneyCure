package moneycure.database;

import moneycure.model.*;
import java.sql.*;
import java.time.*;
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

    public double getMonthlyIncomeTotal(Month month, int year){

        String sql = "SELECT SUM(amount) FROM income " +
                     "WHERE strftime('%m', date) = ? " +
                     "AND strftime('%Y', date) = ? ";

        double totalIncome = 0.0;

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql)){

            preparedStatement.setString(1, String.format("%02d",month.getValue()));
            preparedStatement.setString(2, String.valueOf(year));

            try (ResultSet rs = preparedStatement.executeQuery()){
                if(rs.next()){
                    totalIncome = rs.getDouble(1);
                }
            }

        } catch (SQLException e){
            LOGGER.log(Level.SEVERE,"Error fetching total income",e);
        }

        return totalIncome;
    }
}
