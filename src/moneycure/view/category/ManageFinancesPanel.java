package moneycure.view.category;

import moneycure.view.Helper;

import javax.swing.*;
import java.awt.*;

import static moneycure.view.Helper.stylePanelBorder;

public class ManageFinancesPanel extends JPanel {

    // ====== FIELDS =====
    private JComboBox<String> viewFilterDropdown, monthDropdown;
    private JTextField searchField;

    // ====== CONSTRUCTOR =====
    public ManageFinancesPanel() {
        setLayout(new BorderLayout(10, 10));
        initComponents();
    }

    private void initComponents() {
        // TOP PANEL with GridBagLayout
        JPanel topPanel = new JPanel(new GridLayout(1, 3, 10, 10));

            // ===== View Filter =====
            JPanel viewFilterPanel = new JPanel(new BorderLayout());

                viewFilterDropdown = new JComboBox<>(new String[]{"All", "Income", "Savings", "Budget", "Expenses"});
                viewFilterDropdown.setPreferredSize(new Dimension(140, 30));

            viewFilterPanel.add(viewFilterDropdown);
            stylePanelBorder(viewFilterPanel,"Select View");
            Helper.stylePanelBackground(viewFilterPanel);

        topPanel.add(viewFilterPanel);

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
                Helper.stylePanelBackground(monthFilterPanel);

        topPanel.add(monthFilterPanel);

            // ===== Search Field =====
            JPanel searchFieldPanel = new JPanel(new BorderLayout());
                searchField = new JTextField();
                searchField.setPreferredSize(new Dimension(140, 15));

                searchFieldPanel.add(searchField);
                stylePanelBorder(searchFieldPanel,"Search");
                Helper.stylePanelBackground(searchFieldPanel);

        topPanel.add(searchFieldPanel);

        // ===== Style Panel =====
        Helper.styleTopPanel(topPanel);

        add(topPanel, BorderLayout.NORTH);
    }
}
