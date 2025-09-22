// budget data container
package moneycure.model;

public class Budget {

    private String date;
    private String category;
    private double amount;
    private String notes;

    public Budget(String date, String category, double amount, String notes){
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.notes = notes;
    }

    public Budget(String category, double amount) {
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

