//add savings form
package moneycure.view.feature;

import moneycure.view.Helper;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SavingsPanel extends JPanel {

    public SavingsPanel(){
        initComponents();
    }

    private void initComponents(){
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(new Color(201, 201, 201));

        JLabel lblSavings = new JLabel("\uD83C\uDFE6 ADD SAVINGS", SwingConstants.CENTER);
        Helper.styleTitleLabel(lblSavings);

        JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBackground(new Color(218, 218, 218));

        JComboBox<String> savingsTypeCombo = new JComboBox<>(new String[]{
                "\uD83D\uDEA8 EMERGENCY FUNDS", "✈️ TRAVEL & VACATION", "\uD83C\uDF93 EDUCATION",
                "\uD83D\uDCBC CAREER & BUSINESS", "\uD83D\uDC16 GENERAL SAVINGS", "⚙ OTHERS"});
            formPanel.add(new JLabel("Savings Type: "), Helper.getGbc(0,0, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(savingsTypeCombo, Helper.getGbc(1,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            JTextField txtDate = new JTextField(25);
            txtDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            formPanel.add(new JLabel("Date (yyyy-MM-dd): "), Helper.getGbc(0,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(txtDate, Helper.getGbc(1,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            formPanel.add(new JLabel("Amount: "), Helper.getGbc(0,2,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(new JTextField(25), Helper.getGbc(1,2,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            formPanel.add(new JLabel("Notes: "), Helper.getGbc(0,3,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(new JTextField(25), Helper.getGbc(1,3,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            Helper.styleFont(formPanel, new Font("Arial", Font.BOLD,14), new Font("Segoe UI Emoji", Font.PLAIN, 14));

        JPanel btnSubmitCancelPanel = new JPanel(new GridLayout(1,2,10,10));
            btnSubmitCancelPanel.setPreferredSize(new Dimension(0, 50));

            JButton btnSubmitSavings = new JButton("Submit");
            JButton btnCancelSavings = new JButton("Cancel");

            btnSubmitCancelPanel.add(btnSubmitSavings);
            btnSubmitCancelPanel.add(btnCancelSavings);

            Helper.StyleBottomButtons(btnSubmitSavings);
            Helper.StyleBottomButtons(btnCancelSavings);

        add(lblSavings,BorderLayout.NORTH);
        add(formPanel,BorderLayout.CENTER);
        add(btnSubmitCancelPanel, BorderLayout.SOUTH);
    }
}

