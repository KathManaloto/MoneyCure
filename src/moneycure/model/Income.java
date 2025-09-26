package moneycure.model;

public class Income {

    // ===== FIELDS =====
    private final String date;
    private final String incomeSource;
    private final double amount;
    private final String notes;

    // ===== CONSTRUCTOR =====
    public Income(String date, String incomeSource, double amount, String notes){
        this.date = date;
        this.incomeSource = incomeSource;
        this.amount = amount;
        this.notes = notes;

    }
}
