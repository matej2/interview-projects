package org.example;


import java.util.Scanner;

public class Main {
    // TODO: Replace with log4j calls
    private static void l(double str) {
        Main.l(String.valueOf(str));
    }


    private static void l(String str) {
        System.out.println(str);
    }

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

    private static double readVar(Scanner scanner, String varName){
        System.out.println("Enter the value of " + varName);
        return scanner.nextDouble();
    }



    public static void pentagonalPrisym() {
        Scanner scanner = new Scanner(System.in);
        double a = Main.readVar(scanner, "x");
        double h = Main.readVar(scanner, "h");

        double volume = 0.25 * Math.sqrt(5*(5+2*Math.sqrt(5)))*Math.pow(a, 2)*h;
        Main.l(volume);
    }

    public static void main(String[] args) {
        Main.pentagonalPrisym();
    }
}