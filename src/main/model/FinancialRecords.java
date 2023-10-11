package model;

import java.util.ArrayList;
import java.util.List;

// Represents a financial records having a list of expense transactions and a list of income transactions
public class FinancialRecords {
    private final List<ExpenseTransaction> expenseTransactions;
    private final List<IncomeTransaction> incomeTransactions;

    public FinancialRecords() {
        expenseTransactions = new ArrayList<>();
        incomeTransactions = new ArrayList<>();
    }

    // EFFECTS: add an income transaction to the record
    public void addIncomeTransaction(IncomeTransaction transaction) {
        incomeTransactions.add(transaction);
    }

    // EFFECTS: add an expense transaction to the record
    public void addExpenseTransaction(ExpenseTransaction transaction) {
        expenseTransactions.add(transaction);
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

        return totalIncome;
    }

    // EFFECTS: calculate the total expense
    public double calculatedTotalExpense() {
        double totalExpense = 0;
        for (ExpenseTransaction transaction : expenseTransactions) {
            totalExpense = totalExpense + transaction.getExpenseAmount();
        }

        return totalExpense;
    }

    // EFFECTS: calculate the net income (income - expense)
    public double calculateNetIncome() {
        double netIncome;
        double totalIncome = calculatedTotalIncome();
        double totalExpense = calculatedTotalExpense();
        netIncome = totalIncome - totalExpense;
        return netIncome;
    }

    // EFFECTS: Get a list of expense transactions
    public List<ExpenseTransaction> getExpenseTransactions() {
        return expenseTransactions;
    }

    // EFFECTS: Get a list of income transactions
    public List<IncomeTransaction> getIncomeTransactions() {
        return incomeTransactions;
    }


}
