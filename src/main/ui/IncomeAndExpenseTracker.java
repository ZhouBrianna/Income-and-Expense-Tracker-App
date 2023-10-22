package ui;

import model.ExpenseTransaction;
import model.FinancialRecords;
import model.IncomeTransaction;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

// Income and Expense Tracker Application
public class IncomeAndExpenseTracker {
    private static final String JSON_STORE = "./data/financialrecords.json";
    private FinancialRecords financialRecords;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Effects: run the application
    public IncomeAndExpenseTracker() throws FileNotFoundException {
        input = new Scanner(System.in);
        financialRecords = new FinancialRecords();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean isRunning = true;
        String command;
        input = new Scanner(System.in);

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
        switch (command) {
            case "1":
                addIncomeTransaction();
                break;
            case "2":
                addExpenseTransaction();
                break;
            case "3":
                calculateTotalIncome();
                break;
            case "4":
                calculateTotalExpense();
                break;
            case "5":
                calculateNetIncome();
                break;
            case "6":
                viewTransactions();
                break;
            case "7":
                printTransactions();
                break;
            case "8":
                saveTransactions();
                break;
            case "9":
                loadTransactions();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
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
        System.out.println("\t7 -> Print Transactions");
        System.out.println("\t8 -> Save Transactions");
        System.out.println("\t9 -> Load Transactions");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: add an income transaction to financial records
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

    // MODIFIES: this
    // EFFECTS: add an expense transaction to financial records
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

    // EFFECTS: print out the total income
    private void calculateTotalIncome() {
        System.out.println("Total Income is: $" + financialRecords.calculatedTotalIncome());
    }

    // EFFECTS: print out the total expense
    private void calculateTotalExpense() {
        System.out.println("Total Expense is: $" + financialRecords.calculatedTotalExpense());
    }

    // EFFECTS: print out the net income
    private void calculateNetIncome() {
        System.out.println("Net Income is: $" + financialRecords.calculateNetIncome());
    }

    // EFFECTS: view a list of income transactions and a list of expense transactions
    private void viewTransactions() {
        System.out.println("Income Transactions:");
        List<IncomeTransaction> incomeTransactions = financialRecords.getIncomeTransactions();
        if (financialRecords.getIncomeTransactions().isEmpty()) {
            System.out.println("No income transactions.");
        } else {
            for (IncomeTransaction incomeTransaction : incomeTransactions) {
                System.out.print("Amount: $" + incomeTransaction.getIncomeAmount() + " ");
                System.out.print("Date: " + incomeTransaction.getDate() + " ");
                System.out.print("Description: " + incomeTransaction.getDescription() + "\n");
            }
        }
        System.out.println("Expense Transactions:");
        List<ExpenseTransaction> expenseTransactions = financialRecords.getExpenseTransactions();
        if (expenseTransactions.isEmpty()) {
            System.out.println("No expense transactions.");
        } else {
            for (ExpenseTransaction expenseTransaction : expenseTransactions) {
                System.out.print("Amount: $" + expenseTransaction.getExpenseAmount() + " ");
                System.out.print("Date: " + expenseTransaction.getDate() + " ");
                System.out.print("Description: " + expenseTransaction.getDescription() + "\n");
            }
        }
    }

    // EFFECTS: prints all the transactions in the financial records
    private void printTransactions() {
        List<IncomeTransaction> incomeTransactions = financialRecords.getIncomeTransactions();
        List<ExpenseTransaction> expenseTransactions = financialRecords.getExpenseTransactions();

        for (IncomeTransaction incomeTransaction : incomeTransactions) {
            System.out.println(incomeTransaction);
        }

        for (ExpenseTransaction expenseTransaction: expenseTransactions) {
            System.out.println(expenseTransaction);
        }
    }

    // EFFECTS: saves the transaction to file
    private void saveTransactions() {
        try {
            jsonWriter.open();
            jsonWriter.write(financialRecords);
            jsonWriter.close();
            System.out.println("Saved" + "financialRecords" + "to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads financial records from file
    private void loadTransactions() {
        try {
            financialRecords = jsonReader.read();
            System.out.println("Loaded " + "financialRecords"+ " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}

