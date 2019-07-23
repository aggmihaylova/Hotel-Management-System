package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Booking;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Room;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomTest {
/*
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

        // set of bookings
        long firstGuestID = 9413043456L;
        firstBooking = new Booking();
        firstBooking.saveDate(firstFromDate, firstToDate);
        firstBooking.saveGuestID(firstGuestID);

        long secondGuestID = 9413043456L;
        secondBooking = new Booking();
        secondBooking.saveDate(secondFromDate, secondToDate);
        secondBooking.saveGuestID(secondGuestID);

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
        long removeBookingGuestID = 9704123456L;
        Booking removeBooking = new Booking();
        removeBooking.saveGuestID(removeBookingGuestID);
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
        Booking newBooking = new Booking();
        long guestID = 9504134532L;
        newBooking.saveDate(fromDate, toDate);
        newBooking.saveGuestID(guestID);

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
        long guestID = 9304012345L;
        Booking newBooking = new Booking();
        newBooking.saveDate(fromDate, toDate);
        newBooking.saveGuestID(guestID);

        // when and then

        assertThat(room.createBooking(newBooking), is(equalTo(roomID)));
    }*/
}