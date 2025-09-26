package moneycure.model;

public class Income {

    // ===== FIELDS =====
    private final String date;
    private final String incomeCombo;
    private final double amount;
    private final String notes;

    // ===== CONSTRUCTOR =====
    public Income(String date, String incomeSource, double amount, String notes){
        this.date = date;
        this.incomeCombo = incomeSource;
        this.amount = amount;
        this.notes = notes;
    }

    // ===== GETTERS =====
    public String getDate(){ return date; }
    public String getIncomeCombo(){ return incomeCombo; }
    public double getAmount(){ return amount; }
    public String getNotes(){ return notes; }

}
