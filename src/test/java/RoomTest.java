package test.java;

import main.hotel.service.domain.commodities.Booking;
import main.java.Room;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {


    List<Room> rooms;
    Booking firstBooking;
    Booking secondBooking;
    Set<Booking> bookings;

    @BeforeEach
    void setUp() {

        firstBooking = new Booking(9413043456L, LocalDate.of(2019, 05, 23),
                LocalDate.of(2019, 05, 25), "John");


        secondBooking = new Booking(9413043456L, LocalDate.of(2019, 05, 30),
                LocalDate.of(2019, 06, 5), "John");

        bookings = new HashSet<>();
        bookings.add(firstBooking);
        bookings.add(secondBooking);

        rooms = new ArrayList<>();
        rooms.add(new Room(543,null,null,bookings,(short)5));

    }

    @Test
    void removeBooking() {

        // given

        Booking removeBooking = new Booking(9413043436L, LocalDate.of(2019, 04, 15),
                LocalDate.of(2019, 04, 27), "Joe");

        // when
        boolean check = bookings.remove(removeBooking);

        //then
        assertEquals(false, check);
    }

    @Test
    void checkForAvailability() {
        // given
             Booking booking = new Booking(9405124582L,LocalDate.of(2019,03,12),
                     LocalDate.of(2019,03,15),"John");


        // when and then
         boolean check=bookings.equals(firstBooking);

            assertEquals(false, check);

    }

    @Test
    void findAvailableDatesForIntervalAndSize() {

        // given
        Booking userInterval = new Booking(9403211345L, LocalDate.of(2019, 05, 27),
                LocalDate.of(2019, 05, 28), "Ivan");

        // when
        Booking free = rooms.get(0).findAvailableDatesForIntervalAndSize(userInterval);

        // then
       assertNotNull(free);


    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test");
    }
}