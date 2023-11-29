package persistence;

import model.*;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads financial records from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads financial records from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FinancialRecords read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Application Reloaded"));
        return parseFinancialRecords(jsonObject);

    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }


        return contentBuilder.toString();
    }

    // EFFECTS: parses FinancialRecords from JSON object and returns it
    private FinancialRecords parseFinancialRecords(JSONObject jsonObject) {
        FinancialRecords financialRecords = new FinancialRecords();
        addIncomeTransactions(financialRecords, jsonObject);
        addExpenseTransactions(financialRecords, jsonObject);
        return financialRecords;
    }

    // MODIFIES: financialRecords
    // EFFECTS: parses income Transaction from JSON object and adds them to FinancialRecords
    private void addIncomeTransactions(FinancialRecords fr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Income Transactions");
        for (Object json : jsonArray) {
            JSONObject nextTransaction = (JSONObject) json;
            addIncomeTransaction(fr, nextTransaction);
        }
    }

    // MODIFIES: financialRecords
    // EFFECTS: parses income Transaction from JSON object and adds it to FinancialRecords
    private void addIncomeTransaction(FinancialRecords fr, JSONObject jsonObject) {
        double amount = jsonObject.getDouble("amount");
        String date = jsonObject.getString("date");
        String description = jsonObject.getString("description");
        IncomeTransaction incomeTransaction = new IncomeTransaction(amount, date, description);
        fr.addIncomeTransaction(incomeTransaction);
    }

    // MODIFIES: financialRecords
    // EFFECTS: parses Expense Transaction from JSON object and adds them to FinancialRecords
    private void addExpenseTransactions(FinancialRecords fr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Expense Transactions");
        for (Object json : jsonArray) {
            JSONObject nextTransaction = (JSONObject) json;
            addExpenseTransaction(fr, nextTransaction);
        }
    }

    // MODIFIES: financialRecords
    // EFFECTS: parses Expense Transactions from JSON object and adds it to FinancialRecords
    private void addExpenseTransaction(FinancialRecords fr, JSONObject jsonObject) {
        double amount = jsonObject.getDouble("amount");
        String date = jsonObject.getString("date");
        String description = jsonObject.getString("description");
        ExpenseTransaction expenseTransaction = new ExpenseTransaction(amount, date, description);
        fr.addExpenseTransaction(expenseTransaction);
    }
}
