package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IncomeTransactionTest {
    private IncomeTransaction testIncome;

    @BeforeEach
    public void setUp() {
        testIncome = new IncomeTransaction(120.10, "2023-01-01", "Salary");
    }

    @Test
    public void testConstructor() {
        assertEquals(120.10, testIncome.getIncomeAmount());
        assertEquals("2023-01-01", testIncome.getDate());
        assertEquals("Salary", testIncome.getDescription());
    }

    @Test
    public void testRemoveDescription() {
        testIncome.removeDescription();
        assertNull(testIncome.getDescription());
    }

    @Test
    public void testMoreIncome() {
        assertEquals(121.60, testIncome.moreIncome(1.5));
    }
}
