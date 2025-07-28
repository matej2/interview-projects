package org.example;

import org.example.finance.CompoundInterestCalculator;

import java.util.Scanner;

public class Main {

    public static void pentagonalPrisym() {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double h = scanner.nextDouble();

        double volume = 0.25 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) * Math.pow(a, 2) * h;
        System.out.println(volume);
    }

    public static void main(String[] args) {
        //BudgetTracker budgetTracker = new BudgetTracker();
        //Main.pentagonalPrisym();
        //budgetTracker.run();
        CompoundInterestCalculator.run();
    }
}