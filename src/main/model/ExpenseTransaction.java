package model;

import org.json.JSONObject;
import persistence.Writable;


// Represents an expense transaction having a ExpenseAmount, date and income description.
public class ExpenseTransaction implements Writable {
    private double expenseAmount;
    private final String date;
    private String description;

    // Construct an expense transaction with the given amount, date and description
    public ExpenseTransaction(double amount, String date, String description) {
        this.expenseAmount = amount;
        this.date = date;
        this.description = description;

    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    // MODIFIES: this
    // EFFECTS: delete an expense description
    public void removeDescription() {
        this.description = null;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: amount is added to expenseAmount and return the updated expenseAmount
    public double moreExpense(double amount) {
        this.expenseAmount = expenseAmount + amount;
        return expenseAmount;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("amount", expenseAmount);
        json.put("date", date);
        json.put("description", description);
        return json;
    }
}
