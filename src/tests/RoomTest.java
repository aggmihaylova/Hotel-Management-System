package tests;

import commodities.Booking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {


    Booking booking;
    Set<Booking> bookings;

    @BeforeEach
    void setUp() {
        booking = new Booking(9413043456L, LocalDate.of(2019, 05, 23),
                LocalDate.of(2019, 05, 27), "John");

        bookings = new HashSet<>();
        bookings.add(booking);
    }

    @Test
    void removeBooking() {

        Booking removeBooking = new Booking(9413043436L, LocalDate.of(2019, 06, 23),
                LocalDate.of(2019, 06, 27), "Joe");


        assertEquals(false, bookings.remove(removeBooking));
    }

    @Test
    void checkForAvailability() {

        for (Booking book : bookings)
            assertEquals(true, book.equals(booking));

    }

    @Test
    void findAvailableDatesForIntervalAndSize() {

        // under construction
    }
    @AfterEach
    void tearDown() {
        System.out.println("End of test");
    }
}