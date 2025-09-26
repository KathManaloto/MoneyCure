package moneycure.view.category;

import moneycure.view.*;
import moneycure.view.feature.*;

import javax.swing.*;
import java.awt.*;

public class AddDataPanel extends JPanel {

    // ===== FIELDS =====
    private JButton btnIncome,btnSavings,btnBudget,btnExpenses;
    private CardLayout cardLayout;
    private JPanel contentPanel;

    public static final String INCOME   = "Income";
    public static final String SAVINGS  = "Savings";
    public static final String BUDGET   = "Budget";
    public static final String EXPENSES = "Expenses";

    private BudgetPanel budgetPanel;
    private ExpensePanel expensePanel;

    // ===== CONSTRUCTOR =====
    public AddDataPanel(){
        initComponents();
    }

    // ===== INIT COMPONENTS =====
    private void initComponents(){
        setLayout(new BorderLayout(10,0));

            // ===== TOP PANEL =====
            JPanel topPanel = new JPanel(new GridLayout(1,4,10,10));

                // ----- TopPanel Buttons -----
                btnIncome   = new JButton("Add Income");
                btnSavings  = new JButton("Add Savings");
                btnBudget   = new JButton("Set Budget");
                btnExpenses = new JButton("Add Expenses");

                Helper.styleTopPanel(topPanel);
                Helper.styleTopButtons(btnIncome);
                Helper.styleTopButtons(btnSavings);
                Helper.styleTopButtons(btnBudget);
                Helper.styleTopButtons(btnExpenses);

            topPanel.add(btnIncome);
            topPanel.add(btnSavings);
            topPanel.add(btnBudget);
            topPanel.add(btnExpenses);

            // ===== CONTENT AREA (CardLayout) =====
            cardLayout   = new CardLayout();
            contentPanel = new JPanel(cardLayout);

                IncomePanel incomePanel   = new IncomePanel();
                SavingsPanel savingsPanel = new SavingsPanel();
                budgetPanel  = new BudgetPanel();
                expensePanel = new ExpensePanel();

            contentPanel.add(incomePanel,INCOME);
            contentPanel.add(savingsPanel,SAVINGS);
            contentPanel.add(budgetPanel,BUDGET);
            contentPanel.add(expensePanel,EXPENSES);

        add(topPanel,BorderLayout.NORTH);
        add(contentPanel,BorderLayout.CENTER);

        showCard(INCOME);
    }

    // ===== HELPER - SHOW CARD =====
    public void showCard(String name){
        cardLayout.show(contentPanel, name);
    }

    // ===== GETTERS =====
    public JButton getBtnIncome(){ return btnIncome; }
    public JButton getBtnSavings(){ return btnSavings; }
    public JButton getBtnBudget(){ return btnBudget; }
    public JButton getBtnExpenses(){ return btnExpenses; }

    public ExpensePanel getExpensePanel() { return expensePanel; }
    public BudgetPanel getBudgetPanel(){ return budgetPanel; }
}
