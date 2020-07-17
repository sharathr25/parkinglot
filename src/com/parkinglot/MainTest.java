package com.parkinglot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStream() {
        System.setOut(new PrintStream(originalOut));
        System.setIn(originalIn);
    }

    @Test
    void testCreateParkingLotCommand() {
        System.setIn(new ByteArrayInputStream("create_parking_lot 6".getBytes()));
        Main.main();
        assertEquals("Created a parking lot with 6 slots\n", outContent.toString());
    }

    @Test
    void testParkCarWhenSlotAvailable() {
        System.setIn(new ByteArrayInputStream(("" +
                "create_parking_lot 6" +
                "\npark KA-01-HH-1234 White light"
        ).getBytes()));
        Main.main();
        assertEquals("Allocated slot number: 1", outContent.toString().split("\n")[1]);
    }

    @Test
    void testParkCarWhenSlotNotAvailable() {
        System.setIn(new ByteArrayInputStream(("" +
                "create_parking_lot 0" +
                "\npark KA-01-HH-1234 White light"
        ).getBytes()));
        Main.main();
        assertEquals("Sorry, parking lot is full", outContent.toString().split("\n")[1]);
    }

    @Test
    void testParkinglotWhenCarLeavesASlot() {
        System.setIn(new ByteArrayInputStream(("" +
                "create_parking_lot 1" +
                "\npark KA-01-HH-1234 White" +
                "\nleave 1"
        ).getBytes()));
        Main.main();
        assertEquals("Slot number 1 is free", outContent.toString().split("\n")[2]);
    }

    @Test
    void testStatusOfParkingLot() {
        System.setIn(new ByteArrayInputStream(("" +
                "create_parking_lot 2" +
                "\npark KA-01-HH-1234 White light" +
                "\npark KA-01-HH-1235 Black heavy" +
                "\nstatus"
        ).getBytes()));
        Main.main();
        assertEquals("Slot No.\tRegistration No\tColor", outContent.toString().split("\n")[3]);
        assertEquals("1\tKA-01-HH-1234\tWhite", outContent.toString().split("\n")[4]);
        assertEquals("2\tKA-01-HH-1235\tBlack", outContent.toString().split("\n")[5]);
    }

    @Test
    void testToGetRegistrationNumbersOfCarsUsingColor() {
        System.setIn(new ByteArrayInputStream((
                "create_parking_lot 2" +
                "\npark KA-01-HH-1234 White light" +
                "\npark KA-01-HH-1235 White heavy" +
                "\nregistration_numbers_for_cars_with_colour White"
        ).getBytes()));
        Main.main();
        assertEquals("KA-01-HH-1234, KA-01-HH-1235",outContent.toString().split("\n")[3]);
    }

    @Test
    void testToGetSlotNumbersOfCarsUsingColor() {
        System.setIn(new ByteArrayInputStream((
                "create_parking_lot 3" +
                "\npark KA-01-HH-1234 White light" +
                "\npark KA-01-HH-1235 Black light" +
                "\npark KA-01-HH-1236 White heavy" +
                "\nslot_numbers_for_cars_with_colour White"
        ).getBytes()));
        Main.main();
        assertEquals("1, 3",outContent.toString().split("\n")[4]);
    }

    @Test
    void testToGetSlotNumberOfCarUsingRegistrationNumber() {
        System.setIn(new ByteArrayInputStream((
                "create_parking_lot 3" +
                "\npark KA-01-HH-1234 White light" +
                "\nslot_number_for_registration_number KA-01-HH-1234" +
                "\nslot_number_for_registration_number KA-01-HH-1235"
        ).getBytes()));
        Main.main();
        assertEquals("1",outContent.toString().split("\n")[2]);
        assertEquals("Not found",outContent.toString().split("\n")[3]);
    }

    @Test
    void testForCompleteFunctionality() {
        System.setIn(new ByteArrayInputStream((
                "create_parking_lot 6" +
                "\npark KA-01-HH-1234 White light" +
                "\npark KA-01-HH-9999 White light" +
                "\npark KA-01-BB-0001 Black light" +
                "\npark KA-01-HH-2701 Blue heavy" +
                "\npark KA-01-HH-3141 Black heavy" +
                "\nleave 4" +
                "\nstatus" +
                "\npark KA-01-P-333 White heavy" +
                "\npark KA-01-P-333 White light" +
                "\nregistration_numbers_for_cars_with_colour White" +
                "\nslot_numbers_for_cars_with_colour White" +
                "\nslot_number_for_registration_number KA-01-HH-3141" +
                "\nslot_number_for_registration_number MH-04-AY-1111"
        ).getBytes()));
        Main.main();
        assertEquals("Created a parking lot with 6 slots\n" +
                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Allocated slot number: 4\n" +
                "Allocated slot number: 5\n" +
                "Slot number 4 is free\n" +
                "Slot No.\tRegistration No\tColor\n" +
                "1\tKA-01-HH-1234\tWhite\n" +
                "2\tKA-01-HH-9999\tWhite\n" +
                "3\tKA-01-BB-0001\tBlack\n" +
                "5\tKA-01-HH-3141\tBlack\n" +
                "Allocated slot number: 4\n" +
                "Sorry, parking lot is full\n" +
                "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n" +
                "1, 2, 4\n" +
                "5\n" +
                "Not found\n", outContent.toString()
        );
    }
}
