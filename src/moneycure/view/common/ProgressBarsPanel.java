package moneycure.view.common;

import moneycure.view.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ProgressBarsPanel extends JPanel {

    // ===== FIELDS =====
    private Map<String, Double> budgets, expenses;

    // ===== CONSTRUCTOR =====
    public ProgressBarsPanel(Map<String, Double> budgets, Map<String, Double> expenses) {
        this.budgets = budgets;
        this.expenses = expenses;
        setLayout(new GridBagLayout());
        buildUI();
    }

    // ===== BUILD UI =====
    private void buildUI() {
        removeAll(); // clear old components before rebuilding

            int gridY = 0;

            for (String category : budgets.keySet()) {
                if ("📦 Others".equalsIgnoreCase(category)) {continue; }  // OTHERS IN LAST ROW
                addCategoryRow(category, budgets, expenses, gridY++);
            }

            // Add "Others" last if present
            if (budgets.containsKey("📦 Others")) { addCategoryRow("📦 Others", budgets, expenses, gridY++);}

        revalidate();
        repaint();
    }

    private void addCategoryRow(String category, Map<String, Double> budgets, Map<String, Double> expenses, int gridY) {

        double budget    = budgets.get(category);
        double expense   = expenses.getOrDefault(category, 0.0);
        int max          = (int) Math.max(budget, expense);
        double remaining = budget - expense;

            // CREATE PROGRESSBAR
            JLabel categoryLabel     = new JLabel(category);
            JProgressBar progressBar = new JProgressBar(0, max);

            if (remaining >= 0) {
                progressBar.setString("₱" + String.format("%.2f", remaining) + " remaining");
                progressBar.setForeground(Helper.getGradientColor((float) expense / (float) budget));

            } else {
                progressBar.setString("-₱" + String.format("%.2f", Math.abs(remaining)) + " over");
                progressBar.setForeground(Color.RED); // highlight over-budget
            }

            // STYLE PROGRESSBAR
            progressBar.setValue((int) expense);
            progressBar.setPreferredSize(new Dimension(progressBar.getPreferredSize().width, 35));
            progressBar.setStringPainted(true);
            progressBar.setFont(new Font("Monospaced", Font.PLAIN, 13));
            progressBar.setBackground(new Color(236, 236, 236));
            progressBar.setBorder(BorderFactory.createLineBorder(new Color(138, 138, 138)));
            progressBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI() {
                protected Color getSelectionForeground() { return Color.BLACK; }
                protected Color getSelectionBackground() { return Color.BLACK; }
            });

            progressBar.setToolTipText(
                "<html><b>Category:</b> " + category +
                "<br><b>Budget:</b> ₱" + String.format("%.2f", budget) +
                "<br><b>Expenses:</b> ₱" + String.format("%.2f",expense) +
                "</html>"
            );

        add(categoryLabel, Helper.getGbc(0, gridY, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 0));
        add(progressBar, Helper.getGbc(1, gridY, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1));
    }

    // ===== UPDATE DATA =====
    public void updateData(Map<String, Double> budgets, Map<String, Double> expenses) {
        this.budgets = budgets;
        this.expenses = expenses;
        buildUI(); // rebuild the bars with new data
    }
}