package com.parkinglot;

public class Slot {
    boolean isAvailable;
    Vehicle vehicle;
    int slotNumber;
    VehicleType vehicleType;

    Slot(VehicleType vehicleType) {
        this.isAvailable = true;
        this.vehicle = null;
        this.slotNumber = -1;
        this.vehicleType = vehicleType;
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

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
