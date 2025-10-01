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

    private ManageFinancesController manageFinancesController;

    // ===== CONSTRUCTORS =====
    public MoneyCureController(MainFrame mainFrame, AddDataPanel addDataPanel, DashboardPanel dashboardPanel, ManageFinancesPanel manageFinances) {
        this.mainFrame      = mainFrame;
        this.addDataPanel   = addDataPanel;
        this.dashboardPanel = dashboardPanel;
        this.manageFinances = manageFinances;

        IncomeController incomeController = new IncomeController(addDataPanel.getIncomePanel(), new IncomeDAO(), dashboardPanel);
        SavingsController savingsController = new SavingsController(addDataPanel.getSavingsPanel(), new SavingsDAO(), dashboardPanel);
        BudgetController budgetController = new BudgetController(addDataPanel.getBudgetPanel(), new BudgetDAO(), dashboardPanel);
        ExpenseController expenseController = new ExpenseController(addDataPanel.getExpensePanel(), new ExpenseDAO(), dashboardPanel);

        initController();

    }

    // ===== ATTACH LISTENERS =====
    private void initController() {

        // ===== SIDEBAR BUTTONS CONTROLLERS =====
        mainFrame.getBtnDashboard().addActionListener(e -> showDashboard());
        mainFrame.getBtnAddData().addActionListener(e -> showAddData());
        mainFrame.getBtnManage().addActionListener(e -> showManage());

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
    private void showDashboard() { mainFrame.showCard(MainFrame.DASHBOARD);}

    // ===== SHOW CARD PANEL - ADD_DATA  =====
    private void showAddData() { mainFrame.showCard(MainFrame.ADD_DATA);}

        // ADD_DATA FEATURES
        private void showIncome()  { addDataPanel.showCard(AddDataPanel.INCOME);}
        private void showSavings() { addDataPanel.showCard(AddDataPanel.SAVINGS);}
        private void showBudget()  { addDataPanel.showCard(AddDataPanel.BUDGET); }
        private void showExpenses(){ addDataPanel.showCard(AddDataPanel.EXPENSES);}

    // ===== SHOW CARD PANEL - MANAGE_FINANCES  =====
    private void showManage() {
        mainFrame.showCard(MainFrame.MANAGE);
        if (manageFinancesController == null) {
            manageFinancesController = new ManageFinancesController(manageFinances,new TransactionDAO());
        }
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