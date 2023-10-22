package persistence;

import model.ExpenseTransaction;
import model.FinancialRecords;
import model.IncomeTransaction;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            FinancialRecords financialRecords = new FinancialRecords();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFinancialRecords() {
        try {
            FinancialRecords financialRecords = new FinancialRecords();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFinancialRecords.json");
            writer.open();
            writer.write(financialRecords);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFinancialRecords.json");
            financialRecords = reader.read();
            assertEquals(0, financialRecords.getIncomeTransactions().size());
            assertEquals(0, financialRecords.getExpenseTransactions().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFinancialRecords() {
        try {
            FinancialRecords financialRecords = new FinancialRecords();
            ExpenseTransaction ex = new ExpenseTransaction(10, "2023-10-10", "shopping");
            financialRecords.addExpenseTransaction(ex);
            IncomeTransaction in = new IncomeTransaction(100, "2023-10-11", "GIFT");
            financialRecords.addIncomeTransaction(in);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFinancialRecords.json");
            writer.open();
            writer.write(financialRecords);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFinancialRecords.json");
            financialRecords = reader.read();
            List<ExpenseTransaction> expenseTransactions = financialRecords.getExpenseTransactions();
            List<IncomeTransaction> incomeTransactions = financialRecords.getIncomeTransactions();
            assertEquals(1, expenseTransactions.size());
            assertEquals(1, incomeTransactions.size());
            checkExpenseTransaction(10, "2023-10-10","shopping", expenseTransactions.get(0));
            checkIncomeTransaction(100, "2023-10-11","GIFT", incomeTransactions.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
