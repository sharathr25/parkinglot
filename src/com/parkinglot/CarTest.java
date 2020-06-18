package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarTest {
    @Test
    void testCar() {
        Vehicle car = new Car("KA-01-HH-1234", "White");
        assertEquals("KA-01-HH-1234", car.getRegistrationNumber());
        assertEquals("White", car.getColor());
    }
}
