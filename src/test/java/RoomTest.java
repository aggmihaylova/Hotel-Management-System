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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

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
        rooms.add(new Room(543, null, null, bookings, (short) 5));

    }

    @Test
    void removeBooking() throws Exception {

        // given

        Booking removeBooking = secondBooking;

        // when
        boolean check = rooms.get(0).removeBooking(secondBooking);

        //then
        assertThat(check, is(equalTo(true)));
    }

    @Test
    void checkForAvailability() {
        // given
        Booking booking = firstBooking;

        // when and then

        boolean check = rooms.get(0).checkForAvailability(firstBooking);

        assertThat(check, is(equalTo(false)));

    }

    @Test
    void findAvailableDatesForIntervalAndSize() {

        // given
        Booking userInterval = new Booking(9403211345L, LocalDate.of(2019, 05, 27),
                LocalDate.of(2019, 05, 28), "Ivan");

        // when
        Booking free = rooms.get(0).findAvailableDatesForIntervalAndSize(userInterval);

        // then
        //   assertNotNull(free);
        assertThat(free, is(not(equalTo(null))));


    }

    @AfterEach
    void tearDown() {
        System.out.println("End of test");
    }
}