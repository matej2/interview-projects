package org.example.bank;

public class AccountManager {
    private long balance = 0;

    public void deposit(long value) {
        balance += value;
    }

    public void withdraw(long value) {
        balance -= value;
    }

    public  long getBalance() {
        return balance;
    }
}
