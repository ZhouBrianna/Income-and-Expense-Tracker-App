package persistence;
import model.ExpenseTransaction;
import model.FinancialRecords;

import model.IncomeTransaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            FinancialRecords financialRecords = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFinancialRecords() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFinancialRecords.json");
        try {
            FinancialRecords financialRecords = reader.read();
            List<IncomeTransaction> incomeTransactions = financialRecords.getIncomeTransactions();
            List<ExpenseTransaction> expenseTransactions = financialRecords.getExpenseTransactions();

            assertEquals(0, incomeTransactions.size());
            assertEquals(0, expenseTransactions.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFinancialRecords() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFinancialRecords.json");
        try {
            FinancialRecords financialRecords = reader.read();
            List<ExpenseTransaction> expenseTransactions = financialRecords.getExpenseTransactions();
            List<IncomeTransaction> incomeTransactions = financialRecords.getIncomeTransactions();
            assertEquals(1, expenseTransactions.size());
            assertEquals(1, incomeTransactions.size());
            checkExpenseTransaction(10, "2023-10-10","shopping", expenseTransactions.get(0));
            checkIncomeTransaction(100, "2023-10-11","GIFT", incomeTransactions.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }




}
