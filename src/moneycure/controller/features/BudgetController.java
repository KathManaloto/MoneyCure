//Reads input from the View, builds a Model, calls DAO.
package moneycure.controller.features;

import moneycure.database.*;
import moneycure.model.*;
import moneycure.view.feature.*;

import javax.swing.*;
import java.util.*;
import java.util.logging.*;

public class BudgetController {

    // ===== FIELDS =====
    private final BudgetDAO budgetDAO;
    private final BudgetPanel budgetPanel;
    private final DashboardPanel dashboardPanel;

    private static final Logger LOGGER = Logger.getLogger(BudgetController.class.getName());

    // ===== CONSTRUCTOR =====
    public BudgetController(BudgetPanel budgetPanel, BudgetDAO budgetDAO, DashboardPanel dashboardPanel) {
        this.budgetPanel = budgetPanel;
        this.budgetDAO = budgetDAO;
        this.dashboardPanel = dashboardPanel;

        LOGGER.info("BudgetController initialized!");
        initController();
        loadRecentBudgets();
    }

    // ===== INIT CONTROLLER =====
    private void initController() {
        budgetPanel.getBtnAddBudget().addActionListener(e -> addBudget());
    }

    // === ON ADD BUTTON CLICK ===
    private void addBudget() {

        try {
            // DATE
            String date = budgetPanel.getSelectedDate();

            if (date.isEmpty()) {
                JOptionPane.showMessageDialog(
                    budgetPanel,
                    "Date is required.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // CATEGORY
            ExpenseBudgetCategory selected = (ExpenseBudgetCategory) budgetPanel.getBudgetCategoryCombo().getSelectedItem();
            String category = selected != null ? selected.getName() : "OTHERS";

            // AMOUNT
            String amountText = budgetPanel.getTxtAmountBudget().getText().trim();
            double amount;

            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(
                    budgetPanel,
                    "Please enter an amount.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                amount = Double.parseDouble(amountText);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                    budgetPanel,
                    "Amount must be a valid number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            // NOTES
            String notes = budgetPanel.getTxtNotesBudget().getText();

            // CREATE EXPENSE OBJECT
            Budget budget = new Budget(date, category, amount, notes);

            // SAVE TO DATABASE
            boolean success = budgetDAO.addBudget(budget);

            if (success) {
                JOptionPane.showMessageDialog(budgetPanel, "Budget added!");

                loadRecentBudgets();
                budgetPanel.clearFields();
                dashboardPanel.refreshData();

            } else {
                JOptionPane.showMessageDialog(budgetPanel, "Failed to add budget.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    budgetPanel,
                    "Error adding expense: " + ex.getLocalizedMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE
            );
            LOGGER.log(Level.SEVERE,"Error adding budget",ex);
        }
    }

    private void loadRecentBudgets() {

        budgetPanel.clearBudgetTable();
        List<Budget> recentBudget = budgetDAO.getBudget(10);

        for (Budget budget : recentBudget) {
            budgetPanel.addBudgetToTable(
                budget.getDate(),
                budget.getCategory(),
                budget.getAmount(),
                budget.getNotes()
            );
        }
    }
}