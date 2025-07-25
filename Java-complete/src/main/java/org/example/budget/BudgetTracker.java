package org.example.budget;

import java.util.Scanner;

public class BudgetTracker {
    final private Scanner scanner = new Scanner(System.in);

    private void enterValueMsg(String name) {
        System.out.println("Enter the value for: " + name);
    }

    public void run() {
        String name;
        do {
            this.enterValueMsg("name");

            name = this.scanner.nextLine();

            this.getInfo(name);
        } while(!name.equals("q"));
    }

    private void visualizeBudget(double totalExpense, double income) {
        final String AVAILABLE_BUDGET = "_";
        final String TRANSPORTATION_BUDGET = "#";
        final byte LENGTH = 20;

        if (income > totalExpense) {
            double percentageExpense = (totalExpense / income);
            double percentageIncome = 1 - percentageExpense;

            long visualizationPartitionExpense = Math.round(LENGTH * percentageExpense);
            long visualizationPartitionIncome = Math.round(LENGTH * percentageIncome);

            System.out.print("[");

            for (byte i = 0; i < visualizationPartitionExpense; i++) {
                System.out.print(TRANSPORTATION_BUDGET);
            }

            for (byte i = 0; i < visualizationPartitionIncome; i++) {
                System.out.print(AVAILABLE_BUDGET);
            }
            System.out.print("]");
            System.out.println();

        }
    }

    private void getInfo(String name) {
        Scanner scanner = new Scanner(System.in);

        this.enterValueMsg("number of days");
        byte numOfDays = scanner.nextByte();


        this.enterValueMsg("income");
        long income = scanner.nextLong();

        this.enterValueMsg("food expenses");
        float expensesFood = scanner.nextLong();

        this.enterValueMsg("transportation expenses");
        float expensesTransportation = scanner.nextFloat();

        this.enterValueMsg("subscription expenses");
        float expensesSubscriptions = scanner.nextFloat();

        float totalExpenses = expensesFood + expensesTransportation + expensesSubscriptions;
        float remainingBudget = income - totalExpenses;

        String remainingExpenseMsg = "You have enough remaining budget";
        if (totalExpenses > income)
            remainingExpenseMsg = "You dont have enough remaining budget";

        String nextMonthBudgetMsg = "You have enough budget for next month";
        if (remainingBudget > income)
            nextMonthBudgetMsg = "You dont have enough remaining budget for next month";

        // Calculate relative daily expenses
        float relativeDailyExpensesFood = expensesFood / numOfDays;
        float relativeDailyExpensesTransportation = expensesTransportation / numOfDays;
        float relativeDailyExpensesSubscriptions = expensesSubscriptions / numOfDays;

        String output = String.format("""
                        Budget status for %s:
                        
                        Number of days: %d
                        Income: %d
                        
                        Expenses:
                          - food: %f
                          - transportation: %f
                          - subscriptions: %f
                        
                        Remaining monthly budget: %f
                        
                        Relative daily expenses:
                          - food: %f
                          - transportation: %f
                          - subscriptions: %f
                        
                        %s
                        %s
                        """,
                name,
                numOfDays,
                income,
                expensesFood,
                expensesTransportation,
                expensesSubscriptions,
                remainingBudget,
                relativeDailyExpensesFood,
                relativeDailyExpensesTransportation,
                relativeDailyExpensesSubscriptions,
                remainingExpenseMsg,
                nextMonthBudgetMsg);

        System.out.println(output);

        visualizeBudget(totalExpenses, income);

    }
}
