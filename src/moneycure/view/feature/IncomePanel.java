//add income form
package moneycure.view.feature;

import moneycure.view.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IncomePanel extends JPanel {

    // ===== FIELDS =====
    JComboBox<String> incomeCombo;

    // ===== CONSTRUCTOR =====
    public IncomePanel(){
       initComponents();
    }

    // ===== INIT COMPONENTS =====
    private void initComponents(){
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(new Color(201, 201, 201));

        //titleLabel & formPanel
        JLabel lblIncome = new JLabel("\uD83D\uDCB8 ADD INCOME",SwingConstants.CENTER);
        JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBackground(new Color(218, 218, 218));

            incomeCombo = new JComboBox<>(new String[]{
                    "\uD83D\uDCBC SALARY",
                    "\uD83C\uDFE0 RENT",
                    "\uD83D\uDCC8 BUSINESS",
                    "\uD83D\uDDA5️ FREELANCE",
                    "⚙️ OTHERS"
            });

            formPanel.add(new JLabel("Income Source: "), Helper.getGbc(0,0, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(incomeCombo, Helper.getGbc(1,0, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

        JTextField txtDate = new JTextField(25);
            txtDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            formPanel.add(new JLabel("Date (yyyy-MM-dd): "), Helper.getGbc(0,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(txtDate, Helper.getGbc(1,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            formPanel.add(new JLabel("Amount: "), Helper.getGbc(0,2, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(new JTextField(25), Helper.getGbc(1,2, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            formPanel.add(new JLabel("Notes: "), Helper.getGbc(0,3, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(new JTextField(25), Helper.getGbc(1,3, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
        Helper.styleTitleLabel(lblIncome);

        Helper.styleFont(formPanel,new Font("Arial",Font.BOLD, 14), new Font("Segoe UI Emoji", Font.PLAIN, 14));

        JPanel btnSubmitCancelPanel = new JPanel(new GridLayout(1,2,10,10));
            btnSubmitCancelPanel.setPreferredSize(new Dimension(0, 50));

            JButton btnSubmitIncome = new JButton("Submit");
            JButton btnCancelIncome = new JButton("Cancel");

            Helper.styleBottomButtons(btnSubmitIncome);
            Helper.styleBottomButtons(btnCancelIncome);

            btnSubmitCancelPanel.add(btnSubmitIncome);
            btnSubmitCancelPanel.add(btnCancelIncome);

        add(lblIncome,BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(btnSubmitCancelPanel, BorderLayout.SOUTH);
    }
}
