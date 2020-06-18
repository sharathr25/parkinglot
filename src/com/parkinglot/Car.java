package com.parkinglot;

public class Car implements Vehicle{
    String registrationNumber;
    String color;

    Car(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    @Override
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    @Override
    public String getColor() {
        return color;
    }
}
