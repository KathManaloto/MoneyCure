//Instantiates sub-controllers like ExpenseController and passes them the right Panel + DAO.
package moneycure.controller;

import moneycure.controller.addDataFeatures.*;
import moneycure.controller.manage.ManageFinancesController;
import moneycure.database.*;
import moneycure.view.*;
import moneycure.view.sidebar.*;
import moneycure.view.feature.*;
import javax.swing.*;
import java.util.*;

public class MoneyCureController {

    // ===== FIELDS =====
    private final MainFrame mainFrame;
    private final AddDataPanel addDataPanel;
    private final DashboardPanel dashboardPanel;
    private final ManageFinancesPanel manageFinances;

    private final IncomeController incomeController;
    private final SavingsController savingsController;
    private final ExpenseController expenseController;
    private final BudgetController budgetController;

    private ManageFinancesController manageFinancesController;

    // ===== CONSTRUCTORS =====
    public MoneyCureController(MainFrame mainFrame, AddDataPanel addDataPanel, DashboardPanel dashboardPanel, ManageFinancesPanel manageFinances) {
        this.mainFrame      = mainFrame;
        this.addDataPanel   = addDataPanel;
        this.dashboardPanel = dashboardPanel;
        this.manageFinances = manageFinances;

        incomeController  = new IncomeController(addDataPanel.getIncomePanel(), new IncomeDAO(), dashboardPanel);
        savingsController = new SavingsController(addDataPanel.getSavingsPanel(), new SavingsDAO(), dashboardPanel);
        budgetController  = new BudgetController(addDataPanel.getBudgetPanel(), new BudgetDAO(), dashboardPanel);
        expenseController = new ExpenseController(addDataPanel.getExpensePanel(), new ExpenseDAO(), dashboardPanel);

        initController();

    }

    // ===== ATTACH LISTENERS =====
    private void initController() {

        // ===== SIDEBAR BUTTONS CONTROLLERS =====
        mainFrame.getBtnDashboard().addActionListener(e -> showDashboard());
        mainFrame.getBtnAddData().addActionListener(e -> showAddData());
        mainFrame.getBtnManage().addActionListener(e -> showManage());
        mainFrame.getBtnAnalysis().addActionListener(e -> showAnalysis());

            // ===== DASHBOARD CONTROLLER =====
            dashboardPanel.getMonthPanel().getMonthComboBox().addActionListener(e -> {
                String selected = (String) dashboardPanel.getMonthPanel().getMonthComboBox().getSelectedItem();
                if(selected != null){
                    java.time.Month month = java.time.Month.valueOf(selected.toUpperCase());
                    dashboardPanel.setCurrentMonth(month);
                    dashboardPanel.refreshData();
                }
            });

            // ===== ADD_DATA FEATURES CONTROLLERS =====
            addDataPanel.getBtnIncome().addActionListener(e -> showIncome());
            addDataPanel.getBtnSavings().addActionListener(e -> showSavings());
            addDataPanel.getBtnBudget().addActionListener(e -> showBudget());
            addDataPanel.getBtnExpenses().addActionListener(e -> showExpenses());
    }

    // ===== SHOW CARD PANEL - DASHBOARD =====
    private void showDashboard() {
        mainFrame.showCard(MainFrame.DASHBOARD);
    }

    // ===== SHOW CARD PANEL - ADD_DATA  =====
    private void showAddData() {
        mainFrame.showCard(MainFrame.ADD_DATA);
    }

        // ----- SHOW CARD PANEL ADD_DATA_INCOME -----
        private void showIncome() {
            addDataPanel.showCard(AddDataPanel.INCOME);
        }

        // ----- SHOW CARD PANEL ADD_DATA_SAVINGS -----
        private void showSavings() {
            addDataPanel.showCard(AddDataPanel.SAVINGS);
        }

        // ----- SHOW CARD PANEL ADD_DATA_BUDGET -----
        private void showBudget() {
            addDataPanel.showCard(AddDataPanel.BUDGET);
        }

        // V SHOW CARD PANEL ADD_DATA_EXPENSES -----
        private void showExpenses() {
            addDataPanel.showCard(AddDataPanel.EXPENSES);
        }


    // ===== SHOW CARD PANEL - MANAGE_FINANCES  =====
    private void showManage() {
        mainFrame.showCard(MainFrame.MANAGE);
        if (manageFinancesController == null) {
            manageFinancesController = new ManageFinancesController(manageFinances);
        }
    }

    // ===== SHOW CARD PANEL - FINANCIAL_ANALYSIS  =====
    private void showAnalysis() {
        mainFrame.showCard(MainFrame.ANALYSIS);
    }

    // CLEAR FIELDS
    public static <E> void clearFields(JSpinner dateSpinner, JTextField txtAmount, JTextField txtNotes, JComboBox<E> comboBox) {
        if (dateSpinner != null) { dateSpinner.setValue(new Date());}
        if (txtAmount != null) { txtAmount.setText(""); }
        if (txtNotes != null) { txtNotes.setText(""); }
        if (comboBox != null && comboBox.getItemCount() > 0) {
            comboBox.setSelectedIndex(0);
        }
    }

    // GET SELECTED DATE
    public static String getSelectedDate(JSpinner dateSpinner){
        Date date = (Date) dateSpinner.getValue();
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}