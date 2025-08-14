package org.example.vehicle;

import org.example.vehicle.base.Driveable;
import org.example.vehicle.base.Vehicle;

public class Van extends Vehicle implements Driveable {
    final private short payloadCapacity;

    public Van(String make, String model, short year, short payloadCapacity) {
        super(make, model, year);
        this.payloadCapacity = payloadCapacity;
    }

    @Override
    public void start() {
        System.out.println("Van started");
    }

    @Override
    public void stop() {
        System.out.println("Van started");
    }

    @Override
    public String toString() {
        return "Van{" +
                "year=" + year +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", payloadCapacity=" + payloadCapacity +
                '}';
    }
}
