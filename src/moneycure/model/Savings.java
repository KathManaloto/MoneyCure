package moneycure.model;

public class Savings {

    // ====== FIELDS =====
    private final String date;
    private final String savingsType;
    private final double savingsAmount;
    private final String savingsNotes;

    // ===== CONSTRUCTOR =====
    public Savings(String date, String savingsType, double savingsAmount, String savingsNotes){
        this.date = date;
        this.savingsType = savingsType;
        this.savingsAmount = savingsAmount;
        this.savingsNotes = savingsNotes;
    }

    // ===== GETTERS =====
    public String getDate(){ return date; }
    public String getSavingsType(){ return savingsType; }
    public double getSavingsAmount(){ return savingsAmount; }
    public String getSavingsNotes(){ return savingsNotes; }
}
