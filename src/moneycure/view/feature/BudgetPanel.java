//set budget form
package moneycure.view.feature;

import moneycure.model.*;
import moneycure.view.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class BudgetPanel extends JPanel {

    // ===== FIELDS =====
    private JSpinner dateSpinner;
    private JComboBox<ExpenseBudgetCategory> budgetCategoryCombo;
    private DefaultTableModel budgetTableModel;
    private JTextField txtAmountBudget, txtNotesBudget;
    private JButton btnAddBudget;

    // ===== CONSTRUCTOR =====
    public BudgetPanel(){ initComponents();}

    // ===== INIT COMPONENTS =====
    private void initComponents(){
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,5,20));
        setBackground(new Color(201, 201, 201));

            // CREATE TITLE LABEL AND FORM PANEL
            JLabel lblBudget = new JLabel("\uD83D\uDCCC SET BUDGET", SwingConstants.CENTER);
            JPanel formPanel = new JPanel(new GridBagLayout());

                // date
                JLabel lblDateBudget = new JLabel("Date (yyyy-MM-dd): ");
                dateSpinner = new JSpinner(new SpinnerDateModel());
                JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
                dateSpinner.setEditor(editor);
                dateSpinner.setValue(new Date());

                formPanel.add(lblDateBudget, Helper.getGbc(0,0, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));
                formPanel.add(dateSpinner, Helper.getGbc(1,0, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));

                // category
                JLabel lblCategoryBudget = new JLabel("Category: ");
                budgetCategoryCombo = new JComboBox<>(new ExpenseBudgetCategory[]{
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

                formPanel.add(lblCategoryBudget, Helper.getGbc(0,1, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));
                formPanel.add(budgetCategoryCombo, Helper.getGbc(1,1, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));

                // amount
                JLabel lblAmountBudget = new JLabel("Amount: ");
                txtAmountBudget = new JTextField(25);

                formPanel.add(lblAmountBudget, Helper.getGbc(0,2, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));
                formPanel.add(txtAmountBudget, Helper.getGbc(1,2, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));

                // notes
                JLabel lblNotesBudget = new JLabel("Notes: ");
                txtNotesBudget  = new JTextField(25);

                formPanel.add(lblNotesBudget, Helper.getGbc(0,3, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));
                formPanel.add(txtNotesBudget, Helper.getGbc(1,3, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0 ));

                // add button
                btnAddBudget = new JButton("Add Budget");
                formPanel.add(btnAddBudget,Helper.getGbc(1,4, GridBagConstraints.NONE,GridBagConstraints.WEST,0 ));

            // SET STYLE TITLE AND FORM PANEL
            formPanel.setBackground(new Color(218, 218, 218));
            Helper.styleTitleLabel(lblBudget);
            Helper.styleFont(formPanel, new Font("Arial", Font.BOLD,14),
                    new Font("Segoe UI Emoji", Font.PLAIN, 12));

            // CREATE CONTENT SUMMARY PANEL
            JPanel contentSummaryPanel = new JPanel(new BorderLayout());

                // table model
                budgetTableModel = new DefaultTableModel(new Object[]{"Date","Category","Amount","Notes"},0) {
                    public boolean isCellEditable(int row, int column){ return false;}};

                // JTable
                JTable budgetTable = new JTable(budgetTableModel);

                // scrollPane
                JScrollPane scrollPane = new JScrollPane(budgetTable);
                contentSummaryPanel.add(scrollPane,BorderLayout.CENTER);

                // wrap formPanel and contentSummaryPanel in centerPanel for better spacing
                JPanel centerPanel = new JPanel(new BorderLayout());
                centerPanel.add(formPanel,BorderLayout.NORTH);
                centerPanel.add(contentSummaryPanel, BorderLayout.CENTER);

            Helper.styleSummaryTable(budgetTable);

        // add lblExpenses and center panels to main panel
        add(lblBudget, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    // Add expense to RecentExpensesTable
    public void addBudgetToTable(String date, String category, double amount, String notes){
        budgetTableModel.addRow(new Object[]{date, category, String.format("₱%.2f",amount),
                notes == null || notes.isEmpty() ? "-" : notes});
    }

    // Clear RecentExpensesTable
    public void clearBudgetTable(){ budgetTableModel.setRowCount(0); }

    // Clear TextFields
    public void clearFields() {
        if (dateSpinner != null) { dateSpinner.setValue(new Date());}
        if (txtAmountBudget != null) { txtAmountBudget.setText(""); }
        if (txtNotesBudget != null) { txtNotesBudget.setText(""); }
        if (budgetCategoryCombo != null && budgetCategoryCombo.getItemCount() > 0) {
            budgetCategoryCombo.setSelectedIndex(0);
        }
    }

    // ===== GETTERS =====
    public String getSelectedDate(){
        Date date = (Date) dateSpinner.getValue();
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public JComboBox<ExpenseBudgetCategory> getBudgetCategoryCombo(){ return budgetCategoryCombo; }
    public JButton getBtnAddBudget(){ return btnAddBudget; }
    public JTextField getTxtAmountBudget(){ return txtAmountBudget; }
    public JTextField getTxtNotesBudget(){ return txtNotesBudget; }
}

