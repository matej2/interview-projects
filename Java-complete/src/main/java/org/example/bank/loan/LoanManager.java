package org.example.bank.loan;

public class LoanManager {
    private final Double principal;
    private final Integer monthsPeriod;
    private enum interestType {
        DYNAMIC,
        FIXED
    }
    private final Float interestRate;

    // Additional costs
    private final Double initialCost;
    private final Double monthlyCostForAccountManagement;
    private final Double monthlyCostForLoanManagement;

    public LoanManager(double principal, int monthsPeriod, float interestRate, double initialCost, double monthlyCostForAccountManagement, double monthlyCostForLoanManagement) {
        this.principal = principal;
        this.monthsPeriod = monthsPeriod;
        this.interestRate = interestRate;
        this.initialCost = initialCost;
        this.monthlyCostForAccountManagement = monthlyCostForAccountManagement;
        this.monthlyCostForLoanManagement = monthlyCostForLoanManagement;
    }

    public Double getMonthlyPayment() {
        Float monthlyInterestRate = interestRate / 12;
        return (principal*monthlyInterestRate*Math.pow(1+monthlyInterestRate, monthsPeriod))/(Math.pow(1+monthlyInterestRate, monthsPeriod)-1);
    }
}
