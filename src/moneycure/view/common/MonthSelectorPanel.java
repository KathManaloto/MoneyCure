package moneycure.view.common;

import moneycure.view.Helper;
import javax.swing.*;
import java.awt.*;
import java.time.*;

public class MonthSelectorPanel extends JPanel {

    // ===== FIELDS =====
    private final JComboBox<String> monthComboBox;

    // ===== CONSTRUCTOR =====
    public MonthSelectorPanel(){
        setLayout(new BorderLayout());

            // CREATE MONTH COMBO BOX
            monthComboBox = new JComboBox<>();
            for (Month month : Month.values()) {
                String formatted = month.name().charAt(0) + month.name().substring(1).toLowerCase();
                monthComboBox.addItem(formatted);
            }

            // SELECT CURRENT MONTH
            Month currentMonth      = LocalDate.now().getMonth();
            String currentFormatted = currentMonth.name().charAt(0) + currentMonth.name().substring(1).toLowerCase();

            // STYLE MONTH COMBO BOX
            monthComboBox.setSelectedItem(currentFormatted);
            monthComboBox.setFont(new Font("Verdana", Font.PLAIN, 16));
            monthComboBox.setBackground(new Color(182, 182, 182));
            monthComboBox.setForeground(new Color(59, 59, 59));
            monthComboBox.setFocusable(false);
            monthComboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(
                        JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    label.setHorizontalAlignment(SwingConstants.CENTER);

                    return label;
                }
            });

            Helper.stylePanelBorder(this, "Select Month");

        // ADD MONTH COMBO BOX TO PANEL
        add(monthComboBox, BorderLayout.CENTER);
    }

    // ===== GETTER =====
    public JComboBox<String> getMonthComboBox(){ return monthComboBox; }
}
