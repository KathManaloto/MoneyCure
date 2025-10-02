package moneycure.controller.manage;

import moneycure.database.*;
import moneycure.model.*;
import moneycure.view.sidebar.*;
import java.time.Month;
import java.util.List;

public class ManageFinancesController {

    private final ManageFinancesPanel manageFinancesPanel;
    private final TransactionDAO transactionDAO;

    public ManageFinancesController(ManageFinancesPanel manageFinancesPanel, TransactionDAO transactionDAO){
        this.manageFinancesPanel = manageFinancesPanel;
        this.transactionDAO = transactionDAO;

        initComponent();
        applyFilters(); // load initial
    }

    private void initComponent(){
        manageFinancesPanel.getViewFilterDropdown().addActionListener(e -> applyFilters());
        manageFinancesPanel.getMonthDropdown().addActionListener(e -> applyFilters());
        manageFinancesPanel.getYearDropdown().addActionListener(e -> applyFilters());
        manageFinancesPanel.getTxtFieldSearch().addActionListener(e -> applyFilters());
    }

    private void applyFilters(){
        List<Transaction> transactions = transactionDAO.getAllTransactions();

        // === View filter ===
        String selectedView = (String) manageFinancesPanel.getViewFilterDropdown().getSelectedItem();
        transactions = transactions.stream()
                .filter(t -> "All".equalsIgnoreCase(selectedView) ||
                        t.getCategory().equalsIgnoreCase(selectedView))
                .toList();

        // === Month filter ===
        String selectedMonth = (String) manageFinancesPanel.getMonthDropdown().getSelectedItem();
        if (!"All Months".equalsIgnoreCase(selectedMonth)) {
            int monthValue = Month.valueOf(selectedMonth.toUpperCase()).getValue();
            transactions = transactions.stream()
                    .filter(t -> t.getDate().getMonthValue() == monthValue)
                    .toList();
        }

        // === Year filter ===
        Integer selectedYear = (Integer) manageFinancesPanel.getYearDropdown().getSelectedItem();
        transactions = transactions.stream()
                .filter(t -> t.getDate().getYear() == selectedYear)
                .toList();

        // === Search filter ===
        String query = manageFinancesPanel.getTxtFieldSearch().getText().trim().toLowerCase();
        if (!query.isEmpty()) {
            transactions = transactions.stream()
                    .filter(t -> t.getDescription().toLowerCase().contains(query) ||
                            t.getNotes().toLowerCase().contains(query))
                    .toList();
        }

        // update table
        manageFinancesPanel.updateTransactionTable(transactions);
    }
}
