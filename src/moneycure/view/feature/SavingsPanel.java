//add savings form
package moneycure.view.feature;

import moneycure.view.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SavingsPanel extends JPanel {

    // ====== FIELDS =====
    private JButton btnSubmitSavings,btnCancelSavings;
    private JTextField txtAmountSavings,txtNotesSavings;
    private JSpinner dateSpinner;
    private JComboBox<String> savingsTypeCombo;

    // ====== CONSTRUCTOR =====
    public SavingsPanel(){
        initComponents();
    }

    // ===== INIT COMPONENTS =====
    private void initComponents(){
        setLayout(new BorderLayout(10,10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(new Color(201, 201, 201));

            // CREATE TITLE LABEL AND FORM PANEL
            JLabel lblSavings = new JLabel("\uD83C\uDFE6 ADD SAVINGS", SwingConstants.CENTER);
            JPanel formPanel = new JPanel(new GridBagLayout());

                // date
                JLabel lblDateSavings = new JLabel("Date (yyyy-MM-dd): ");
                    dateSpinner  = new JSpinner(new SpinnerDateModel());
                    JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
                    dateSpinner.setEditor(editor);
                    dateSpinner.setValue(new Date());

                formPanel.add(lblDateSavings, Helper.getGbc(0,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
                formPanel.add(dateSpinner, Helper.getGbc(1,0,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

                // savings type
                JLabel lblSavingsType = new JLabel("Savings Type: ");
                savingsTypeCombo = new JComboBox<>(new String[]{
                    "\uD83D\uDEA8 EMERGENCY FUNDS",
                    "✈️ TRAVEL & VACATION",
                    "\uD83C\uDF93 EDUCATION",
                    "\uD83D\uDCBC CAREER & BUSINESS",
                    "\uD83D\uDC16 GENERAL SAVINGS",
                    "⚙ OTHERS"
                });

                formPanel.add(lblSavingsType, Helper.getGbc(0,1, GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
                formPanel.add(savingsTypeCombo, Helper.getGbc(1,1,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

                // amount
                JLabel lblAmountSavings = new JLabel("Amount: ");
                txtAmountSavings = new JTextField(25);

                formPanel.add(lblAmountSavings, Helper.getGbc(0,2,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
                formPanel.add(txtAmountSavings, Helper.getGbc(1,2,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

                // notes
                JLabel lblNotesSavings = new JLabel("Notes: ");
                txtNotesSavings = new JTextField(25);

                formPanel.add(lblNotesSavings, Helper.getGbc(0,3,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));
                formPanel.add(txtNotesSavings, Helper.getGbc(1,3,GridBagConstraints.HORIZONTAL,GridBagConstraints.WEST,0));

            // SET STYLE TITLE AND FORM PANEL
            formPanel.setBackground(new Color(218, 218, 218));
            Helper.styleTitleLabel(lblSavings);
            Helper.styleFont(formPanel, new Font("Arial", Font.BOLD,14), new Font("Segoe UI Emoji", Font.PLAIN, 14));

            // CREATE SUBMIT/CANCEL_BUTTONS PANEL
            JPanel btnSubmitCancelPanel = new JPanel(new GridLayout(1,2,10,10));

                btnSubmitSavings = new JButton("Submit");
                btnCancelSavings = new JButton("Cancel");

                btnSubmitCancelPanel.add(btnSubmitSavings);
                btnSubmitCancelPanel.add(btnCancelSavings);

            btnSubmitCancelPanel.setPreferredSize(new Dimension(0, 50));
            Helper.styleBottomButtons(btnSubmitSavings);
            Helper.styleBottomButtons(btnCancelSavings);

        add(lblSavings,BorderLayout.NORTH);
        add(formPanel,BorderLayout.CENTER);
        add(btnSubmitCancelPanel, BorderLayout.SOUTH);
    }

    // ===== GETTERS =====
    public JButton getBtnSubmitSavings(){ return btnSubmitSavings; }
    public JButton getBtnCancelSavings(){ return btnCancelSavings; }
    public JSpinner getDateSpinner(){ return dateSpinner; }
    public JTextField getTxtAmountSavings(){ return txtAmountSavings; }
    public JTextField getTxtNotesSavings(){ return txtNotesSavings; }
    public JComboBox<String> getSavingsTypeCombo(){ return savingsTypeCombo; }
}