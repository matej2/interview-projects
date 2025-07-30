package org.example.bank;

import java.util.Scanner;

public class FinanceApp {
    Scanner SCANNER = new Scanner(System.in);
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

        byte selection;
        do {
            System.out.print(OPTIONS);
            selection = SCANNER.nextByte();

            if (selection != 0) {
                switch (selection) {
                    case 1 -> System.out.println("Your balance: "+account.getBalance());
                    case 2 -> {
                        System.out.println("Enter amount to deposit");
                        long amount = SCANNER.nextLong();
                        account.deposit(amount);
                    }
                    case 3 -> {
                        System.out.println("Enter amount to withdraw");
                        long amount = SCANNER.nextLong();
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

        SCANNER.close();
    }
}
