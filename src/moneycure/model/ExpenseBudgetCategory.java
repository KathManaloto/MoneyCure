package moneycure.model;

public class ExpenseBudgetCategory {

    private final String name;              // clean category (e.g. "🏠HOUSING")
    private final String displayName;       // what user sees (e.g. "🏠 HOUSING (e.g. Rent, mortgage...)")

    public ExpenseBudgetCategory(String name, String displayName){
        this.name = name;
        this.displayName = displayName;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return displayName;           // JComboBox uses this for display
    }
}
