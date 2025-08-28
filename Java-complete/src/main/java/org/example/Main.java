package org.example;

import org.example.bank.Calculator;
import org.example.bank.FinanceApp;
import org.example.budget.BudgetTracker;
import org.example.finance.CompoundInterestCalculator;
import org.example.libary.Libary;
import org.example.libary.LibaryManagement;
import org.example.vehicle.VehicleRentalService;

import java.util.Scanner;

public class Main {

    public static void pentagonalPrisym(Scanner scanner) {
        System.out.println("Enter a and h for pentagonal prisym:");
        double a = scanner.nextDouble();
        double h = scanner.nextDouble();

        double volume = 0.25 * Math.sqrt(5 * (5 + 2 * Math.sqrt(5))) * Math.pow(a, 2) * h;
        System.out.println(volume);

        scanner.close();
    }

    public static void main(String[] args) {
        BudgetTracker budgetTracker = new BudgetTracker();
        FinanceApp financeApp = new FinanceApp();
        Scanner SCANNER = new Scanner(System.in);
        LibaryManagement libary = new LibaryManagement();

        byte option;



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

            option = SCANNER.nextByte();
            switch(option) {
                case 1: Main.pentagonalPrisym(SCANNER);
                    break;
                case 2: budgetTracker.run(SCANNER);
                    break;
                case 3: CompoundInterestCalculator.run(SCANNER);
                    break;
                case 4: financeApp.run(SCANNER);
                    break;
                case 5: Calculator.run();
                    break;
                case 6: {
                    Libary.run();
                }
                case 7: {
                    VehicleRentalService.run();
                }
                case 0: return;
                default:
                    System.out.println("Incorrect option");
            }
        } while (true);
    }
}