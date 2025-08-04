package org.example.bank;

public class AccountManager {
    private double balance = 0;

    public void deposit(double value) {
        balance += value;
    }

    public void withdraw(double value) {
        balance -= value;
    }

    public double getBalance() {
        return balance;
    }
}
