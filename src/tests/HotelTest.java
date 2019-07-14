package tests;

import commodities.Booking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.Hotel;
import project.Room;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    Set<Booking> bookings;
    Booking booking;
    short countBeds;
    ArrayList<Room> rooms;

    // other are getters and setters, addReservation calls another method

    @BeforeEach
    void setUp() {
        countBeds = 5;
        booking = new Booking(9413043456L, LocalDate.of(2019, 05, 23),
                LocalDate.of(2019, 05, 27), "John");

        bookings = new HashSet<>();
        bookings.add(booking);

        rooms = new ArrayList<>();
        rooms.add(new Room(32, null, null, bookings, (short) countBeds));

    }

    @Test
    void bookRoomByDate() {
        Hotel hotel = new Hotel("Bordeaux", rooms);
        assertEquals(null, hotel.findRoomByBeds(1));
    }

    @Test
    void findRoomByBeds() {
        Hotel hotel = new Hotel("Bordeaux", rooms);
        assertEquals(hotel.getRooms().get(0), hotel.findRoomByBeds(countBeds));
    }

    @AfterEach
    void tearDown() {
        System.out.println("End of the test");

    }
}