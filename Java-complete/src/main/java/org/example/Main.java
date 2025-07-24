package org.example;


import java.util.Scanner;

public class Main {

    public static void userInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Budget list name");
        String name = scanner.nextLine();

        System.out.println("Enter number of days in month ");
        byte numOfDays = scanner.nextByte();

        System.out.println("Enter monthly income for " + name);
        long income = scanner.nextLong();

        System.out.println("Enter monthly expenses for food");
        float expensesFood = scanner.nextLong();

        System.out.println("Enter monthly expenses for transportation");
        float expensesTransportation = scanner.nextFloat();

        System.out.println("Enter monthly expenses for subscriptions");
        float expensesSubscriptions = scanner.nextFloat();

        float remainingBudget = income - (expensesFood + expensesTransportation + expensesSubscriptions);

        // Calculate relative daily expenses
        float relativeDailyExpensesFood = expensesFood / numOfDays;
        float relativeDailyExpensesTransportation = expensesTransportation / numOfDays;
        float relativeDailyExpensesSubscriptions = expensesSubscriptions / numOfDays;

        System.out.printf("""
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
                relativeDailyExpensesSubscriptions);
        scanner.close();
    }

    public static void triangle() {
        double x;
        double y;
        double z;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the value of x: ");
        x = scanner.nextDouble();
        System.out.println("Enter the value of y: ");
        y = scanner.nextDouble();

        z = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

        System.out.println("The value of z is: " + z);
        scanner.close();
    }

    public static void main(String[] args) {
        Main.triangle();
    }
}