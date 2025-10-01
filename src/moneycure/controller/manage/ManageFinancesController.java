package moneycure.controller.manage;

import moneycure.database.TransactionDAO;
import moneycure.model.Transaction;
import moneycure.view.sidebar.ManageFinancesPanel;

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

    private void loadTransactions(){
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        manageFinancesPanel.updateTransactionTable(transactions);
    }

    private void filterByView(){
        String selected = (String) manageFinancesPanel.getViewFilterDropdown().getSelectedItem();
        loadTransactions();
    }

    private void filterByMonth(){
        String selected = (String) manageFinancesPanel.getMonthDropdown().getSelectedItem();
        loadTransactions();
    }

    private void filterByYear(){
        Integer selected = (Integer) manageFinancesPanel.getYearDropdown().getSelectedItem();
        loadTransactions();
    }

    private void searchTransactions(){
        String query = manageFinancesPanel.getTxtFieldSearch().getText();
        loadTransactions();

    }

}
