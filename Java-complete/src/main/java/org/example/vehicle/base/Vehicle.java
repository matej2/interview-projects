package org.example.vehicle.base;

public abstract class Vehicle {
    protected String make;
    protected String model;
    protected short year;

    protected Vehicle(String make, String model, short year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
}
