package moneycure.view;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.awt.*;

public class Helper {

    // Style TopButtons - AddData Panel
    public static void styleTopButtons(JButton button){
        button.setFont(new Font("Verdana", Font.BOLD,14));
        button.setForeground(new Color(44, 44, 44));
        button.setBackground(new Color(241, 241, 241));
        button.setFocusable(false);
    }

    // Style TopPanel - Dashboard, AddData Panels
    public static void styleTopPanel(JPanel panel){
        panel.setPreferredSize(new Dimension(0, 90));
        panel.setBackground(new Color(128, 128, 128));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createLineBorder(new Color(241, 241, 241), 2)
        ));
    }

    // Style BottomButtons - Income, Savings Panels
    public static void styleBottomButtons(JButton button){
        button.setFont(new Font("Verdana", Font.BOLD, 18));
        button.setBackground(new Color(241, 241, 241));
        button.setForeground(new Color(44, 44, 44));
        button.setFocusable(false);
    }

    // Style PanelBorder (label & chartTitle) - Dashboard Panel
    public static void stylePanelBorder(JPanel panel,String title) {
         panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(82, 82, 82)),
            title,
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Verdana", Font.BOLD, 16),
            new Color(59, 59, 59)
        ));
    }

    // Style Title Label - AddDataCategories (Income, Savings, Budget, Expenses)
    public static void styleTitleLabel(JLabel label){
        label.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        label.setForeground(new Color(25,25,25));
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    }

    // Style PanelBackgroundColor - Dashboard Panel
    public static void stylePanelBackground(JPanel... panels){
        for(JPanel panel : panels){
            panel.setBackground(new Color(248, 248, 248));
        }
    }

    // Style FormFont - AddDataCategories (Income, Savings, Budget, Expenses)
    public static void styleFont(Component comp, Font labelFont, Font fieldFont) {

        if (comp instanceof JLabel label) {
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
            label.setFont(labelFont);

        } else if (comp instanceof JTextField textField) {
            textField.setBorder(BorderFactory.createEmptyBorder(11, 10, 10, 5));
            textField.setFont(fieldFont);

        } else if (comp instanceof JComboBox comboBox) {
            Dimension size = comboBox.getPreferredSize();
            comboBox.setPreferredSize(new Dimension(size.width, 30));
            comboBox.setFont(fieldFont);
            comboBox.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 0));
            comboBox.setFocusable(false);

            comboBox.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value,
                                                              int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                renderer.setFont(fieldFont);
                renderer.setBorder(BorderFactory.createEmptyBorder(4, 5, 2, 5));
                return renderer;
                }
            });

        } else if (comp instanceof JButton button) {
            button.setFont(new Font("Verdana", Font.BOLD, 12));
            button.setBorder(BorderFactory.createEmptyBorder(7, 15, 7, 15));
            button.setFocusable(false);

        } else if (comp instanceof JSpinner spinner) {
            spinner.setFont(fieldFont);
            spinner.setBorder(BorderFactory.createEmptyBorder(4, 5, 4, 5));

            if (spinner.getEditor() instanceof JSpinner.DefaultEditor editor) {
                JFormattedTextField textField = editor.getTextField();
                    textField.setFont(fieldFont);
                    textField.setHorizontalAlignment(SwingConstants.CENTER);
                    textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
        }
    }

    // Style GBC - Dashboard, AddDataCategories (Income, Savings, Budget, Expense) Panels
    public static GridBagConstraints getGbc(int x, int y, int fill, int anchor, int weight){
        GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = x;
            gbc.gridy = y;
            gbc.insets = new Insets(5,5,5,5);
            gbc.anchor = anchor;
            gbc.fill = fill;
            gbc.weightx = weight;

        return gbc;
    }

    // Style ProgressbarColor - Dashboard Panel
    public static Color getGradientColor(double percentage) {
        if (percentage < 0) percentage = 0;
        if (percentage > 1) percentage = 1;

        int red      = (int) (percentage * 255);
        int green    = (int) ((1 - percentage) * 255);
        int blue     = 0;
        double light = 0.1;

        red   = (int) (red + (255 - red) * light);
        green = (int) (green + (255 - green) * light);
        blue  = (int) (blue + (255 - blue) * light);

        return new Color(red, green, blue);
    }

    // StyleJTable - Expense Panel
    public static void styleSummaryTable(JTable table){
        table.setRowHeight(25);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);

        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Date
        table.getColumnModel().getColumn(1).setPreferredWidth(200); // Category
        table.getColumnModel().getColumn(2).setPreferredWidth(130); // Amount
        table.getColumnModel().getColumn(3).setPreferredWidth(300); // Notes

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0; i < table.getColumnCount(); i++){
            table.getTableHeader().setDefaultRenderer(renderer);
            table.getColumnModel().getColumn(i).setResizable(false);
        }

        JTableHeader header = table.getTableHeader();
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel lbl = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            lbl.setFont(new Font("Arial", Font.BOLD, 14));
            lbl.setBackground(new Color(107, 107, 107));
            lbl.setForeground(Color.WHITE);
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setOpaque(true);

            return lbl;
            }
        });
    }

    public static <T> void styleFilterDropdown(JComboBox<T> combo) {
        combo.setFont(new Font("Verdana", Font.PLAIN, 14));
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
    }
}