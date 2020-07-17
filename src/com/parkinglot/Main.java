package com.parkinglot;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String ...args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = null;

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            switch (command) {
                case "create_parking_lot": {
                    if(parkingLot == null) {
                        try {
                            int sizeOfParkingLot = Integer.parseInt(input.split(" ")[1]);
                            parkingLot = new ParkingLot(sizeOfParkingLot);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid value for command");
                        }
                    } else {
                        System.out.println("Parking lot already created");
                    }
                    break;
                }
                case "park": {
                    String registrationNumberOfCar = input.split(" ")[1];
                    String colorOfCar = input.split(" ")[2];
                    VehicleType vehicleType;
                    vehicleType = VehicleType.valueOfCommandStr( input.split( " ")[3]);
                    Car car = new Car(registrationNumberOfCar, colorOfCar, vehicleType != null ? vehicleType : VehicleType.ANY);
                    int slotNumber = parkingLot.allocateSlot(car);
                    System.out.println(slotNumber != -1 ? "Allocated slot number: " + slotNumber : "Sorry, parking lot is full");
                    break;
                }
                case "leave": {
                    try {
                        int slotNumber = Integer.parseInt(input.split(" ")[1]);
                        int availableSlotNumber = parkingLot.leaveSlot(slotNumber);
                        System.out.println(availableSlotNumber != -1 ? "Slot number "+ availableSlotNumber + " is free" : "Slot number not found");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid value for command");
                    }
                    break;
                }
                case "status": {
                    parkingLot.printStatus();
                    break;
                }
                case "registration_numbers_for_cars_with_colour": {
                    String colorToSearch = input.split(" ")[1];
                    List<String> registrationNumbers = parkingLot.getRegistrationNumberOfCarsUsingColor(colorToSearch);
                    Iterator registrationNumbersIterator = registrationNumbers.iterator();
                    while (registrationNumbersIterator.hasNext()) {
                        System.out.print(registrationNumbersIterator.next() + (registrationNumbersIterator.hasNext() ? ", " : ""));
                    }
                    System.out.println();
                    break;
                }
                case "slot_numbers_for_cars_with_colour": {
                    String colorToSearch = input.split(" ")[1];
                    List<Integer> slotNumbers = parkingLot.getSlotNumbersOfCarsUsingColor(colorToSearch);
                    Iterator slotNumbersIterator = slotNumbers.iterator();
                    while (slotNumbersIterator.hasNext()) {
                        System.out.print(slotNumbersIterator.next() + (slotNumbersIterator.hasNext() ? ", ": ""));
                    }
                    System.out.println();
                    break;
                }
                case "slot_number_for_registration_number": {
                    String registrationNumberToSearch = input.split(" ")[1];
                    int slotNumber = parkingLot.getSlotNumberOfCarUsingRegistrationNo(registrationNumberToSearch);
                    System.out.println(slotNumber != -1 ? slotNumber : "Not found");
                    break;
                }
                default: {
                    System.out.println("command invalid");
                }
            }
        }
    }
}
