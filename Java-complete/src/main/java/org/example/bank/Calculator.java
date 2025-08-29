package org.example.bank;

public class Calculator implements Runnable {

    static private String[][] getArray() {
        byte SIZE = 3;
        String[][] calc = new String[SIZE+1][SIZE];
        byte counter = 1;

        for(byte i = 0; i<SIZE; i++) {
            for(byte j = 0; j<SIZE; j++) {
                calc[i][j] = String.valueOf(counter);
                counter++;
            }
        }

        calc[SIZE] = new String[]{"*", "0", "#"};

        return calc;
    }

    static private void printCalc(String [][] calcArray) {
        for(String[] row:calcArray) {
            for(String cell:row) {
                System.out.printf("%s ", cell);
            }
            System.out.println();
        }

    }

     public void run() {
        String[][] result = getArray();
        printCalc(result);
    }
}
