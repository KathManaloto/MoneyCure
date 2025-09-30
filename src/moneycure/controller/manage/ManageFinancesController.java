package moneycure.controller.manage;

import moneycure.view.sidebar.ManageFinancesPanel;

public class ManageFinancesController {

    private final ManageFinancesPanel manageFinancesPanel;

    public ManageFinancesController(ManageFinancesPanel manageFinancesPanel){
        this.manageFinancesPanel = manageFinancesPanel;
        initComponent();
    }

    private void initComponent(){

        // hook up dropdowns
        manageFinancesPanel.getViewFilterDropdown().addActionListener(e -> filterByView());
        manageFinancesPanel.getMonthDropdown().addActionListener(e -> filterByMonth());
        manageFinancesPanel.getYearDropdown().addActionListener(e -> filterByYear());

        // search field
        manageFinancesPanel.getTxtFieldSearch().addActionListener(e -> searchTransactions());
    }

    private void filterByView(){
        String selected = (String) manageFinancesPanel.getViewFilterDropdown().getSelectedItem();

    }

    private void filterByMonth(){
        String selected = (String) manageFinancesPanel.getMonthDropdown().getSelectedItem();
    }

    private void filterByYear(){
        Integer selected = (Integer) manageFinancesPanel.getYearDropdown().getSelectedItem();
    }

    private void searchTransactions(){
        String query = manageFinancesPanel.getTxtFieldSearch().getText();

    }

}
