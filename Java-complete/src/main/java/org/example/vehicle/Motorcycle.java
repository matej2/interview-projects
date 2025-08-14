package org.example.vehicle;

import org.example.vehicle.base.Driveable;
import org.example.vehicle.base.Vehicle;

public class Motorcycle extends Vehicle implements Driveable {
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

    @Override
    public String toString() {
        return "Motorcycle{" +
                "weightKg=" + weightKg +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
