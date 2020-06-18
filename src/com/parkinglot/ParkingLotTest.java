package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    @Test
    void testCreateParkingLot() {
        ParkingLot parkingLot = new ParkingLot(6);
        assertEquals(6, parkingLot.getSize());
        Slot[] slots = parkingLot.getSlots();
        for (Slot slot: slots) {
            assertEquals(true, slot.getAvailability());
            assertEquals(null, slot.getVehicle());
        }
    }

    @Test
    void testParkAVehicleWhenThereIsASlot() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car("KA-01-HH-1234", "White");
        int slotNumber = parkingLot.allocateSlot(car);
        assertEquals(1, slotNumber);
    }

    @Test
    void testParkAVehicleWhenThereIsNoSlot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car1 = new Car("KA-01-HH-1234", "White");
        Car car2 = new Car("KA-01-HH-1235", "White");
        parkingLot.allocateSlot(car1);
        int slotNumber = parkingLot.allocateSlot(car2);
        assertEquals(-1, slotNumber);
    }

    @Test
    void testWhenVehicleLeavesASlot() {
        ParkingLot parkingLot = new ParkingLot(1);
        Car car = new Car("KA-01-HH-1234", "White");
        parkingLot.allocateSlot(car);
        int slotNumber = parkingLot.leaveSlot(1);
        assertEquals(1, slotNumber);
    }

    @Test
    void testGetRegistrationNumbersUsingColor() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car1 = new Car("KA-01-HH-1234", "White");
        Car car2 = new Car("KA-01-HH-1235", "White");
        Car car3 = new Car("KA-01-HH-1236", "Black");
        parkingLot.allocateSlot(car1);
        parkingLot.allocateSlot(car2);
        parkingLot.allocateSlot(car3);
        assertEquals(
                "[KA-01-HH-1234, KA-01-HH-1235]",
                parkingLot.getRegistrationNumberOfCarsUsingColor("White").toString()
        );
    }

    @Test
    void testGetSlotNumbersUsingColor() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car1 = new Car("KA-01-HH-1234", "White");
        Car car2 = new Car("KA-01-HH-1235", "White");
        Car car3 = new Car("KA-01-HH-1236", "Black");
        parkingLot.allocateSlot(car1);
        parkingLot.allocateSlot(car2);
        parkingLot.allocateSlot(car3);
        assertEquals(
                "[1, 2]",
                parkingLot.getSlotNumbersOfCarsUsingColor("White").toString()
        );
    }

    @Test
    void testGetSlotNumberUsingRegistrationNo() {
        ParkingLot parkingLot = new ParkingLot(3);
        Car car1 = new Car("KA-01-HH-1234", "White");
        Car car2 = new Car("KA-01-HH-1235", "White");
        Car car3 = new Car("KA-01-HH-1236", "Black");
        parkingLot.allocateSlot(car1);
        parkingLot.allocateSlot(car2);
        parkingLot.allocateSlot(car3);
        assertEquals(
                2,
                parkingLot.getSlotNumberOfCarUsingRegistrationNo("KA-01-HH-1235")
        );
    }
}
