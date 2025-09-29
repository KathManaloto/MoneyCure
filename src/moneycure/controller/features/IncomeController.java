package moneycure.controller.features;

import moneycure.controller.*;
import moneycure.database.*;
import moneycure.model.*;
import moneycure.view.feature.*;
import javax.swing.*;
import java.util.*;
import java.util.logging.*;

public class IncomeController {

    // ===== FIELDS =====
    private final IncomeDAO incomeDAO;
    private final IncomePanel incomePanel;
    private final DashboardPanel dashboardPanel;

    private static final Logger LOGGER = Logger.getLogger(IncomeController.class.getName());

    // ===== CONSTRUCTOR =====
    public IncomeController(IncomePanel incomePanel, IncomeDAO incomeDAO, DashboardPanel dashboardPanel){
        this.incomePanel    = incomePanel;
        this.incomeDAO      = incomeDAO;
        this.dashboardPanel = dashboardPanel;

        initController();
    }

    // ====== INIT CONTROLLER =====
    private void initController(){
        incomePanel.getBtnSubmitIncome().addActionListener(e -> onSubmitButton());
        incomePanel.getBtnCancelIncome().addActionListener(e -> onCancelButton());
    }

    // ===== ON SUBMIT INCOME =====
    private void onSubmitButton(){

        try{
            // date
            String date = MoneyCureController.getSelectedDate(incomePanel.getDateSpinner());
                if(date.isEmpty()){
                    JOptionPane.showMessageDialog(
                        incomePanel,
                        "Date is required.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

            // income source
            String incomeCombo = Objects.requireNonNull(incomePanel.getIncomeCombo().getSelectedItem()).toString();

            // amount
            String amountText = incomePanel.getTxtAmountIncome().getText().trim();
            double amount;

                if(amountText.isEmpty()){
                    JOptionPane.showMessageDialog(
                        incomePanel,
                        "Please enter an amount.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                try {
                    amount = Double.parseDouble(amountText);

                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(
                            incomePanel,
                            "Amount must be greater than zero.",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }

                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(
                        incomePanel,
                        "Amount must be a valid number.",
                        "Validation Error",
                        JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                // notes
                String notes = incomePanel.getTxtNotesIncome().getText();

                Income income = new Income(date, incomeCombo, amount, notes);
                boolean success = incomeDAO.addIncome(income);

                if(success){
                    JOptionPane.showMessageDialog(incomePanel, "Income added!");
                    dashboardPanel.refreshData();
                }

        } catch (Exception e){
            JOptionPane.showMessageDialog(
                incomePanel,
                "Error adding income: " + e.getLocalizedMessage(),
                "Error",
                JOptionPane.WARNING_MESSAGE
            );
            LOGGER.log(Level.SEVERE,"Error adding income, e");
        }
    }

    // ===== ON CANCEL BUTTON =====
    private void onCancelButton(){
        MoneyCureController.clearFields(
            incomePanel.getDateSpinner(),
            incomePanel.getTxtAmountIncome(),
            incomePanel.getTxtNotesIncome(),
            incomePanel.getIncomeCombo()
        );
    }
}