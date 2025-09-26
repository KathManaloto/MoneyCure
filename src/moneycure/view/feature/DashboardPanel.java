package moneycure.view.feature;

import moneycure.database.*;
import moneycure.view.*;
import moneycure.view.common.*;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.*;

public class DashboardPanel extends JPanel {

    // ===== FIELDS =====
    private final BudgetDAO budgetDAO;
    private final ExpenseDAO expenseDAO;

    private final MonthSelectorPanel monthPanel;
    private final int currentYear;

    private final SummaryPanel expensesPanel;
    private final ProgressBarsPanel progressBarsPanel;

    private Month currentMonth;
    private Map<String, Double> budgets;
    private Map<String, Double> expenses;

    // ===== CONSTRUCTOR =====
    public DashboardPanel() {
        setLayout(new BorderLayout(10, 0));
        setBackground(Color.LIGHT_GRAY);

            // ===== TOP PANEL =====
            JPanel topPanel = new JPanel(new GridLayout(1, 4, 10, 10));

                // ----- TopMonthPanel -----
                monthPanel = new MonthSelectorPanel();

                // ----- TopSummary panels -----
                SummaryPanel balancePanel = new SummaryPanel("Balance");
                expensesPanel = new SummaryPanel("Expenses");
                SummaryPanel savingsPanel = new SummaryPanel("Savings");

                Helper.stylePanelBackground(monthPanel, balancePanel, expensesPanel, savingsPanel);
                Helper.styleTopPanel(topPanel);

            topPanel.add(monthPanel);
            topPanel.add(balancePanel);
            topPanel.add(expensesPanel);
            topPanel.add(savingsPanel);

            // ===== PROGRESS PANEL =====
            budgetDAO    = new BudgetDAO();
            expenseDAO   = new ExpenseDAO();

            currentMonth = LocalDate.now().getMonth();
            currentYear  = LocalDate.now().getYear();

            budgets  = budgetDAO.getMonthlyBudget(currentMonth, currentYear);
            expenses = expenseDAO.getMonthlyExpenses(currentMonth, currentYear);

            progressBarsPanel = new ProgressBarsPanel(budgets, expenses);

            Helper.stylePanelBorder(progressBarsPanel, "BUDGET VS EXPENSES");

        add(topPanel, BorderLayout.NORTH);
        add(progressBarsPanel,BorderLayout.CENTER);
        updateSummary(expenses);
    }

    // ===== REFRESH DATA =====
    public void refreshData() {
        budgets  = budgetDAO.getMonthlyBudget(currentMonth,currentYear);
        expenses = expenseDAO.getMonthlyExpenses(currentMonth,currentYear);

        progressBarsPanel.updateData(budgets, expenses);

        double totalExpenses = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
        expensesPanel.setValue(totalExpenses);
        revalidate();
        repaint();
    }

    // ===== UPDATE SUMMARY PANEL ====
    private void updateSummary(Map<String, Double> expenses){
        double totalExpenses = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
        expensesPanel.setValue(totalExpenses);
    }

    // ===== GETTERS & SETTERS =====
    public MonthSelectorPanel getMonthPanel() { return monthPanel;}
    public void setCurrentMonth(Month month) { this.currentMonth = month; }
}
