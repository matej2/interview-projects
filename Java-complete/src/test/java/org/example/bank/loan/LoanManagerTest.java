package org.example.bank.loan;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoanManagerTest {

    @Test
    public void getMonthlyPayment() {
        LoanManager loanManager = new LoanManager(
                100000,
                240,
                0.028f,
                500,
                4.8,
                3.9
        );

        assertEquals(544.6478844530583, loanManager.getMonthlyPayment());
    }
}