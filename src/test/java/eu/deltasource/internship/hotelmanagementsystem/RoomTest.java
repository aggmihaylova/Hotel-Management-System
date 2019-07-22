package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoomTest {

    private Set<AbstractCommodity> commodities;
    private Set<LocalDate> maintenanceDates;
    private Room room;
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
        firstBooking = new Booking(9413043456L);
        firstBooking.saveDate(firstFromDate, firstToDate);
        secondBooking = new Booking(9413043456L);
        secondBooking.saveDate(secondFromDate, secondToDate);

        // set of bookings

        bookings = new HashSet<>();
        bookings.add(firstBooking);
        bookings.add(secondBooking);

        // set of maintenance dates
        maintenanceDates = new HashSet<>();
        maintenanceDates.add(LocalDate.of(2019, 5, 26));


        // set of commodities
        commodities = new HashSet<>();
        AbstractCommodity bed = new Bed(commodities.size() + 1, BedType.DOUBLE);
        commodities.add(bed);
        AbstractCommodity toilet = new Toilet(commodities.size() + 1, "Blue");
        commodities.add(toilet);

        room = new Room(1, commodities, maintenanceDates, bookings);
        room.saveCapacity(toilet);
        room.saveCapacity(bed);
    }

    @Test
    void removeExistingBooking() {
        // given
        Booking removeBooking = secondBooking;

        // when
        boolean check = room.removeBooking(removeBooking);

        //then
        assertThat(check, is(equalTo(true)));
    }

    @Test
    void removeBookingThatDoesNotExist() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 12, 6);
        LocalDate toDate = LocalDate.of(2019, 12, 12);
        Booking removeBooking = new Booking(9704123456L);
        removeBooking.saveDate(fromDate, toDate);

        // when and then
        assertThrows(InvalidBookingException.class, () -> room.removeBooking(removeBooking));
    }


    @Test
    void checkForAvailableDate() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 3, 16);
        LocalDate toDate = LocalDate.of(2019, 3, 25);
        Booking newBooking = new Booking(9504134532L, fromDate, toDate);

        // when
        boolean check = room.checkForAvailability(newBooking);

        // then
        assertThat(check, is(equalTo(true)));
    }

    @Test
    void checkForUnavailableDate() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 5, 23);
        LocalDate toDate = LocalDate.of(2019, 5, 25);
        Booking newBooking = new Booking(9504134532L);
        newBooking.saveDate(fromDate, toDate);

        // when
        boolean check = room.checkForAvailability(newBooking);

        // then
        assertThat(check, is(equalTo(false)));
    }

    @Test
    void findAvailableDatesForIntervalAndSize() {

        // given
        LocalDate fromDate = LocalDate.of(2019, 05, 27);
        LocalDate toDate = LocalDate.of(2019, 05, 28);
        Set<Booking> freeInterval;

        // when
        freeInterval = room.findAvailableDatesForIntervalAndSize(fromDate, toDate);

        // then
        assertThat(freeInterval, is(not(equalTo(null))));
    }

    @Test
    void findUnavailableDatesForIntervalAndSize() {

        // given
        LocalDate fromDate = LocalDate.of(2019, 05, 23);
        LocalDate toDate = LocalDate.of(2019, 05, 25);
        Set<Booking> freeInterval;
        int size = 0;

        // when
        freeInterval = room.findAvailableDatesForIntervalAndSize(fromDate, toDate);

        // then
        assertThat(freeInterval.size(), is((equalTo(size))));
    }

    @Test
    void prepareRoom() {
        // given
        int size = 2;
        LocalDate maintenanceDate = LocalDate.of(2019, 5, 12);

        // when
        room.prepareRoom(maintenanceDate);

        //then
        assertThat(room.getMaintenanceDates().size(), is(equalTo(size)));
    }

    @Test
    void checkCapacity() {
        // given
        int expectedCapacity = 2;

        //when
        int actualCapacity = room.getCapacity();

        //when
        assertThat(actualCapacity, is(equalTo(expectedCapacity)));

    }

    @Test
    void createBookingTest() {

        // given
        int roomID = 1;
        LocalDate fromDate = LocalDate.of(2019, 7, 13);
        LocalDate toDate = LocalDate.of(2019, 7, 25);
        Booking newBooking = new Booking(9304012345L, fromDate, toDate);

        // when and then

        assertThat(room.createBooking(newBooking), is(equalTo(roomID)));
    }
}