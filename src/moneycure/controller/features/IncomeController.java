package moneycure.controller.features;

import moneycure.controller.MoneyCureController;
import moneycure.database.IncomeDAO;
import moneycure.model.Income;
import moneycure.view.feature.IncomePanel;

import javax.swing.*;
import java.util.Objects;
import java.util.logging.*;

public class IncomeController {

    private final IncomeDAO incomeDAO;
    private final IncomePanel incomePanel;
    private static final Logger LOGGER = Logger.getLogger(IncomeController.class.getName());

    public IncomeController(IncomePanel incomePanel, IncomeDAO incomeDAO){
        this.incomePanel = incomePanel;
        this.incomeDAO   = incomeDAO;

        initController();
    }

    private void initController(){
        incomePanel.getBtnSubmitIncome().addActionListener(e -> onSubmitIncome());
    }

    private void onSubmitIncome(){

        try{
            // date
            String date = MoneyCureController.getSelectedDate(incomePanel.getDateSpinner());
                if(date.isEmpty()){
                    JOptionPane.showMessageDialog(
                        incomePanel,
                        "Date is required.",
                        "Validation error",
                        JOptionPane.WARNING_MESSAGE);
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
                        JOptionPane.WARNING_MESSAGE);

                    return;
                }

                try {
                    amount = Double.parseDouble(amountText);

                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(
                            incomePanel,
                            "Amount must be greater than zero.",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog(
                            incomePanel,
                            "Amount must be a valid number.",
                            "Validation Error",
                            JOptionPane.WARNING_MESSAGE);

                        return;
                    }

                // notes
                String notes = incomePanel.getTxtNotesIncome().getText();

                Income income = new Income(date, incomeCombo, amount, notes);
                boolean success = incomeDAO.addIncome(income);

                if(success){
                    JOptionPane.showMessageDialog(incomePanel, "Income added!");


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


}
