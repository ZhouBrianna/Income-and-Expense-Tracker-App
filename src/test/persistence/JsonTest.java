package persistence;

import model.IncomeTransaction;
import model.ExpenseTransaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class JsonTest {
    protected void checkIncomeTransaction(double amount, String date, String description, IncomeTransaction it) {
        assertEquals(amount, it.getIncomeAmount());
        assertEquals(date, it.getDate());
        assertEquals(description,it.getDescription());
    }

    protected void checkExpenseTransaction(double amount, String date, String description, ExpenseTransaction et) {
        assertEquals(amount, et.getExpenseAmount());
        assertEquals(date, et.getDate());
        assertEquals(description,et.getDescription());
    }
}
