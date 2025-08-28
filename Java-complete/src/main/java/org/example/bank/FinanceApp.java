package org.example.bank;

import org.example.util.ScannerFactory;

import java.util.Scanner;

public class FinanceApp implements Runnable {
    String OPTIONS = """
                ## Select option ##
                
                1. View balance
                2. Deposit money
                3. Withdraw money
                4. Exit app
                """;
    Scanner scanner = ScannerFactory.createScanner();

    public void run() {
        AccountManager account = new AccountManager();
        boolean isFinished = false;

        byte selection;
        do {
            System.out.print(OPTIONS);
            selection = scanner.nextByte();
            double amount;

            if (selection != 0) {
                switch (selection) {
                    case 1 -> System.out.printf("\nBalance: %.2f €\n", account.getBalance());
                    case 2 -> {
                        System.out.println("Enter amount to deposit");
                        amount = scanner.nextLong();
                        account.deposit(amount);
                    }
                    case 3 -> {
                        System.out.println("Enter amount to withdraw");
                        amount = scanner.nextLong();
                        account.withdraw(amount);
                    }
                    case 4 -> {
                        System.out.println("Bye");
                        isFinished = true;
                    }
                    default -> System.out.println("Not a valid selection");
                }
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();

        } while (!isFinished);

        scanner.close();
    }
}
