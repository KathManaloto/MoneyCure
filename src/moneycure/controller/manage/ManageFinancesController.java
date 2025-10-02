package moneycure.controller.manage;

import moneycure.database.*;
import moneycure.model.*;
import moneycure.view.sidebar.*;
import java.util.List;

public class ManageFinancesController {

    private final ManageFinancesPanel manageFinancesPanel;
    private final TransactionDAO transactionDAO;

    public ManageFinancesController(ManageFinancesPanel manageFinancesPanel, TransactionDAO transactionDAO){
        this.manageFinancesPanel = manageFinancesPanel;
        this.transactionDAO = transactionDAO;

        initComponent();
        loadTransactions();
    }

    private void initComponent(){

        // hook up dropdowns
        manageFinancesPanel.getViewFilterDropdown().addActionListener(e -> filterByView());
        manageFinancesPanel.getMonthDropdown().addActionListener(e -> filterByMonth());
        manageFinancesPanel.getYearDropdown().addActionListener(e -> filterByYear());

        // search field
        manageFinancesPanel.getTxtFieldSearch().addActionListener(e -> searchTransactions());
    }

    public void loadTransactions(){
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        manageFinancesPanel.updateTransactionTable(transactions);
    }

    private void filterByView(){
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        String selected = (String) manageFinancesPanel.getViewFilterDropdown().getSelectedItem();

        if(!"All".equalsIgnoreCase(selected)){
            transactions = transactions.stream()
                    .filter(t -> t.getCategory().equalsIgnoreCase(selected))
                    .toList();
        }

        manageFinancesPanel.updateTransactionTable(transactions);
    }

    private void filterByMonth(){
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        String selected = (String) manageFinancesPanel.getMonthDropdown().getSelectedItem();

        if(!"All Months".equalsIgnoreCase(selected)){
            int selectedMonth = java.time.Month.valueOf(selected.toUpperCase()).getValue();
            transactions = transactions.stream()
                    .filter(t -> t.getDate().getMonthValue() == selectedMonth)
                    .toList();
        }

        manageFinancesPanel.updateTransactionTable(transactions);
    }

    private void filterByYear(){
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        Integer selectedYear = (Integer) manageFinancesPanel.getYearDropdown().getSelectedItem();

        transactions = transactions.stream()
                    .filter(t -> t.getDate().getYear() == selectedYear)
                    .toList();


        manageFinancesPanel.updateTransactionTable(transactions);
    }

    private void searchTransactions(){
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        String query = manageFinancesPanel.getTxtFieldSearch().getText().trim().toLowerCase();

        if(!query.isEmpty()){
            transactions = transactions.stream()
                    .filter(t -> t.getDescription().toLowerCase().contains(query)
                            || t.getCategory().toLowerCase().contains(query)
                            || t.getNotes().toLowerCase().contains(query))
                    .toList();
        }
        manageFinancesPanel.updateTransactionTable(transactions);

    }

}
