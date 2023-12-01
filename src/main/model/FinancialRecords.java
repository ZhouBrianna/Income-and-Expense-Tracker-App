package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a financial records having a list of expense transactions and a list of income transactions
public class FinancialRecords implements Writable {
    private final List<ExpenseTransaction> expenseTransactions;
    private final List<IncomeTransaction> incomeTransactions;

    // Construct a financial records with empty list of expense transactions and income transactions
    public FinancialRecords() {
        expenseTransactions = new ArrayList<>();
        incomeTransactions = new ArrayList<>();
    }

    // EFFECTS: add an income transaction to the record
    public void addIncomeTransaction(IncomeTransaction transaction) {
        incomeTransactions.add(transaction);

        String eventMessage = String.format(
                "Added an income transaction: Date: %s, Description: %s, Amount: $%.2f",
                transaction.getDate(), transaction.getDescription(), transaction.getIncomeAmount()
        );

        EventLog.getInstance().logEvent(new Event(eventMessage));
    }

    // EFFECTS: add an expense transaction to the record
    public void addExpenseTransaction(ExpenseTransaction transaction) {
        expenseTransactions.add(transaction);

        String eventMessage = String.format(
                "Added an expense transaction: Date: %s, Description: %s, Amount: $%.2f",
                transaction.getDate(), transaction.getDescription(), transaction.getExpenseAmount()
        );

        EventLog.getInstance().logEvent(new Event(eventMessage));
    }

    // EFFECTS: delete an income transaction to the record
    public void deleteIncomeTransaction(IncomeTransaction transaction) {
        incomeTransactions.remove(transaction);
    }

    // EFFECTS: delete an expense transaction to the record
    public void deleteExpenseTransaction(ExpenseTransaction transaction) {
        expenseTransactions.remove(transaction);
    }

    // EFFECTS: calculate the total income
    public double calculatedTotalIncome() {
        double totalIncome = 0;
        for (IncomeTransaction transaction : incomeTransactions) {
            totalIncome = totalIncome + transaction.getIncomeAmount();
        }

        EventLog.getInstance().logEvent(new Event("Calculated Total Income: $" + totalIncome));
        return totalIncome;

    }

    // EFFECTS: calculate the total expense
    public double calculatedTotalExpense() {
        double totalExpense = 0;
        for (ExpenseTransaction transaction : expenseTransactions) {
            totalExpense = totalExpense + transaction.getExpenseAmount();
        }

        EventLog.getInstance().logEvent(new Event("Calculated Total Expense: $" + totalExpense));
        return totalExpense;
    }

    // EFFECTS: calculate the net income (income - expense)
    public double calculateNetIncome() {
        double netIncome;
        double totalIncome = calculatedTotalIncome();
        double totalExpense = calculatedTotalExpense();
        netIncome = totalIncome - totalExpense;
        EventLog.getInstance().logEvent(new Event("Calculated Net Income: $" + netIncome));
        return netIncome;
    }

    // EFFECTS: Get a list of expense transactions
    public List<ExpenseTransaction> getExpenseTransactions() {
        EventLog.getInstance().logEvent(new Event("Viewed list of expense transactions"));
        return expenseTransactions;
    }

    // EFFECTS: Get a list of income transactions
    public List<IncomeTransaction> getIncomeTransactions() {
        EventLog.getInstance().logEvent(new Event("Viewed list of income transactions"));
        return incomeTransactions;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Income Transactions", incomeTransactionsToJson());
        json.put("Expense Transactions", expenseTransactionsToJson());
        return json;
    }

    // EFFECTS: Converts a list of income transactions to a JSON array
    private JSONArray incomeTransactionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (IncomeTransaction transaction : incomeTransactions) {
            jsonArray.put(transaction.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: Converts a list of expense transactions to a JSON array
    private JSONArray expenseTransactionsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (ExpenseTransaction transaction : expenseTransactions) {
            jsonArray.put(transaction.toJson());
        }
        return jsonArray;
    }
}
