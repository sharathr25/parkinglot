package com.parkinglot;

public class Car implements Vehicle{
    String registrationNumber;
    String color;
    VehicleType vehicleType;

    Car(String registrationNumber, String color, VehicleType vehicleType) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.vehicleType = vehicleType;
    }

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
