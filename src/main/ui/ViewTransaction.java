package ui;

import model.ExpenseTransaction;
import model.FinancialRecords;
import model.IncomeTransaction;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// A new window to view transactions
public class ViewTransaction extends JFrame {
    private final JTextArea transactions;
    private final FinancialRecords financialRecords;

    // Constructs a view transaction window with financial records
    public ViewTransaction(FinancialRecords financialRecords) {
        this.financialRecords = financialRecords;
        setTitle("Transactions");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        transactions = new JTextArea();
        transactions.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(transactions);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        displayTransactions();

        setVisible(true);
    }

    // EFFECTS: Display transactions in a proper format
    private void displayTransactions() {
        List<IncomeTransaction> incomeTransactions = financialRecords.getIncomeTransactions();
        transactions.append("Income Transactions:\n");
        for (IncomeTransaction incomeTransaction : incomeTransactions) {
            transactions.append(formatTransaction(incomeTransaction));
        }

        List<ExpenseTransaction> expenseTransactions = financialRecords.getExpenseTransactions();

        transactions.append("\nExpense Transactions:\n");
        for (ExpenseTransaction expenseTransaction : expenseTransactions) {
            transactions.append(formatTransaction(expenseTransaction));
        }
    }

    // EFFECTS: set up a format for income transaction
    private String formatTransaction(IncomeTransaction incomeTransaction) {
        return String.format("Amount: $%.2f | Date: %s | Description: %s\n",
                incomeTransaction.getIncomeAmount(),
                incomeTransaction.getDate(),
                incomeTransaction.getDescription());
    }

    // EFFECTS: set up a format for expense transaction
    private String formatTransaction(ExpenseTransaction expenseTransaction) {
        return String.format("Amount: $%.2f | Date: %s | Description: %s\n",
                expenseTransaction.getExpenseAmount(),
                expenseTransaction.getDate(),
                expenseTransaction.getDescription());
    }


}
