package org.example;

import org.example.bank.Calculator;
import org.example.bank.FinanceApp;
import org.example.budget.BudgetTracker;
import org.example.finance.CompoundInterestCalculator;
import org.example.libary.Libary;
import org.example.util.ScannerFactory;
import org.example.vehicle.VehicleRentalService;

import java.util.Scanner;

public class Main {

    public static void pentagonalPrisym() {
        Scanner scanner = ScannerFactory.createScanner();
        System.out.println("Enter a and h for pentagonal prisym:");
        double a = scanner.nextDouble();
        double h = scanner.nextDouble();

        double volume = 0.25 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) * Math.pow(a, 2) * h;
        System.out.println(volume);

        scanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = ScannerFactory.createScanner();

        byte option;
        Runnable selected = null;

        do {
            System.out.println("""
                ************
                1. PentagonalPrisym calculation
                2. Budget tracker
                3. Compound interest calculator
                4. Finance app
                5. Calculator
                6. Libary
                7. Car rental service
                0. Exit
                ************
                """);
            System.out.println("Select an option");

            option = scanner.nextByte();
            switch(option) {
                case 1: Main.pentagonalPrisym();
                    break;
                case 2: selected = new BudgetTracker();
                    break;
                case 3: selected = new CompoundInterestCalculator();
                    break;
                case 4: selected = new FinanceApp();
                    break;
                case 5: selected = new Calculator();
                    break;
                case 6:
                    selected = new Libary();
                break;
                case 7: selected = new VehicleRentalService();
                    break;
                case 0: {
                    scanner.close();
                    return;
                }
                default:
                    System.out.println("Incorrect option");
            }
            if(selected != null) {
                selected.run();
            }
            selected = null;
        } while (true);
    }
}