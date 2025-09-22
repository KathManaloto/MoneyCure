// expense data container
package moneycure.model;

public class Expense {

    private final String date;
    private final String category;
    private final double amount;
    private final String notes;

    public Expense(String date, String category, double amount, String notes){
        this.date     = date;
        this.category = category;
        this.amount   = amount;
        this.notes    = notes;
    }

    public Expense (String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.date = null;
        this.notes = null;
    }

    public String getDate(){ return date; }
    public String getCategory(){ return category; }
    public double getAmount(){ return amount; }
    public String getNotes(){ return notes; }
}
