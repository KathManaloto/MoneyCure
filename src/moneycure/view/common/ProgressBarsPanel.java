package moneycure.view.common;

import moneycure.view.Helper;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ProgressBarsPanel extends JPanel {

    private Map<String, Double> budgets;
    private Map<String, Double> expenses;

    public ProgressBarsPanel(Map<String, Double> budgets, Map<String, Double> expenses) {
        this.budgets = budgets;
        this.expenses = expenses;
        setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {
        removeAll(); // clear old components before rebuilding
        int gridY = 0;

        for (String category : budgets.keySet()) {
            double budget = budgets.get(category);
            double expense = expenses.getOrDefault(category, 0.0);

            JLabel label = new JLabel(category);
            add(label, Helper.getGbc(0, gridY, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 0));

            JProgressBar progressBar = new JProgressBar(0, (int) budget);
            progressBar.setValue((int) Math.min(expense, budget)); // cap at budget
            progressBar.setPreferredSize(new Dimension(progressBar.getPreferredSize().width, 35));

            int value = progressBar.getValue();
            int max = progressBar.getMaximum();
            float percent = (max > 0) ? (float) value / max : 0;

            double remaining = budget - expense;
            progressBar.setStringPainted(true);
            progressBar.setString("₱" + String.format("%.2f", Math.max(0, remaining)) + " remaining");
            progressBar.setFont(new Font("Arial", Font.PLAIN, 11));
            progressBar.setForeground(Helper.getGradientColor(percent));
            progressBar.setBackground(new Color(236, 236, 236));
            progressBar.setBorder(BorderFactory.createLineBorder(new Color(138, 138, 138)));

            progressBar.setUI(new javax.swing.plaf.basic.BasicProgressBarUI() {
                protected Color getSelectionForeground() { return Color.BLACK; }
                protected Color getSelectionBackground() { return Color.BLACK; }
            });

            add(progressBar, Helper.getGbc(1, gridY, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1));

            gridY++;
        }

        revalidate();
        repaint();
    }

    public void updateData(Map<String, Double> budgets, Map<String, Double> expenses) {
        this.budgets = budgets;
        this.expenses = expenses;
        buildUI(); // rebuild the bars with new data
    }
}
