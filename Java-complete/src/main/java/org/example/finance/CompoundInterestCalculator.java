package org.example.finance;

import org.example.util.InputUtils;
import org.example.util.ScannerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class CompoundInterestCalculator implements Runnable {
    private final Scanner scanner = ScannerFactory.createScanner();

    public void run() {

        InputUtils.enterValueMsg("principal");
        double principal = scanner.nextLong();

        InputUtils.enterValueMsg("interest rate (0-1)");
        float interestRate = scanner.nextFloat();

        InputUtils.enterValueMsg("compound frequency (1: annually, 12: monthly, 52: weekly, 365: daily)");
        int compoundFrequency = scanner.nextInt();

        InputUtils.enterValueMsg("length in years");
        byte lengthYears = scanner.nextByte();

        System.out.println("Total P+I: "+CompoundInterestCalculator.calculateCompoundInterest(principal, interestRate, compoundFrequency, lengthYears));
    }

    private static double calculateCompoundInterest(double principal, float interestRate, int compoundFrequency, byte lengthYears ) {
        double result = principal * Math.pow( (1 + interestRate/compoundFrequency ), lengthYears*compoundFrequency);
        BigDecimal bd = new BigDecimal(Double.toString(result));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
