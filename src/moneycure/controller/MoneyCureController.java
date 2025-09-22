//Instantiates sub-controllers like ExpenseController and passes them the right Panel + DAO.
package moneycure.controller;

import moneycure.controller.features.*;
import moneycure.database.*;
import moneycure.view.*;
import moneycure.view.category.*;
import moneycure.view.feature.*;

public class MoneyCureController {

    // ===== FIELDS =====
    private final MainFrame mainFrame;
    private final AddDataPanel addDataPanel;

    private ExpenseController expenseController;
    private BudgetController budgetController;

    // ===== CONSTRUCTORS =====
    public MoneyCureController(MainFrame mainFrame, AddDataPanel addDataPanel) {
        this.mainFrame = mainFrame;
        this.addDataPanel = addDataPanel;
        initController();
    }

    // ===== ATTACH LISTENERS =====
    private void initController() {

        // ----- SIDE BARS -----
        mainFrame.getBtnDashboard().addActionListener(e -> showDashboard());
        mainFrame.getBtnAddData().addActionListener(e -> showAddData());
        mainFrame.getBtnManage().addActionListener(e -> showManage());
        mainFrame.getBtnAnalysis().addActionListener(e -> showAnalysis());

        // ----- ADD_DATA FEATURES -----
        addDataPanel.getBtnIncome().addActionListener(e -> showIncome());
        addDataPanel.getBtnSavings().addActionListener(e -> showSavings());
        addDataPanel.getBtnBudget().addActionListener(e -> showBudget());
        addDataPanel.getBtnExpenses().addActionListener(e -> showExpenses());

    }

    // ===== SIDEBARS =====
    private void showDashboard() {
        mainFrame.showCard(MainFrame.DASHBOARD);
    }

    private void showAddData() {
        mainFrame.showCard(MainFrame.ADD_DATA);
    }

    private void showManage() {
        mainFrame.showCard(MainFrame.MANAGE);
    }

    private void showAnalysis() {
        mainFrame.showCard(MainFrame.ANALYSIS);
    }

    // ===== ADD_DATA FEATURES =====
    private void showIncome() {
        addDataPanel.showCard(AddDataPanel.INCOME);
    }

    private void showSavings() {
        addDataPanel.showCard(AddDataPanel.SAVINGS);
    }

    private void showBudget() {
        addDataPanel.showCard(AddDataPanel.BUDGET);

        if (budgetController == null) {
            BudgetDAO budgetDAO = new BudgetDAO();
            BudgetPanel budgetPanel = addDataPanel.getBudgetPanel();
            DashboardPanel dashboardPanel = mainFrame.getDashboardPanel();

            budgetController = new BudgetController(budgetPanel, budgetDAO, dashboardPanel);
        }
    }

    private void showExpenses() {
        addDataPanel.showCard(AddDataPanel.EXPENSES);

        if (expenseController == null) {
            ExpenseDAO expenseDAO = new ExpenseDAO();
            ExpensePanel expensePanel = addDataPanel.getExpensePanel();
            expenseController = new ExpenseController(expensePanel, expenseDAO);
        }
    }
}

