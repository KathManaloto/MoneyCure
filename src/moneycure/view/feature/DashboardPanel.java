package moneycure.view.feature;

import moneycure.database.*;
import moneycure.model.Budget;
import moneycure.model.Expense;
import moneycure.view.*;
import moneycure.view.common.*;
import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.*;
import java.util.List;

public class DashboardPanel extends JPanel {

    // ===== FIELDS =====
    private final BudgetDAO budgetDAO;
    private final ExpenseDAO expenseDAO;
    private ProgressBarsPanel progressBarsPanel; // <-- make it a field

    // ===== CONSTRUCTOR =====
    public DashboardPanel() {
        setLayout(new BorderLayout(10, 0));
        setBackground(Color.LIGHT_GRAY);

        budgetDAO = new BudgetDAO();
        expenseDAO = new ExpenseDAO();

        // ===== TOP PANEL =====
        JPanel topPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        // ----- TopMonthPanel -----
        MonthSelectorPanel monthPanel = new MonthSelectorPanel();

        // ----- TopSummary panels -----
        SummaryPanel balancePanel = new SummaryPanel("Balance");
        SummaryPanel expensesPanel = new SummaryPanel("Expenses");
        SummaryPanel savingsPanel = new SummaryPanel("Savings");

        Helper.stylePanelBackground(monthPanel, balancePanel, expensesPanel, savingsPanel);
        Helper.styleTopPanel(topPanel);

        topPanel.add(monthPanel);
        topPanel.add(balancePanel);
        topPanel.add(expensesPanel);
        topPanel.add(savingsPanel);

        add(topPanel, BorderLayout.NORTH);

        // ===== PROGRESS PANEL =====
        Month currentMonth = LocalDate.now().getMonth();
        int currentYear = LocalDate.now().getYear();

        Map<String, Double> budgets = loadBudgets(currentMonth, currentYear);
        Map<String, Double> expenses = loadExpenses(currentMonth, currentYear);

        progressBarsPanel = new ProgressBarsPanel(budgets, expenses); // <-- assign to field
        Helper.stylePanelBorder(progressBarsPanel, "BUDGET VS EXPENSES");

        add(progressBarsPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private Map<String, Double> loadBudgets(Month month, int year) {
        Map<String, Double> budgetMap = new HashMap<>();
        List<Budget> budgetList = budgetDAO.getBudgetsByMonth(month, year);

        for (Budget b : budgetList) {
            budgetMap.put(b.getCategory(), b.getAmount());
        }
        return budgetMap;
    }

    private Map<String, Double> loadExpenses(Month month, int year) {
        Map<String, Double> expenseMap = new HashMap<>();
        List<Expense> expenseList = expenseDAO.getExpensesByMonth(month, year);

        for (Expense e : expenseList) {
            expenseMap.put(e.getCategory(), e.getAmount());
        }
        return expenseMap;
    }

    public void refreshData() {
        Month currentMonth = LocalDate.now().getMonth();
        int currentYear = LocalDate.now().getYear();

        Map<String, Double> budgets = loadBudgets(currentMonth, currentYear);
        Map<String, Double> expenses = loadExpenses(currentMonth, currentYear);

        progressBarsPanel.updateData(budgets, expenses); // <-- now works
        revalidate();
        repaint();
    }
}
