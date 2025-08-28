package org.example.vehicle;

import org.example.util.ScannerFactory;

import java.util.Scanner;

public class VehicleRentalService implements Runnable{
    private final Scanner scanner = ScannerFactory.createScanner();


    public void run() {
        Car honda = new Car("Toyota", "Corolla", (short)2022, (byte)4);
        Motorcycle yamaha = new Motorcycle("Yamaha", "MT-07", (short)2021, (short) 184);
        Van ford = new Van("Ford", "Transit", (short) 2020, (short)2447);

        honda.start();
        yamaha.start();
        ford.start();

        System.out.println(honda);
        System.out.println(yamaha);
        System.out.println(ford);

        honda.stop();
        yamaha.stop();
        ford.stop();
    }
}
