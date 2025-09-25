package moneycure.view.feature;

import moneycure.database.*;
import moneycure.view.*;
import moneycure.view.common.*;
import javax.swing.*;
import java.awt.*;
import java.time.Month;
import java.util.*;

public class DashboardPanel extends JPanel {

    // ===== FIELDS =====
    private final BudgetDAO budgetDAO;
    private final ExpenseDAO expenseDAO;
    private final ProgressBarsPanel progressBarsPanel;

    private final SummaryPanel balancePanel;
    private final SummaryPanel expensesPanel;
    private final SummaryPanel savingsPanel;

    private final MonthSelectorPanel monthPanel;

    private Month currentMonth;
    private final int currentYear;

    // ===== CONSTRUCTOR =====
    public DashboardPanel() {
        setLayout(new BorderLayout(10, 0));
        setBackground(Color.LIGHT_GRAY);

            budgetDAO = new BudgetDAO();
            expenseDAO = new ExpenseDAO();

            currentMonth = java.time.LocalDate.now().getMonth();
            currentYear = java.time.LocalDate.now().getYear();

            // ===== TOP PANEL =====
            JPanel topPanel = new JPanel(new GridLayout(1, 4, 10, 10));

                // ----- TopMonthPanel -----
                monthPanel = new MonthSelectorPanel();

                // ----- TopSummary panels -----
                balancePanel  = new SummaryPanel("Balance");
                expensesPanel = new SummaryPanel("Expenses");
                savingsPanel  = new SummaryPanel("Savings");

            Helper.stylePanelBackground(monthPanel,balancePanel, expensesPanel, savingsPanel);
            Helper.styleTopPanel(topPanel);

            topPanel.add(monthPanel);
            topPanel.add(balancePanel);
            topPanel.add(expensesPanel);
            topPanel.add(savingsPanel);

        add(topPanel, BorderLayout.NORTH);

        // ===== PROGRESS PANEL =====
        Map<String, Double> budgets = budgetDAO.getMonthlyBudget(currentMonth, currentYear);
        Map<String, Double> expenses = expenseDAO.getMonthlyExpenses(currentMonth, currentYear);

        progressBarsPanel = new ProgressBarsPanel(budgets, expenses);
        Helper.stylePanelBorder(progressBarsPanel, "BUDGET VS EXPENSES");

        add(progressBarsPanel,BorderLayout.CENTER);
        updateSummary(expenses);
    }

    public void refreshData() {
        Map<String, Double> budgets  = budgetDAO.getMonthlyBudget(currentMonth,currentYear);
        Map<String, Double> expenses = expenseDAO.getMonthlyExpenses(currentMonth,currentYear);

        progressBarsPanel.updateData(budgets, expenses);

        double totalExpenses = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
        expensesPanel.setValue(totalExpenses);
        revalidate();
        repaint();
    }

    private void updateSummary(Map<String, Double> expenses){
        double totalExpenses = expenses.values().stream().mapToDouble(Double::doubleValue).sum();
        expensesPanel.setValue(totalExpenses);
    }

    public MonthSelectorPanel getMonthPanel() {
        return monthPanel;
    }

    public void setCurrentMonth(Month month) {
        this.currentMonth = month;
    }

}
