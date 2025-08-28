package org.example.util;

import java.util.Scanner;

public class ScannerFactory {
    public static Scanner createScanner() {
        return new Scanner(System.in);
    }
}
