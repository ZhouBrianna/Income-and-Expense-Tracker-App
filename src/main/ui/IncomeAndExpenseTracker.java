package ui;

import model.ExpenseTransaction;
import model.FinancialRecords;
import model.IncomeTransaction;

import java.util.List;
import java.util.Scanner;

public class IncomeAndExpenseTracker {
    private FinancialRecords financialRecords;
    private Scanner input;

    // Effects: run the application
    public IncomeAndExpenseTracker() {
        runApp();
    }

    // modifies: this
    // effects: processes user input
    private void runApp() {
        boolean isRunning = true;
        String command = null;

        init();

        while (isRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                isRunning = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            addIncomeTransaction();
        } else if (command.equals("2")) {
            addExpenseTransaction();
        } else if (command.equals("3")) {
            calculateTotalIncome();
        } else if (command.equals("4")) {
            calculateTotalExpense();
        } else if (command.equals("5")) {
            calculateNetIncome();
        } else if (command.equals("6")) {
            viewTransactions();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes financialRecords
    private void init() {
        financialRecords = new FinancialRecords();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add Income Transaction");
        System.out.println("\t2 -> Add Expense Transaction");
        System.out.println("\t3 -> Calculate Total Income");
        System.out.println("\t4 -> Calculate Total Expense");
        System.out.println("\t5 -> Calculate Net Income");
        System.out.println("\t6 -> View Transactions");
        System.out.println("\tq -> quit");
    }

    // modifies: this
    // effects: add an income transaction to financial records
    private void addIncomeTransaction() {
        System.out.println("Enter income amount: $");
        double amount = input.nextDouble();

        if (amount < 0.0) {
            System.out.println("Cannot input negative income amount, please try again");
            return;
        }

        System.out.println("Enter transaction date (YYYY-MM-DD): ");
        String date = input.next();

        System.out.println("Enter description: ");
        String description = input.next();

        IncomeTransaction incomeTransaction = new IncomeTransaction(amount, date, description);
        financialRecords.addIncomeTransaction(incomeTransaction);
        System.out.println("Income transaction added successfully.");
    }

    // modifies: this
    // effects: add an expense transaction to financial records
    private void addExpenseTransaction() {
        System.out.println("Enter expense amount: $");
        double amount = input.nextDouble();

        if (amount < 0.0) {
            System.out.println("Cannot input negative expense amount, please try again");
            return;
        }

        System.out.println("Enter transaction date (YYYY-MM-DD): ");
        String date = input.next();

        System.out.println("Enter description: ");
        String description = input.next();

        ExpenseTransaction expenseTransaction = new ExpenseTransaction(amount, date, description);
        financialRecords.addExpenseTransaction(expenseTransaction);
        System.out.println("Expense transaction added successfully.");
    }


    private void calculateTotalIncome() {
        System.out.println("Total Income is:" + financialRecords.calculatedTotalIncome());
    }

    private void calculateTotalExpense() {
        System.out.println("Total Expense is:" + financialRecords.calculatedTotalExpense());
    }

    private void calculateNetIncome() {
        System.out.println("Net Income is: " + financialRecords.calculateNetIncome());
    }

    private void viewTransactions() {
        System.out.println("Income Transactions:");
        List<IncomeTransaction> incomeTransactions = financialRecords.getIncomeTransactions();
        if (financialRecords.getIncomeTransactions().isEmpty()) {
            System.out.println("No income transactions.");
        } else {
            for (IncomeTransaction incomeTransaction : incomeTransactions) {
                System.out.print("Amount: " + incomeTransaction.getIncomeAmount() + " ");
                System.out.print("Date: " + incomeTransaction.getDate() + " ");
                System.out.print("Description: " + incomeTransaction.getDescription() + " ");
            }
        }
        System.out.println();
        System.out.println("Expense Transactions:");
        List<ExpenseTransaction> expenseTransactions = financialRecords.getExpenseTransactions();
        if (expenseTransactions.isEmpty()) {
            System.out.println("No expense transactions.");
        } else {
            for (ExpenseTransaction expenseTransaction : expenseTransactions) {
                System.out.print("Amount: " + expenseTransaction.getExpenseAmount() + " ");
                System.out.print("Date: " + expenseTransaction.getDate() + " ");
                System.out.print("Description: " + expenseTransaction.getDescription() + " ");

            }
        }
    }


}

