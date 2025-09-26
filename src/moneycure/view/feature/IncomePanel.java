//add income form
package moneycure.view.feature;

import moneycure.view.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class IncomePanel extends JPanel {

    // ===== FIELDS =====
    private JComboBox<String> incomeCombo;
    private JSpinner dateSpinner;
    private JTextField txtAmountIncome, txtNotesIncome;
    private JButton btnSubmitIncome, btnCancelIncome;

    // ===== CONSTRUCTOR =====
    public IncomePanel(){ initComponents(); }

    // ===== INIT COMPONENTS =====
    private void initComponents(){
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(new Color(201, 201, 201));

            // CREATE TITLE LABEL AND FORM PANEL
            JLabel lblIncome = new JLabel("\uD83D\uDCB8 ADD INCOME",SwingConstants.CENTER);
            JPanel formPanel = new JPanel(new GridBagLayout());

            // date
            JLabel lblDateIncome = new JLabel("Date (yyyy-MM-dd): ");
            dateSpinner = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
            dateSpinner.setEditor(editor);
            dateSpinner.setValue(new Date());

            formPanel.add(lblDateIncome,Helper.getGbc(0,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(dateSpinner,Helper.getGbc(1,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            // income source
            JLabel lblIncomeSource = new JLabel("Income Source: ");
            incomeCombo = new JComboBox<>(new String[]{
                "\uD83D\uDCBC SALARY",
                "\uD83C\uDFE0 RENT",
                "\uD83D\uDCC8 BUSINESS",
                "\uD83D\uDDA5️ FREELANCE",
                "⚙️ OTHERS"
            });

            formPanel.add(lblIncomeSource, Helper.getGbc(0,1, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(incomeCombo, Helper.getGbc(1,1, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            // amount
            JLabel lblAmountIncome = new JLabel("Amount: ");
            txtAmountIncome = new JTextField(25);

            formPanel.add(lblAmountIncome, Helper.getGbc(0,2, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(txtAmountIncome, Helper.getGbc(1,2, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            // notes
            JLabel lblNotesIncome = new JLabel("Notes: ");
            txtNotesIncome = new JTextField(25);

            formPanel.add(lblNotesIncome, Helper.getGbc(0,3, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
            formPanel.add(txtNotesIncome, Helper.getGbc(1,3, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

        // SET STYLE TITLE AND FORM PANEL
        formPanel.setBackground(new Color(218, 218, 218));
        Helper.styleTitleLabel(lblIncome);
        Helper.styleFont(formPanel,new Font("Arial",Font.BOLD, 14), new Font("Segoe UI Emoji", Font.PLAIN, 14));

        // CREATE SUBMIT/CANCEL_BUTTONS PANEL
        JPanel btnSubmitCancelPanel = new JPanel(new GridLayout(1,2,10,10));

            // buttons
            btnSubmitIncome = new JButton("Submit");
            btnCancelIncome = new JButton("Cancel");

            btnSubmitCancelPanel.add(btnSubmitIncome);
            btnSubmitCancelPanel.add(btnCancelIncome);

        // Style buttons
        btnSubmitCancelPanel.setPreferredSize(new Dimension(0, 50));
        Helper.styleBottomButtons(btnSubmitIncome);
        Helper.styleBottomButtons(btnCancelIncome);

        add(lblIncome,BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(btnSubmitCancelPanel, BorderLayout.SOUTH);
    }

    // ===== GETTERS =====
    public JButton getBtnSubmitIncome(){ return btnSubmitIncome; }
    public JSpinner getDateSpinner(){ return dateSpinner; }
    public JComboBox<String> getIncomeCombo(){ return incomeCombo; }
    public JTextField getTxtAmountIncome(){ return txtAmountIncome; }
    public JTextField getTxtNotesIncome(){ return txtNotesIncome; }
}