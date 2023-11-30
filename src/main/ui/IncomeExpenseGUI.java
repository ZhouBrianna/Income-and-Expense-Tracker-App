package ui;

import model.Event;
import model.EventLog;
import model.ExpenseTransaction;
import model.FinancialRecords;
import model.IncomeTransaction;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

// Income and Expense Application Graphical User Interface
public class IncomeExpenseGUI extends JFrame implements ActionListener {

    private JLabel amount;
    private JLabel description;
    private JLabel date;
    private JLabel totalIncomeLabel;
    private JLabel totalExpenseLabel;
    private JLabel netIncomeLabel;
    private JLabel moneyLabel;
    private ImageIcon money;
    private JTextField amountText;
    private JTextField descriptionText;
    private JTextField dateText;
    private JButton addIncomeButton;
    private JButton addExpenseButton;
    private JButton viewTransactionButton;
    private JButton calculateButton;
    private JButton save;
    private JButton load;
    protected FinancialRecords financialRecords;

    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;

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
        Color backgroundColor = new Color(255, 219, 73);
        getContentPane().setBackground(backgroundColor);

        setLabels();
        setTextFields();
        setButtons();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleWindowClosing();
            }
        });

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

        totalIncomeLabel = new JLabel("Total Income");
        totalIncomeLabel.setBounds(430,180,200,150);
        this.add(totalIncomeLabel);

        totalExpenseLabel = new JLabel("Total Expense");
        totalExpenseLabel.setBounds(430,220,200,150);
        this.add(totalExpenseLabel);

        netIncomeLabel = new JLabel("Net Income");
        netIncomeLabel.setBounds(430,260,200,150);
        this.add(netIncomeLabel);

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

        addIncomeButton = createButton("Add Income", 400,30,150,30);
        addExpenseButton = createButton("Add Expense", 400,60,150,30);
        viewTransactionButton = createButton("View Transactions", 400,90,150,30);
        calculateButton = createButton("Calculate", 400, 150, 150, 30);
        save = createButton("Save", 30, 150,80,30);
        load = createButton("Load", 130,150,80,30);
    }

    // EFFECTS: create buttons by setting it bounds, adds it to the frame, attaches the ActionListener,
    //          and then returns the created button.
    private JButton createButton(String text, int x, int y, int width, int height) {

        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        this.add(button);
        button.addActionListener(this);
        return button;
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

        if (description.isEmpty() || date.isEmpty()) {
            showErrorDialog("Cannot be empty, please provide valid input");
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

        if (description.isEmpty() || date.isEmpty()) {
            showErrorDialog("Cannot be empty, please provide valid input");
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

    // EFFECTS: update and display the total income label
    private void calculateTotalIncome() {
        double totalIncome = financialRecords.calculatedTotalIncome();
        totalIncomeLabel.setText("Total Income: $" + String.format("%.2f", totalIncome));
    }

    // EFFECTS: update and display the total expense label
    private void calculateTotalExpense() {
        double totalExpense = financialRecords.calculatedTotalExpense();
        totalExpenseLabel.setText("Total Expense: $" + String.format("%.2f", totalExpense));
    }

    // EFFECTS: update and display the net income label
    private void calculateNetIncome() {
        double netIncome = financialRecords.calculateNetIncome();
        netIncomeLabel.setText("Net Income: $" + String.format("%.2f", netIncome));
    }

    // EFFECTS: print all events in the event log
    public void printEvents() {
        EventLog eventLog = EventLog.getInstance();
        Iterator<model.Event> iterator = eventLog.iterator();

        while (iterator.hasNext()) {
            Event event = iterator.next();
            System.out.println(event.toString());
        }
    }

    // EFFECTS: handle the window closing event
    private void handleWindowClosing() {
        printEvents();
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

        if (e.getSource() == calculateButton) {
            calculateTotalIncome();
            calculateTotalExpense();
            calculateNetIncome();
        }

        if (e.getSource() == viewTransactionButton) {
            new ViewTransaction(financialRecords).setVisible(true);
        }
    }
}

