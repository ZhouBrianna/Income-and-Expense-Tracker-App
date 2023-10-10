package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FinancialRecordsTest {
    private FinancialRecords testRecords;
    private ExpenseTransaction expenseTransaction1;
    private ExpenseTransaction expenseTransaction2;
    private IncomeTransaction incomeTransaction1;
    private IncomeTransaction incomeTransaction2;


    @BeforeEach
    public void setUp() {
        testRecords = new FinancialRecords();
        expenseTransaction1 = new ExpenseTransaction(100, "2023-01-01", "Shopping");
        expenseTransaction2 = new ExpenseTransaction(7, "2023-01-02", "Starbucks");
        incomeTransaction1 = new IncomeTransaction(120, "2022-12-20", "Tips from work");
        incomeTransaction2 = new IncomeTransaction(200, "2022-12-23", "Lucky Money");
    }

    @Test
    public void testAddSingleIncomeTransaction() {
        testRecords.addIncomeTransaction(incomeTransaction1);
        List<IncomeTransaction> incomeTransactions = testRecords.getIncomeTransactions();
        assertEquals(1, incomeTransactions.size());
        assertEquals(incomeTransaction1, incomeTransactions.get(0));
    }

    @Test
    public void testAddMultipleIncomeTransaction() {
        testRecords.addIncomeTransaction(incomeTransaction1);
        testRecords.addIncomeTransaction(incomeTransaction2);
        List<IncomeTransaction> incomeTransactions = testRecords.getIncomeTransactions();
        assertEquals(2, incomeTransactions.size());
        assertEquals(incomeTransaction1, incomeTransactions.get(0));
        assertEquals(incomeTransaction2, incomeTransactions.get(1));
    }

    @Test
    public void testAddSingleExpenseTransaction() {
        testRecords.addExpenseTransaction(expenseTransaction1);
        List<ExpenseTransaction> expenseTransactions = testRecords.getExpenseTransactions();
        assertEquals(1, expenseTransactions.size());
        assertEquals(expenseTransaction1, expenseTransactions.get(0));
    }

    @Test
    public void testAddMultipleExpenseTransaction() {
        testRecords.addExpenseTransaction(expenseTransaction1);
        testRecords.addExpenseTransaction(expenseTransaction2);
        List<ExpenseTransaction> expenseTransactions = testRecords.getExpenseTransactions();
        assertEquals(2, expenseTransactions.size());
        assertEquals(expenseTransaction1, expenseTransactions.get(0));
        assertEquals(expenseTransaction2, expenseTransactions.get(1));
    }

    @Test
    public void testDeleteSingleExpenseTransaction() {
        testRecords.addExpenseTransaction(expenseTransaction1);
        testRecords.addExpenseTransaction(expenseTransaction2);
        List<ExpenseTransaction> expenseTransactions = testRecords.getExpenseTransactions();
        testRecords.deleteExpenseTransaction(expenseTransaction1);
        assertEquals(1, expenseTransactions.size());
        assertTrue(expenseTransactions.contains(expenseTransaction2));
        assertFalse(expenseTransactions.contains(expenseTransaction1));

    }

    @Test
    public void testDeleteMultipleExpenseTransaction() {
        testRecords.addExpenseTransaction(expenseTransaction1);
        testRecords.addExpenseTransaction(expenseTransaction2);
        List<ExpenseTransaction> expenseTransactions = testRecords.getExpenseTransactions();
        testRecords.deleteExpenseTransaction(expenseTransaction1);
        testRecords.deleteExpenseTransaction(expenseTransaction2);
        assertEquals(0, expenseTransactions.size());

    }



    @Test
    public void testDeleteSingleIncomeTransaction() {
        testRecords.addIncomeTransaction(incomeTransaction1);
        testRecords.addIncomeTransaction(incomeTransaction2);
        List<IncomeTransaction> incomeTransactions = testRecords.getIncomeTransactions();
        testRecords.deleteIncomeTransaction(incomeTransaction1);
        assertEquals(1, incomeTransactions.size());
        assertTrue(incomeTransactions.contains(incomeTransaction2));
        assertFalse(incomeTransactions.contains(incomeTransaction1));

    }

    @Test
    public void testDeleteMultipleIncomeTransaction() {
        testRecords.addIncomeTransaction(incomeTransaction1);
        testRecords.addIncomeTransaction(incomeTransaction2);
        List<IncomeTransaction> incomeTransactions = testRecords.getIncomeTransactions();
        testRecords.deleteIncomeTransaction(incomeTransaction1);
        testRecords.deleteIncomeTransaction(incomeTransaction2);
        assertEquals(0, incomeTransactions.size());

    }

    @Test
    public void testCalculatedTotalExpense() {
        testRecords.addExpenseTransaction(expenseTransaction1);
        testRecords.addExpenseTransaction(expenseTransaction2);
        List<ExpenseTransaction> expenseTransactions = testRecords.getExpenseTransactions();
        assertEquals(107, testRecords.calculatedTotalExpense());

    }


    @Test
    public void testCalculatedTotalIncome() {
        testRecords.addIncomeTransaction(incomeTransaction1);
        testRecords.addIncomeTransaction(incomeTransaction2);
        List<IncomeTransaction> incomeTransactions = testRecords.getIncomeTransactions();
        assertEquals(320, testRecords.calculatedTotalIncome());

    }

    @Test
    public void testCalculateNetIncome() {
        testRecords.addExpenseTransaction(expenseTransaction1);
        testRecords.addExpenseTransaction(expenseTransaction2);
        List<ExpenseTransaction> expenseTransactions = testRecords.getExpenseTransactions();
        testRecords.addIncomeTransaction(incomeTransaction1);
        testRecords.addIncomeTransaction(incomeTransaction2);
        List<IncomeTransaction> incomeTransactions = testRecords.getIncomeTransactions();
        assertEquals(320-107, testRecords.calculateNetIncome());


    }



}
