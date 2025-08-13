package org.example.vehicle;

public class Vehicle {
    protected boolean isRunning;
    protected String make;
    protected String model;
    protected short year;

    Vehicle(String make, String model, short year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }
}
