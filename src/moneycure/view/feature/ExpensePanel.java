//The Swing GUI for expensePanel
package moneycure.view.feature;

import moneycure.model.*;
import moneycure.view.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class ExpensePanel extends JPanel {

    // ===== FIELDS =====
    private JSpinner dateSpinner;
    private JComboBox<ExpenseBudgetCategory> expensesCategoryCombo;
    private JButton btnAddExpense;
    private JTextField txtAmountExpenses, txtNotesExpenses;
    private DefaultTableModel expensesTableModel;

    // ===== CONSTRUCTOR =====
    public ExpensePanel(){
        initComponents();
    }

    // ===== INIT COMPONENTS =====
    private void initComponents(){
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,5,20));
        setBackground(new Color(201, 201, 201));

            // CREATE TITLE LABEL AND FORM PANEL
            JLabel lblExpenses = new JLabel("\uD83E\uDDFE ADD EXPENSES", SwingConstants.CENTER);
            JPanel formPanel   = new JPanel(new GridBagLayout());

                // date
                JLabel lblDateExpenses = new JLabel("Date (yyyy-MM-dd): ");
                    dateSpinner = new JSpinner(new SpinnerDateModel());
                    JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
                    dateSpinner.setEditor(editor);
                    dateSpinner.setValue(new Date());

                    formPanel.add(lblDateExpenses, Helper.getGbc(0,0, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));
                    formPanel.add(dateSpinner, Helper.getGbc(1,0, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));

                // category
                JLabel lblCategoryExpenses = new JLabel("Category: ");
                expensesCategoryCombo = new JComboBox<>(new ExpenseBudgetCategory[]{
                    new ExpenseBudgetCategory("🏠 Housing", "🏠 HOUSING (e.g. Rent, mortgage, Electricity, water, internet)"),
                    new ExpenseBudgetCategory("🍽️ Food", "🍽️ FOOD (e.g. Groceries, dining out)"),
                    new ExpenseBudgetCategory("🚗 Transportation", "🚗 TRANSPORTATION (e.g. Gas, public transport, car maintenance)"),
                    new ExpenseBudgetCategory("💵 Debt Payments", "💵 DEBT PAYMENTS (e.g. Credit cards, loans)"),
                    new ExpenseBudgetCategory("💼 Work/School", "💼 WORK/SCHOOL (e.g. supplies)"),
                    new ExpenseBudgetCategory("🎉 Entertainment", "🎉 ENTERTAINMENT (e.g. movies, subscriptions, hobbies)"),
                    new ExpenseBudgetCategory("🛍️ Personal/Lifestyle", "🛍️ PERSONAL/LIFESTYLE (e.g. Clothing, beauty, gym)"),
                    new ExpenseBudgetCategory("🎁 Gifts & Holidays", "🎁 GIFTS & HOLIDAYS (e.g. Birthdays, Christmas, special occasions)"),
                    new ExpenseBudgetCategory("📦 Others", "📦 OTHERS (uncategorized expenses)")
                });

                formPanel.add(lblCategoryExpenses, Helper.getGbc(0,1, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));
                formPanel.add(expensesCategoryCombo, Helper.getGbc(1,1, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));

                // amount
                JLabel lblAmountExpenses = new JLabel("Amount: ");
                txtAmountExpenses = new JTextField(25);

                formPanel.add(lblAmountExpenses, Helper.getGbc(0,2, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));
                formPanel.add(txtAmountExpenses, Helper.getGbc(1,2, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));

                // notes
                JLabel lblNotesExpenses = new JLabel("Notes: ");
                txtNotesExpenses  = new JTextField(25);

                formPanel.add(lblNotesExpenses, Helper.getGbc(0,3, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));
                formPanel.add(txtNotesExpenses, Helper.getGbc(1,3, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));

                // add button
                btnAddExpense = new JButton("Add Expense");
                formPanel.add(btnAddExpense,Helper.getGbc(1,4, GridBagConstraints.NONE,GridBagConstraints.WEST,0 ));

            // SET STYLE TITLE AND FORM PANEL
            formPanel.setBackground(new Color(218, 218, 218));
            Helper.styleTitleLabel(lblExpenses);
            Helper.styleFont(formPanel, new Font("Arial", Font.BOLD,14),
                    new Font("Segoe UI Emoji", Font.PLAIN, 12));

            // CREATE CONTENT SUMMARY PANEL
            JPanel contentSummaryPanel = new JPanel(new BorderLayout());

                // table model
                expensesTableModel = new DefaultTableModel(new Object[]{ "Date","Category", "Amount","Notes" },0){
                    public boolean isCellEditable(int row, int column){ return false; }};

                // JTable
                JTable expensesTable = new JTable(expensesTableModel);

                // scrollPane
                JScrollPane scrollPaneExpense = new JScrollPane(expensesTable);
                contentSummaryPanel.add(scrollPaneExpense,BorderLayout.CENTER);

                // wrap formPanel and contentSummaryPanel in centerPanel for better spacing
                JPanel centerPanel = new JPanel(new BorderLayout());
                centerPanel.add(formPanel,BorderLayout.NORTH);
                centerPanel.add(contentSummaryPanel, BorderLayout.CENTER);

            // SET STYLE EXPENSE SUMMARY TABLE
            Helper.styleSummaryTable(expensesTable);

        // add lblExpenses and center panels to main panel
        add(lblExpenses, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    // Add expense to RecentExpensesTable
    public void addExpenseToTable(String date, String category, double amount, String notes){
        expensesTableModel.addRow(new Object[]{date, category, String.format("₱%.2f",amount),
                notes == null || notes.isEmpty() ? "-" : notes});
    }

    // Clear RecentExpensesTable
    public void clearExpenseTable(){ expensesTableModel.setRowCount(0); }

    // Clear TextFields
    public void clearFields() {
        if (dateSpinner != null) { dateSpinner.setValue(new Date());}
        if (txtAmountExpenses != null) { txtAmountExpenses.setText(""); }
        if (txtNotesExpenses != null) { txtNotesExpenses.setText(""); }
        if (expensesCategoryCombo != null && expensesCategoryCombo.getItemCount() > 0) {
            expensesCategoryCombo.setSelectedIndex(0);
        }
    }

    // ===== GETTERS =====
    public String getSelectedDate(){
        Date date = (Date) dateSpinner.getValue();
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public JComboBox<ExpenseBudgetCategory> getExpensesCategoryCombo(){ return expensesCategoryCombo; }
    public JButton getBtnAddExpense(){ return btnAddExpense; }
    public JTextField getTxtAmountExpenses(){ return txtAmountExpenses; }
    public JTextField getTxtNotesExpenses(){ return txtNotesExpenses; }
}