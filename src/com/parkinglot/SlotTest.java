package com.parkinglot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotTest {
    @Test
    void testSlotOnCreation() {
        Slot slot = new Slot();
        assertEquals(true, slot.getAvailability());
        assertEquals( null, slot.getVehicle());
    }

    @Test
    void testSlotAvailability() {
        Slot slot = new Slot();
        slot.setAvailability(true);
        assertEquals(true, slot.getAvailability());
        slot.setAvailability(false);
        assertEquals(false, slot.getAvailability());
    }

}

