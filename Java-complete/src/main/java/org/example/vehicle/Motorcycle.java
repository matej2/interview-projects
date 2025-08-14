package org.example.vehicle;

public class Motorcycle extends Vehicle implements Driveable{
    short weightKg;

    public Motorcycle(String make, String model, short year, short weightKg) {
        super(make, model, year);
        this.weightKg = weightKg;
    }

    @Override
    public void start() {
        System.out.println("Motorcycle started");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle stopped");
    }
}
