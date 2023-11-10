package ui;

import javax.swing.*;
import java.awt.*;

public class IncomeExpenseGUI extends JFrame {

    private JPanel leftPanel;
    private JPanel rightPanel;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public IncomeExpenseGUI() {
        initializeGUI();
        createLeftPanel();
        //createRightPanel();
        setVisible(true);
    }

    // EFFECTS: initialize the GUI
    private void initializeGUI() {
        setTitle("Income and Expense Tracker Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
    }

    private void createLeftPanel() {
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel incomeExpenseTrackerLabel = new JLabel("Income and Expense Tracker");
        incomeExpenseTrackerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(incomeExpenseTrackerLabel);

        JPanel inputPanel = createInputPanel();
        leftPanel.add(inputPanel);

        add(leftPanel, BorderLayout.WEST);
    }

    private JPanel createInputPanel() {

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JLabel addIncomeTransactionLabel = new JLabel("Add Income Transaction");
        addIncomeTransactionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.add(addIncomeTransactionLabel);

        JPanel amountPanel = new JPanel();
        amountPanel.setLayout(new BoxLayout(amountPanel, BoxLayout.X_AXIS));

        JLabel amountLabel = new JLabel("Amount");
        JTextField amountField = new JTextField();
        //amountField.setPreferredSize(new Dimension(200, 30));
        amountField.setBounds(200,20,30,40);
        amountPanel.add(amountLabel, BorderLayout.NORTH);
        amountPanel.add(amountField, BorderLayout.CENTER);

        JButton addIncomeButton = new JButton("Add Income");
        amountPanel.add(addIncomeButton, BorderLayout.SOUTH);

        inputPanel.add(amountPanel);

        return inputPanel;
    }

    private void createRightPanel() {
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        JLabel budgetPlannerLabel = new JLabel("Budget Planner");
        budgetPlannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(budgetPlannerLabel, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.CENTER);
    }
}





//    public IncomeExpenseGUI() {
//        this.setTitle("Income and Expense Tracker Application");
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(WIDTH, HEIGHT);
//
//        leftPanel = new JPanel();
//        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
//
//        JLabel incomeExpenseTrackerLabel = new JLabel("Income and Expense Tracker");
//        incomeExpenseTrackerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        leftPanel.add(incomeExpenseTrackerLabel);
//
//        JPanel inputPanel = new JPanel(new BorderLayout());
//
//        JLabel addIncomeTransactionLabel = new JLabel("Add Income Transaction");
//        //addIncomeTransactionLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        inputPanel.add(addIncomeTransactionLabel, BorderLayout.NORTH);
//
//        JPanel inputPanel1 = new JPanel();
//        inputPanel1.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
//
//        JLabel amountLabel = new JLabel("Amount");
//        inputPanel.add(amountLabel);
//
//        JTextField amountField = new JTextField();
//        amountField.setMaximumSize(new Dimension(200, 40));
//        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
//        inputPanel.add(amountField);
//
//        JButton addIncomeButton = new JButton("Add Income");
//        inputPanel.add(addIncomeButton, BorderLayout.SOUTH);
//
//        leftPanel.add(inputPanel);
//
//        rightPanel = new JPanel();
//        rightPanel.setLayout(new BorderLayout());
//        JLabel budgetPlannerLabel = new JLabel("Budget Planner");
//        budgetPlannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        rightPanel.add(budgetPlannerLabel, BorderLayout.CENTER);
//
//        this.add(leftPanel, BorderLayout.WEST);
//        this.add(rightPanel, BorderLayout.CENTER);
//
//        this.setVisible(true);
//    }


