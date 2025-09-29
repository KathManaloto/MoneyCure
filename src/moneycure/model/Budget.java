// budget data container
package moneycure.model;

public class Budget {

    // ===== FIELDS =====
    private final String date;
    private final String category;
    private final double amount;
    private final String notes;

    // ===== CONSTRUCTOR =====
    public Budget(String date, String category, double amount, String notes){
        this.date     = date;
        this.category = category;
        this.amount   = amount;
        this.notes    = notes;
    }

    // ===== GETTERS =====
    public String getDate(){ return date; }
    public String getCategory(){ return category; }
    public double getAmount(){ return amount; }
    public String getNotes(){ return notes; }
}

