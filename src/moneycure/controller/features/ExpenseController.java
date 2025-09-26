//Reads input from the View, builds a Model, calls DAO.
package moneycure.controller.features;

import moneycure.controller.MoneyCureController;
import moneycure.database.*;
import moneycure.model.*;
import moneycure.view.feature.*;

import javax.swing.*;
import java.util.List;
import java.util.logging.*;

public class ExpenseController {

    // ===== FIELDS =====
    private final ExpenseDAO expenseDAO;
    private final ExpensePanel expensePanel;
    private final DashboardPanel dashboardPanel;

    private static final Logger LOGGER = Logger.getLogger(ExpenseController.class.getName());

    // ===== CONSTRUCTOR =====
    public ExpenseController(ExpensePanel expensePanel, ExpenseDAO expenseDAO, DashboardPanel dashboardPanel) {
        this.expensePanel = expensePanel;
        this.expenseDAO = expenseDAO;
        this.dashboardPanel = dashboardPanel;

        LOGGER.info("ExpenseController initialized!");
        initController();
        loadRecentExpenses();
    }

    // ===== INIT CONTROLLER =====
    private void initController() {
        expensePanel.getBtnAddExpense().addActionListener(e -> addExpense());
    }

    // === ON ADD BUTTON CLICK ===
    private void addExpense() {

        try {
            // DATE
            String date = expensePanel.getSelectedDate();

                if (date.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        expensePanel,
                        "Date is required.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

            // CATEGORY
            ExpenseBudgetCategory selected = (ExpenseBudgetCategory) expensePanel.getExpensesCategoryCombo().getSelectedItem();
            String category = selected != null ? selected.getName() : "OTHERS";

            // AMOUNT
            String amountText = expensePanel.getTxtAmountExpenses().getText().trim();
            double amount;

                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        expensePanel,
                        "Please enter an amount.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);

                    return;
                }

                try {
                    amount = Double.parseDouble(amountText);

                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(
                                expensePanel,
                                "Amount must be greater than zero.",
                                "Validation Error",
                                JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                        expensePanel,
                        "Amount must be a valid number.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }

            // NOTES
            String notes = expensePanel.getTxtNotesExpenses().getText();

            // CREATE EXPENSE OBJECT
            Expense expense = new Expense(date, category, amount, notes);

            // SAVE TO DATABASE
            boolean success = expenseDAO.addExpense(expense);

                if (success) {
                    JOptionPane.showMessageDialog(expensePanel, "Expense added!");

                    loadRecentExpenses();
                    MoneyCureController.clearFields(expensePanel.getDateSpinner(),expensePanel.getTxtAmountExpenses(),expensePanel.getTxtNotesExpenses(),expensePanel.getExpensesCategoryCombo());
                    dashboardPanel.refreshData();

                } else {
                    JOptionPane.showMessageDialog(expensePanel, "Failed to add expense.");
                }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                expensePanel,
                "Error adding expense: " + ex.getLocalizedMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );

            LOGGER.log(Level.SEVERE,"Error adding expense",ex);
        }
    }

    private void loadRecentExpenses() {
        expensePanel.clearExpenseTable();

        List<Expense> recentExpenses = expenseDAO.getExpenses(10);

        for (Expense expense : recentExpenses) {
            expensePanel.addExpenseToTable(
                expense.getDate(),
                expense.getCategory(),
                expense.getAmount(),
                expense.getNotes()
            );
        }
    }
}
