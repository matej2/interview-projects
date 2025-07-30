package org.example.bank;

import java.util.Scanner;

public class FinanceApp {
    Scanner scanner = new Scanner(System.in);
    String OPTIONS = """
                ## Select option ##
                
                1. View balance
                2. Deposit money
                3. Withdraw money
                4. Exit app
                """;

    public void run() {
        AccountManager account = new AccountManager();
        boolean isFinished = false;

        byte selection = 0;
        do {
            System.out.print(OPTIONS);
            selection = scanner.nextByte();

            if (selection != 0) {
                switch (selection) {
                    case 1 -> System.out.println("Your balance: "+account.getBalance());
                    case 2 -> {
                        System.out.println("Enter amount to deposit");
                        long amount = scanner.nextLong();
                        account.deposit(amount);
                    }
                    case 3 -> {
                        System.out.println("Enter amount to withdraw");
                        long amount = scanner.nextLong();
                        account.withdraw(amount);
                    }
                    case 4 -> isFinished = true;
                    default -> System.out.println("Not a valid selection");
                }
            }

        } while (!isFinished);
    }
}
