package com.parkinglot;

public class Slot {
    boolean isAvailable;
    Vehicle vehicle;
    int slotNumber;

    Slot() {
        isAvailable = true;
        vehicle = null;
        slotNumber = -1;
    }

    Vehicle getVehicle() {
        return vehicle;
    }

    boolean getAvailability() {
        return isAvailable;
    }

    void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public int getSlotNumber() {
        return this.slotNumber;
    }
}
