package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpenseTransactionTest {
    private ExpenseTransaction testExpense;

    @BeforeEach
    public void setUp() {
        testExpense = new ExpenseTransaction(15.8, "2023-1-20", "Shopping");
    }

    @Test
    public void testConstructor() {
        assertEquals(15.8, testExpense.getExpenseAmount());
        assertEquals("2023-1-20", testExpense.getDate());
        assertEquals("Shopping", testExpense.getDescription());
    }

    @Test
    public void testRemoveDescription() {
        testExpense.removeDescription();
        assertNull(testExpense.getDescription());
    }

    @Test
    public void testMoreExpense() {
        assertEquals(22.8, testExpense.moreExpense(7));
    }
}
