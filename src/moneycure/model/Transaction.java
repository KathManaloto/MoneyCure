package moneycure.model;

import java.time.LocalDate;

public class Transaction {

    // ===== FIELDS =====
    private LocalDate date;
    private String category;
    private String description;
    private double amount;
    private String notes;

    // ===== CONSTRUCTOR =====
    public Transaction(LocalDate date, String category,  String description, double amount, String notes){
        this.date        = date;
        this.category    = category;
        this.description = description;
        this.amount      = amount;
        this.notes       = notes;
    }

    // ===== GETTERS =====
    public LocalDate getDate(){ return date; }
    public String getCategory(){ return category; }
    public String getDescription(){ return description; }
    public double getAmount(){ return amount; }
    public String getNotes(){ return notes; }

}
