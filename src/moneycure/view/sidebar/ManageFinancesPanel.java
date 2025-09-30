package moneycure.view.sidebar;

import moneycure.view.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.*;
import java.util.*;
import java.util.List;

import static moneycure.view.Helper.stylePanelBorder;

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

                Helper.styleFilterDropdown(viewFilterDropdown);
                stylePanelBorder(viewFilterPanel,"Select View");
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

                Helper.styleFilterDropdown(monthDropdown);
                stylePanelBorder(monthFilterPanel,"Month");
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

            Helper.styleFilterDropdown(yearDropdown);
            stylePanelBorder(yearFilterPanel, "Year");
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
        Object[][] sampleData = {
            {"2025-09-01", "Income", "Salary" , 5000, "-"},
            {"2025-09-02", "Expenses", "Groceries", -200, "-"},
            {"2025-09-03", "Savings", "Emergency Fund", -300, "-"}
        };

        DefaultTableModel tableModel = new DefaultTableModel(sampleData,columnNames){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        transactionTable = new JTable(tableModel);
        transactionTable.setFillsViewportHeight(true);
        transactionTable.setRowHeight(25);
        transactionTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(transactionTable);

        add(scrollPane, BorderLayout.CENTER);
    }

    // ===== GETTERS =====
    public JComboBox<String> getViewFilterDropdown(){ return viewFilterDropdown; }

}