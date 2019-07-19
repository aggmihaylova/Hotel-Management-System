package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;

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

    private Set<AbstractCommodity> commodities;
    private List<Room> rooms;
    private Booking firstBooking;
    private Booking secondBooking;
    private Set<Booking> bookings;

    @BeforeEach
    public void setUp() {
        // dates
        LocalDate firstFromDate = LocalDate.of(2019, 05, 23);
        LocalDate firstToDate = LocalDate.of(2019, 05, 25);

        LocalDate secondFromDate = LocalDate.of(2019, 05, 30);
        LocalDate secondToDate = LocalDate.of(2019, 06, 5);

        // bookings
        firstBooking = new Booking(9413043456L, firstFromDate, firstToDate);
        secondBooking = new Booking(9413043456L, secondFromDate, secondToDate);

        // set of bookings

        bookings = new HashSet<>();
        bookings.add(firstBooking);
        bookings.add(secondBooking);

        // set of maintenance dates
        Set<LocalDate> maintenanceDates = new HashSet<>();
        maintenanceDates.add(LocalDate.of(2019, 5, 26));

        // set of commodities
        commodities = new HashSet<>();
        AbstractCommodity bed = new Bed(BedType.DOUBLE);
        commodities.add(bed);

        // array list of rooms
        rooms = new ArrayList<>();
        rooms.add(new Room(1,commodities, maintenanceDates, bookings));
    }

    @Test
    void removeBooking() throws Exception {
        // given
        Booking removeBooking = secondBooking;

        // when
        boolean check = rooms.get(0).removeBooking(removeBooking);

        //then
        assertThat(check, is(equalTo(true)));
    }

    @Test
    void checkForAvailability() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 3, 16);
        LocalDate toDate = LocalDate.of(2019, 3, 25);
        Booking newBooking = new Booking(9504134532L, fromDate, toDate);

        // when
        boolean check = rooms.get(0).checkForAvailability(newBooking);

        // then
        assertThat(check, is(equalTo(true)));
    }

    @Test
    void findAvailableDatesForIntervalAndSize() {

        // given
        LocalDate fromDate = LocalDate.of(2019, 05, 27);
        LocalDate toDate = LocalDate.of(2019, 05, 28);
        Set<Booking> freeInterval;

        // when
        freeInterval = rooms.get(0).findAvailableDatesForIntervalAndSize(fromDate, toDate);

        // then
        assertThat(freeInterval, is(not(equalTo(null))));
    }
}