package moneycure.controller.addDataFeatures;

import moneycure.controller.*;
import moneycure.database.*;
import moneycure.model.*;
import moneycure.view.feature.*;
import javax.swing.*;
import java.util.*;

public class SavingsController {

    private final SavingsPanel savingsPanel;
    private final SavingsDAO savingsDAO;
    private final DashboardPanel dashboardPanel;

    public SavingsController(SavingsPanel savingsPanel, SavingsDAO savingsDAO, DashboardPanel dashboardPanel){
        this.savingsPanel   = savingsPanel;
        this.savingsDAO     = savingsDAO;
        this.dashboardPanel = dashboardPanel;

        initController();
    }

    private void initController(){
        savingsPanel.getBtnSubmitSavings().addActionListener(e -> onSubmitButton());
        savingsPanel.getBtnCancelSavings().addActionListener(e -> onCancelButton());
    }

    private void onSubmitButton(){

        try {

            // date
            String date = MoneyCureController.getSelectedDate(savingsPanel.getDateSpinner());
            if (date.isEmpty()) {
                JOptionPane.showMessageDialog(
                    savingsPanel,
                    "Date is required.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE

                );

                return;
            }

            // savings type
            String savingsTypeCombo = Objects.requireNonNull(savingsPanel.getSavingsTypeCombo().getSelectedItem()).toString();

            // amount
            String amountTextSavings = savingsPanel.getTxtAmountSavings().getText().trim();
            if (amountTextSavings.isEmpty()) {
                JOptionPane.showMessageDialog(
                    savingsPanel,
                    "Please enter an amount.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            double amountSavings;

            try{
                amountSavings = Double.parseDouble(amountTextSavings);

            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(
                    savingsPanel,
                    "Amount must be a valid number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if(amountSavings <= 0){
                JOptionPane.showMessageDialog(
                    savingsPanel,
                    "Savings amount should be greater than zero.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
                );

                return;
            }
            // notes
            String notes    = savingsPanel.getTxtNotesSavings().getText();

            // connect to database
            Savings savings = new Savings(date, savingsTypeCombo, amountSavings, notes);
            boolean success = savingsDAO.addSavings(savings);

            if(success){
                JOptionPane.showMessageDialog(savingsPanel,"Savings added successfully");
                MoneyCureController.clearFields(savingsPanel.getDateSpinner(),savingsPanel.getTxtAmountSavings(),savingsPanel.getTxtNotesSavings(),savingsPanel.getSavingsTypeCombo());
                dashboardPanel.refreshData();

            } else {
            JOptionPane.showMessageDialog(savingsPanel, "Failed to add savings.");
        }

        } catch (Exception e){
            JOptionPane.showMessageDialog(
                savingsPanel,
                "Error adding savings: " + e.getLocalizedMessage(),
                "Error",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void onCancelButton(){
        MoneyCureController.clearFields(
            savingsPanel.getDateSpinner(),
            savingsPanel.getTxtAmountSavings(),
            savingsPanel.getTxtNotesSavings(),
            savingsPanel.getSavingsTypeCombo()
        );
    }
}