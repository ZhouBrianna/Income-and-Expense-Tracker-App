package ui;

import model.ExpenseTransaction;
import model.FinancialRecords;
import model.IncomeTransaction;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Income and Expense Application Graphical User Interface
public class IncomeExpenseGUI extends JFrame implements ActionListener {

    private JLabel amount;
    private JLabel description;
    private JLabel date;
    private JTextField amountText;
    private JTextField descriptionText;
    private JTextField dateText;
    private JButton addIncomeButton;
    private JButton addExpenseButton;
    private JButton viewTransactionButton;
    private JButton save;
    private JButton load;
    private JLabel moneyLabel;
    private ImageIcon money;
    protected FinancialRecords financialRecords;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private static final String JSON_STORE = "./data/financialrecords.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    // Construct Income and Expense Application Graphical User Interface
    public IncomeExpenseGUI() {

        financialRecords = new FinancialRecords();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setTitle("Income and Expense Tracker");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setLabels();
        setTextFields();
        setButtons();

        setVisible(true);
    }

    // Effects: set labels and set locations of the labels
    private void setLabels() {

        amount = new JLabel("Amount");
        amount.setBounds(30,20,200,50);
        this.add(amount);

        description = new JLabel("Description");
        description.setBounds(30, 25, 200, 100);
        this.add(description);

        date = new JLabel("Date");
        date.setBounds(30,30,200,150);
        this.add(date);

        money = new ImageIcon("data/money.png");
        moneyLabel = new JLabel(money);
        moneyLabel.setBounds(0,200,300,230);
        add(moneyLabel);

    }

    // EFFECTS: set text fields
    private void setTextFields() {

        amountText = new JTextField();
        amountText.setBounds(110,30,200,30);
        this.add(amountText);

        descriptionText = new JTextField();
        descriptionText.setBounds(110,60,200,30);
        this.add(descriptionText);

        dateText = new JTextField();
        dateText.setBounds(110,90,200,30);
        this.add(dateText);
    }


    // EFFECTS: set buttons
    private void setButtons() {
        addIncomeButton = new JButton("Add Income");
        addIncomeButton.setBounds(400,30,150,30);
        this.add(addIncomeButton);
        addIncomeButton.addActionListener(this);

        addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setBounds(400,60,150,30);
        this.add(addExpenseButton);
        addExpenseButton.addActionListener(this);

        viewTransactionButton = new JButton("View Transactions");
        viewTransactionButton.setBounds(400,90,150,30);
        this.add(viewTransactionButton);
        viewTransactionButton.addActionListener(this);

        save = new JButton("Save");
        save.setBounds(80, 150,100,30);
        this.add(save);
        save.addActionListener(this);


        load = new JButton("Load");
        load.setBounds(200,150,100,30);
        this.add(load);
        load.addActionListener(this);

    }

    // EFFECTS: add income to list of transactions when "Add Income" is clicked
    private void handleAddIncome() {

        double amount = Double.parseDouble(amountText.getText());
        String description = descriptionText.getText();
        String date = dateText.getText();

        if (amount < 0.0) {
            showErrorDialog("Cannot input negative income amount, please try again");
            return;
        }

        IncomeTransaction incomeTransaction = new IncomeTransaction(amount, date, description);
        financialRecords.addIncomeTransaction(incomeTransaction);

    }

    // EFFECTS: add expense to list of transactions when "Add Expense" is clicked
    private void handleAddExpense() {
        double amount = Double.parseDouble(amountText.getText());
        String description = descriptionText.getText();
        String date = dateText.getText();

        if (amount < 0.0) {
            showErrorDialog("Cannot input negative expense amount, please try again");
            return;
        }

        ExpenseTransaction expenseTransaction = new ExpenseTransaction(amount, date, description);
        financialRecords.addExpenseTransaction(expenseTransaction);
    }

    // EFFECTS: show an error dialog with the given message
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // EFFECTS: saves the transaction to file
    private void handleSave() {
        try {
            jsonWriter.open();
            jsonWriter.write(financialRecords);
            jsonWriter.close();
            System.out.println("Saved financial records to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: load the transaction from file
    private void handleLoad() {
        try {
            financialRecords = jsonReader.read();
            System.out.println("Loaded financial records from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: respond when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIncomeButton) {
            handleAddIncome();
        }

        if (e.getSource() == addExpenseButton) {
            handleAddExpense();
        }

        if (e.getSource() == save) {
            handleSave();
        }

        if (e.getSource() == load) {
            handleLoad();
        }

        if (e.getSource() == viewTransactionButton) {
            new ViewTransaction(financialRecords).setVisible(true);
        }
    }
}

