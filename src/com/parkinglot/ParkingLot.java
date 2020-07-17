package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    int size;
    Slot[] slots;

    ParkingLot(int size) {
        this.size = size;
        slots = new Slot[size];
        if(size != 0) {
            int slotsToBeDevided = slots.length / VehicleType.values().length;
            int part = 1;
            int j = 0;
            for (int i = 0; i < slots.length ; i++) {
                slots[i] = j < VehicleType.values().length ? new Slot(VehicleType.values()[j]) : new Slot(VehicleType.ANY);
                if(i == part*slotsToBeDevided) {
                    part++;
                    j++;
                }
            }
        }
        System.out.println("Created a parking lot with " + size + " slots");
    }

    public int getSize() {
        return size;
    }

    public Slot[] getSlots() {
        return slots;
    }

    public int allocateSlot(Vehicle vehicle) {
        for (int i = 0; i < slots.length; i++) {
            if(slots[i].getAvailability() && vehicle.getVehicleType() == slots[i].getVehicleType()) {
                slots[i].setAvailability(false);
                slots[i].setVehicle(vehicle);
                slots[i].setSlotNumber(i+1);
                return slots[i].getSlotNumber();
            }
        }
        return -1;
    }

    public int leaveSlot(int slotNumber) {
        for (Slot slot:slots) {
            if(slot.getSlotNumber() == slotNumber) {
                slot.setAvailability(true);
                slot.setVehicle(null);
                return slotNumber;
            }
        }
        return -1;
    }

    public void printStatus() {
        System.out.println("Slot No.\tRegistration No\tColor");
        for (int i = 0; i < slots.length; i++) {
            if(!slots[i].getAvailability()) {
                Vehicle vehicle = slots[i].getVehicle();
                System.out.println(i + 1 + "\t" + vehicle.getRegistrationNumber() + "\t" + vehicle.getColor());
            }
        }
    }

    public List<String> getRegistrationNumberOfCarsUsingColor(String colorToSearch) {
        List<String> registrationNumbers = new ArrayList<>();
        for (Slot slot: slots) {
            Vehicle vehicle = slot.getVehicle();
            if(vehicle != null) {
                String colorOfVehicle = vehicle.getColor();
                String registrationNoOfvehicle = vehicle.getRegistrationNumber();
                if(colorOfVehicle.equals(colorToSearch)) {
                    registrationNumbers.add(registrationNoOfvehicle);
                }
            }
        }
        return registrationNumbers;
    }

    public List<Integer> getSlotNumbersOfCarsUsingColor(String colorToSearch) {
        List<Integer> slotNumbers = new ArrayList<>();
        for (Slot slot: slots) {
            Vehicle vehicle = slot.getVehicle();
            if(vehicle != null) {
                String colorOfVehicle = vehicle.getColor();
                if(colorOfVehicle.equals(colorToSearch)) {
                    slotNumbers.add(slot.getSlotNumber());
                }
            }
        }
        return slotNumbers;
    }

    public int getSlotNumberOfCarUsingRegistrationNo(String registrationNumberToSearch) {
        for (Slot slot: slots) {
            Vehicle vehicle = slot.getVehicle();
            if(vehicle != null) {
                if(vehicle.getRegistrationNumber().equals(registrationNumberToSearch)) {
                    return slot.getSlotNumber();
                }
            }
        }
        return -1;
    }
}
