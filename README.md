# Income and Expense Tracker Application

## Project Proposal
*The Income and Expense Tracker Application* will help users manage their personal finances by providing features such as, **Income Tracking**, **Expense Tracking**, and **Budget Management**.The application can cater to a broad user base, including: students, individuals and families. I find this project interesting because it can help individuals gain better control over their finances and improve their financial well-being.

## User Stories:

- As a user, I want to be able to add an income that has amount, date and description to the financial records
- As a user, I want to be able to add an expense that has amount, date and description to the financial records
- As a user, I want to be able to view a list of income transactions and a list of expense transactions
- As a user, I want to be able to calculate the total income based on the income transactions on the financial records
- As a user, I want to be able to calculate the total expense based on the expense transactions on the financial records
- As a user, I want to be able to calculate a net income based on the financial records
- As a user, I want to be able to save my financial records (list of income transactions and expense transaction) to file
- As a user, I want to be able to load my financial records (list of income transactions and expense transaction) from file

## Instructions for Grader (Phase 3)
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking buttons labelled "Add Income" or "Add Expense" to add an income or an expense to the list of financial records.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking the button labelled "Calculate" to calculate the total income, total expense, and net income that are being added the list of transactions
- You can view transactions by clicking the button labelled "View Transaction".
- You can locate my visual component on the window when the application starts.
- You can save the state of my application by clicking the button labelled "Save".
- You can reload the state of my application by clicking the button labelled "Load".

## Phase 4: Task 2

- Wed Nov 29 12:13:08 PST 2023
- Added an income transaction: Date: 2023/11/28, Description: gift cards, Amount: $200.00
- Wed Nov 29 12:13:37 PST 2023
- Added an expense transaction: Date: 2023/11/29, Description: lunch, Amount: $10.00
- Wed Nov 29 12:13:39 PST 2023
- Viewed list of income transactions
- Wed Nov 29 12:13:39 PST 2023
- Viewed list of expense transactions
- Wed Nov 29 12:13:41 PST 2023
- Calculated Total Income: $200.0
- Wed Nov 29 12:13:41 PST 2023
- Calculated Total Expense: $10.0
- Wed Nov 29 12:13:41 PST 2023
- Calculated Net Income: $190.0
- Wed Nov 29 12:13:42 PST 2023
- Application Saved

## Phase 4: Task 3
If I have more time to work on this project, I will reduce the duplication between `IncomeTransaction` and `ExpenseTransaction`. 
These two classes have shared behaviours, so the refactoring involves creating an abstract class `Transaction`. 
This abstract class will encapsulates the shared behavior between the `IncomeTransaction` and `ExpenseTransaction`. 
This refactoring address the redundancy in the code by pulling out common fields and methods in `IncomeTransaction` and `ExpenseTransaction` classes.
Both `IncomeTransaction` and `ExpenseTransaction` will then extend `Transaction`, inheriting the common behaviors 
while allowing each class to maintain its specific methods or behavior. 