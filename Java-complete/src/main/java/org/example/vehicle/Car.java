package org.example.vehicle;

import org.example.vehicle.base.Driveable;
import org.example.vehicle.base.Vehicle;

public class Car extends Vehicle implements Driveable {
    final private byte numOfSeats;

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
        return "Car{" +
                "numOfSeats=" + numOfSeats +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
