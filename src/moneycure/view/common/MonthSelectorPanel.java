package moneycure.view.common;

import moneycure.view.Helper;

import javax.swing.*;
import java.awt.*;
import java.time.Month;

public class MonthSelectorPanel extends JPanel {

    private final JComboBox<String> monthComboBox;

    public MonthSelectorPanel(){
        setLayout(new BorderLayout());

        monthComboBox = new JComboBox<>();

        for (Month month : Month.values()) {
            String formatted = month.name().charAt(0) + month.name().substring(1).toLowerCase();
            monthComboBox.addItem(formatted);
        }

        // ✅ Select current month automatically
        Month currentMonth = java.time.LocalDate.now().getMonth();
        String currentFormatted = currentMonth.name().charAt(0) + currentMonth.name().substring(1).toLowerCase();
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

        add(monthComboBox, BorderLayout.CENTER);
        Helper.stylePanelBorder(this, "Select Month");
    }

    public JComboBox<String> getMonthComboBox(){
        return monthComboBox;
    }
}
