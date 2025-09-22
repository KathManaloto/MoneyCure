package moneycure.view.common;

import moneycure.view.Helper;
import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {

    JLabel valueLabel;

    public SummaryPanel(String title) {
        setLayout(new BorderLayout());

        valueLabel = new JLabel("₱0.00", SwingConstants.CENTER);
        valueLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        valueLabel.setForeground(new Color(56, 56, 56));

        add(valueLabel, BorderLayout.CENTER);

        Helper.stylePanelBorder(this, title);
    }

    public void setValue(double value){
        valueLabel.setText("₱" + String.format("%.2f",value));
    }
}