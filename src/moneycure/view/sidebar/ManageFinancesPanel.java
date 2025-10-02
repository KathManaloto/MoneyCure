package moneycure.view.sidebar;

import static moneycure.view.Helper.*;
import moneycure.model.*;
import moneycure.view.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.time.*;
import java.util.*;
import java.util.List;

public class ManageFinancesPanel extends JPanel {

    // ====== FIELDS =====
    private JComboBox<String> viewFilterDropdown, monthDropdown;
    private JComboBox<Integer> yearDropdown;
    private JTextField txtFieldSearch;
    private JTable transactionTable;

    // ====== CONSTRUCTOR =====
    public ManageFinancesPanel() {
        setLayout(new BorderLayout(10, 10));
        initComponents();
    }

    // ===== INIT COMPONENTS =====
    private void initComponents() {

        JPanel topPanel = new JPanel(new BorderLayout());

        // FILTER PANEL
        JPanel filterPanel = new JPanel(new GridLayout(1, 3, 10, 10));
            Helper.styleTopPanel(filterPanel);

            // ===== View Filter =====
            JPanel viewFilterPanel = new JPanel(new BorderLayout());

            viewFilterDropdown = new JComboBox<>(new String[]{"All", "Income", "Savings", "Budget", "Expenses"});
                viewFilterDropdown.setPreferredSize(new Dimension(140, 30));
                viewFilterPanel.add(viewFilterDropdown);

                stylePanelBorder(viewFilterPanel,"Select View");
                Helper.styleFilterDropdown(viewFilterDropdown);
                Helper.stylePanelBackground(viewFilterPanel);

            // ===== Month Filter =====
            String[] months = {
                "All Months", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
            };

            JPanel monthFilterPanel = new JPanel(new BorderLayout());

            monthDropdown = new JComboBox<>(months);
                monthDropdown.setPreferredSize(new Dimension(140, 30));
                monthFilterPanel.add(monthDropdown);

                stylePanelBorder(monthFilterPanel,"Month");
                Helper.styleFilterDropdown(monthDropdown);
                Helper.stylePanelBackground(monthFilterPanel);

            // ===== Year Filter =====
            List<Integer> years = new ArrayList<>();
            int currentYear = Year.now().getValue();
            for(int y=2015; y<= currentYear; y++){ years.add(y);}

            JPanel yearFilterPanel = new JPanel(new BorderLayout());

            yearDropdown = new JComboBox<>(years.toArray(new Integer[0]));
                yearDropdown.setPreferredSize(new Dimension(140, 30));
                yearDropdown.setSelectedItem(currentYear); // default selection
                yearFilterPanel.add(yearDropdown);

                stylePanelBorder(yearFilterPanel, "Year");
                Helper.styleFilterDropdown(yearDropdown);
                Helper.stylePanelBackground(yearFilterPanel);

        filterPanel.add(viewFilterPanel);
        filterPanel.add(monthFilterPanel);
        filterPanel.add(yearFilterPanel);

        // SEARCH PANEL
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            JLabel lblSearch = new JLabel("Search: ");
                lblSearch.setFont(new Font("Verdana", Font.BOLD, 14));
                lblSearch.setForeground(new Color(59, 59, 59));

            txtFieldSearch   = new JTextField(30);
                txtFieldSearch.setPreferredSize(new Dimension(200, 30));
                txtFieldSearch.setFont(new Font("Verdana", Font.PLAIN, 12));
                txtFieldSearch.setForeground(new Color(59, 59, 59));

            searchPanel.add(lblSearch);
            searchPanel.add(txtFieldSearch);

        topPanel.add(filterPanel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // CENTER PANEL
        String[] columnNames = {"Date","Category", "Description", "Amount","Notes"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column){ return false; }
        };

        transactionTable = new JTable(tableModel);
            transactionTable.setFillsViewportHeight(true);
            transactionTable.setRowHeight(25);
            transactionTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateTransactionTable(List<Transaction> transactions){
        String[] columnNames = {"Date","Category", "Description", "Amount","Notes"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        for(Transaction t : transactions){
            model.addRow(new Object[]{
                t.getDate(),
                t.getCategory(),
                t.getDescription(),
                t.getAmount(),
                t.getNotes()
            });
        }

        transactionTable.setModel(model);
    }

    // ===== GETTERS =====
    public JComboBox<String> getViewFilterDropdown(){ return viewFilterDropdown; }
    public JComboBox<String> getMonthDropdown(){ return monthDropdown; }
    public JComboBox<Integer> getYearDropdown(){ return yearDropdown; }
    public JTextField getTxtFieldSearch(){ return txtFieldSearch; }
}