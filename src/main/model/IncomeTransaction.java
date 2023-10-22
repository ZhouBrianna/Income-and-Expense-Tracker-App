package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an income transaction having a amount, date and income description.
public class IncomeTransaction implements Writable {
    private double incomeAmount;
    private final String date;
    private String description;

    // Construct an income transaction with the given amount, date and description
    public IncomeTransaction(double amount, String date, String description) {
        this.incomeAmount = amount;
        this.date = date;
        this.description = description;

    }

    public double getIncomeAmount() {
        return incomeAmount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS: delete an income description
    public void removeDescription() {
        this.description = null;
    }


    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: amount is added to expenseAmount and return the updated expenseAmount
    public double moreIncome(double amount) {
        this.incomeAmount = incomeAmount + amount;
        return incomeAmount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", incomeAmount);
        json.put("date", date);
        json.put("description", description);
        return json;
    }


}


