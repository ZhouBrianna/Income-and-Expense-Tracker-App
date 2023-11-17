package ui;

import javax.swing.*;
import java.io.FileNotFoundException;

// Entry to the IncomeAndExpenseTracker Application
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            IncomeExpenseGUI incomeExpenseGUI = new IncomeExpenseGUI();
            incomeExpenseGUI.setVisible(true);
        });

        try {
            new IncomeAndExpenseTracker();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}




