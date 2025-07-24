package org.example;


import java.util.Scanner;

public class Main {

    public static void userInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Budget list name");
        String name = scanner.nextLine();

        System.out.println("Enter monthly income for " + name);
        long income = scanner.nextLong();

        System.out.println("Enter monthly expenses for food");
        long expensesFood = scanner.nextLong();

        System.out.println("Enter monthly expenses for transportation");
        long expensesTransportation = scanner.nextLong();

        System.out.println("Enter monthly expenses for subscriptions");
        long expensesSubscriptions = scanner.nextLong();

        long remainingBudget = income - (expensesFood + expensesTransportation + expensesSubscriptions);

        System.out.print(String.format("""
                Budget status for %s:
                Income: %d
                
                Expenses:
                  - food: %d
                  - transportation: %d
                  - subscriptions: %d
                
                Remaining monthly budget: %d
                """,
                name,
                income,
                expensesFood,
                expensesTransportation,
                expensesSubscriptions,
                remainingBudget));
    }

    public static void main(String[] args) {
        String name = "John";

        System.out.println("Hello " + name);

        String x = "water";
        String y = "cola";
        String temp;

        temp = y;
        y = x;
        y = temp;

        System.out.println(x);
        System.out.println(y);

        Main.userInput();
    }
}