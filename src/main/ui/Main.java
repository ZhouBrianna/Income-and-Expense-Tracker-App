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
    }
}




