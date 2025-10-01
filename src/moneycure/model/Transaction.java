package moneycure.model;

public class Transaction {

    // ===== FIELDS =====
    private String date;
    private String category;
    private String description;
    private double amount;
    private String notes;

    // ===== CONSTRUCTOR =====
    public Transaction(String date, String category,  String description, double amount, String notes){
        this.date        = date;
        this.category    = category;
        this.description = description;
        this.amount      = amount;
        this.notes       = notes;
    }

    // ===== GETTERS =====
    public String getDate(){ return date; }
    public String getCategory(){ return category; }
    public String getDescription(){ return description; }
    public double getAmount(){ return amount; }
    public String getNotes(){ return notes; }

}
