package ui;

import java.io.FileNotFoundException;

// Entry to the IncomeAndExpenseTracker Application
public class Main {
    public static void main(String[] args) {
        try {
            new IncomeAndExpenseTracker();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
