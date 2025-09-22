// JFrame with sidebar and CardLayout
package moneycure.view;

import javax.swing.*;
import java.awt.*;
import moneycure.view.category.*;
import moneycure.view.feature.*;

public class MainFrame extends JFrame {

    // ===== FIELDS =====
    private JPanel mainPanel,cardPanel;
    private CardLayout cardLayout;
    private JButton btnDashboard,btnAddData,btnManage,btnAnalysis;

    private AddDataPanel addData;
    private DashboardPanel dashboard;

    public static final String DASHBOARD = "Dashboard";
    public static final String ADD_DATA  = "Add Data";
    public static final String MANAGE    = "Manage Finances";
    public static final String ANALYSIS  = "Financial Analysis";

    // ===== CONSTRUCTOR =====
    public MainFrame(){

        super("MoneyCure");

        setSize(900,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        setContentPane(mainPanel);
        setVisible(true);
    }

    // ===== INIT COMPONENTS =====
    private void initComponents(){

        // ----- MAIN PANEL -----
        mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // --- Sidebar ---
        JPanel sidebar = new JPanel();
            sidebar.setLayout(new GridLayout(0,1,10,10));
            sidebar.setBackground(new Color(128, 128, 128));
            sidebar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0,0,0,3, new Color(255, 255, 255)),
                BorderFactory.createEmptyBorder(10,10,10,10)
            ));

        // SidebarButtons
        btnDashboard = createSidebarButton("Dashboard");
        btnAddData   = createSidebarButton("Add Data");
        btnManage    = createSidebarButton("Manage Finances");
        btnAnalysis  = createSidebarButton("Financial Analysis");

        sidebar.add(btnDashboard);
        sidebar.add(btnAddData);
        sidebar.add(btnManage);
        sidebar.add(btnAnalysis);

        // ----- CardPanel -----
        cardLayout = new CardLayout();
        cardPanel  = new JPanel(cardLayout);

        // --- Features ---
        dashboard = new DashboardPanel();
        addData = new AddDataPanel();
        ManageFinancesPanel manage = new ManageFinancesPanel();
        FinancialAnalysisPanel analysis = new FinancialAnalysisPanel();

        cardPanel.add(dashboard,DASHBOARD);
        cardPanel.add(addData,ADD_DATA);
        cardPanel.add(manage,MANAGE);
        cardPanel.add(analysis,ANALYSIS);

        mainPanel.add(sidebar,BorderLayout.WEST);
        mainPanel.add(cardPanel,BorderLayout.CENTER);

        // DEFAULT VIEW
        showCard(DASHBOARD);
    }

    // ===== HELPER - CREATE SIDEBAR =====
    private JButton createSidebarButton(String btnName){
        JButton button = new JButton(btnName);
            button.setFont(new Font("Verdana", Font.BOLD,14));
            button.setForeground(new Color(33, 33, 33));
            button.setBackground(new Color(204, 204, 204));
            button.setFocusable(false);
            return button;
    }

    // ===== HELPER - SHOW CARD =====
    public void showCard(String name){
        cardLayout.show(cardPanel,name);
    }

    // ===== GETTERS =====
    public JButton getBtnDashboard(){ return btnDashboard; }
    public JButton getBtnAddData(){ return btnAddData; }
    public JButton getBtnManage(){ return btnManage; }
    public JButton getBtnAnalysis(){ return btnAnalysis;}

    public AddDataPanel getAddData(){ return addData; }
    public DashboardPanel getDashboardPanel() { return dashboard; }

}

