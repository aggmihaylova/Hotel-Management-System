package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;

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

    // others are getters and setters

    private List<Room> rooms;
    private Booking firstBooking;
    private Booking secondBooking;
    private Set<Booking> bookings;

    @BeforeEach
    public void setUp() {

        firstBooking = new Booking(9413043456L, LocalDate.of(2019, 05, 23),
                LocalDate.of(2019, 05, 25));


        secondBooking = new Booking(9413043456L, LocalDate.of(2019, 05, 30),
                LocalDate.of(2019, 06, 5));


        bookings = new HashSet<>();
        bookings.add(firstBooking);
        bookings.add(secondBooking);


        Set<LocalDate> maintenanceDates = new HashSet<>();
        maintenanceDates.add(LocalDate.of(2019, 5, 26));

        rooms = new ArrayList<>();
        rooms.add(new Room(543, null, maintenanceDates, bookings));

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
        Booking newBooking = new Booking(127L, LocalDate.of(2019, 3, 16),
                LocalDate.of(2019, 3, 25));

        // when

        boolean check = rooms.get(0).checkForAvailability(newBooking);


        // then
        assertThat(check, is(equalTo(true)));

    }

    @Test
    void findAvailableDatesForIntervalAndSize() {

        // given
        Booking userInterval = new Booking(9403211345L, LocalDate.of(2019, 05, 27),
                LocalDate.of(2019, 05, 28));
        Set<Booking> freeInterval = new HashSet<>();

        // when
        freeInterval = rooms.get(0).findAvailableDatesForIntervalAndSize(userInterval.getFrom(), userInterval.getTo());

        // then
        assertThat(freeInterval, is(not(equalTo(null))));


    }

    @AfterEach
    void tearDown() {
        System.out.println("End of the test");
    }
}