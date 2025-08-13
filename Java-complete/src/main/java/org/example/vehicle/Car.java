package org.example.vehicle;

public class Car extends Vehicle implements Driveable {
    private byte numOfSeats;

    public Car(String make, String model, short year, byte numOfSeats) {
        super(make, model, year);
        this.numOfSeats = numOfSeats;
    }

    @Override
    public void start() {
        System.out.println("Car started");
    }

    @Override
    public void stop() {
        System.out.println("Car stopped");
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", make, model, year);
    }
}
