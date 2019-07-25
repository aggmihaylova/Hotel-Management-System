package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Toilet;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomTest {

    private Set<AbstractCommodity> commodities = new HashSet<>();
    private Set<LocalDate> maintenanceDates = new HashSet<>();
    private Room room;
    private Booking firstBooking;
    private Booking secondBooking;
    private Booking thirdBooking;
    private Set<Booking> bookings = new HashSet<>();

    @BeforeEach
    public void setUp() {
        // dates
        LocalDate firstFromDate = LocalDate.of(2019, 05, 15);
        LocalDate firstToDate = LocalDate.of(2019, 05, 18);

        LocalDate secondFromDate = LocalDate.of(2019, 05, 21);
        LocalDate secondToDate = LocalDate.of(2019, 05, 23);

        LocalDate thirdFromDate = LocalDate.of(2019, 3, 21);
        LocalDate thirdToDate = LocalDate.of(2019, 3, 26);

        // creating set of bookings

        long firstGuestID = 9413043456L;
        firstBooking = new Booking(bookings.size() + 1, "Daniel Danielson", firstGuestID, firstFromDate, firstToDate);
        bookings.add(firstBooking);

        long secondGuestID = 9413043456L;
        secondBooking = new Booking(bookings.size() + 1, "Heath Heathens", secondGuestID, secondFromDate, secondToDate);
        bookings.add(secondBooking);

        long thirdGuestID = 9204124563L;
        thirdBooking = new Booking(bookings.size() + 1, "John Miller", thirdGuestID, thirdFromDate, thirdToDate);
        bookings.add(thirdBooking);

        // set of commodities
        AbstractCommodity bed = new Bed(commodities.size() + 1, BedType.DOUBLE);
        AbstractCommodity toilet = new Toilet(commodities.size() + 1, "Blue");
        commodities.add(toilet);
        commodities.add(bed);

        room = new Room(1, commodities, maintenanceDates, bookings);
    }

    @Test
    public void removeExistingBooking() {
        // given
        int bookingsSetSize = 2;

        // when
        room.removeBooking(secondBooking);

        //then
        assertThat(room.getBookings().size(), is(equalTo(bookingsSetSize)));
    }

    @Test
    public void removeBookingThatDoesNotExist() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 12, 6);
        LocalDate toDate = LocalDate.of(2019, 12, 12);
        long removeBookingGuestID = 9704123456L;
        Booking removeBooking = new Booking(bookings.size() + 1, "John Johnson", removeBookingGuestID, fromDate, toDate);

        //when and then
        assertThrows(InvalidBookingException.class, () -> room.removeBooking(removeBooking));
    }

    @Test
    public void checkFreeDate() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 3, 16);
        LocalDate toDate = LocalDate.of(2019, 3, 25);
        long guestID = 9413043456L;
        Booking newBooking = new Booking(bookings.size() + 1, "John Johnson", guestID, fromDate, toDate);

        // when
        boolean check = room.checkForAvailability(newBooking);

        // then
        assertThat(check, is(equalTo(true)));
    }

    @Test
    public void checkNotFreeDate() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 5, 21);
        LocalDate toDate = LocalDate.of(2019, 5, 23);
        long guestID = 9604231345L;
        Booking newBooking = new Booking(bookings.size() + 1, "John Johnson", guestID, fromDate, toDate);

        // when
        boolean check = room.checkForAvailability(newBooking);

        // then
        assertThat(check, is(equalTo(false)));
    }

    @Test
    public void findAvailableDatesForIntervalAndSize() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 05, 10);
        LocalDate toDate = LocalDate.of(2019, 05, 30);
        Set<Booking> freeInterval;
        int size = 3, days = 3;

        // when
        freeInterval = room.findAvailableBookings(fromDate, toDate, days);

        // then
        assertThat(freeInterval.size(), is((equalTo(size))));
    }

    @Test
    public void checkInvalidMaintenanceDates() {
        // given
        Set<LocalDate> maintenanceDates = null;

        // when and then
        assertThrows(MissingArgumentException.class, () -> room.setMaintenanceDates(maintenanceDates));
    }

    @Test
    public void checkInvalidBookings() {
        //given
        Set<Booking> bookingsSet = null;

        // when and then
        assertThrows(MissingArgumentException.class, () -> room.setBookings(bookingsSet));
    }

    @Test
    public void createValidBooking() {
        // given
        int roomID = 1;
        LocalDate fromDate = LocalDate.of(2019, 7, 13);
        LocalDate toDate = LocalDate.of(2019, 7, 25);
        long guestID = 9304012345L;
        Booking newBooking = new Booking(bookings.size() + 1, "Mariya Mariya", guestID, fromDate, toDate);

        // when and then
        assertThat(room.createBooking(newBooking), is(equalTo(roomID)));
    }

    @Test
    public void createInvalidBooking() {
        // given
        Booking newBooking = null;

        // when and then
        assertThrows(InvalidBookingException.class, () -> room.createBooking(newBooking));
    }

    @Test
    public void checkInvalidCommodities() {
        //given
        Set<AbstractCommodity> commodities = null;

        // when and then
        assertThrows(MissingArgumentException.class, () -> room.setCommodities(commodities));
    }

    @Test
    public void checkInvalidCommodity() {
        //given
        AbstractCommodity commodity = null;

        // when and then
        assertThrows(MissingArgumentException.class, () -> room.addCommodity(commodity));
    }

    @Test
    public void checkValidCommodity() {
        //given
        AbstractCommodity commodity = new Bed(commodities.size() + 1, BedType.TRIPLE);
        int capacity = 5;

        // when
        room.addCommodity(commodity);

        assertThat(room.getCapacity(), is(equalTo(capacity)));
    }

    @Test
    public void findUnavailableDatesForIntervalAndSize() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 05, 21);
        LocalDate toDate = LocalDate.of(2019, 05, 23);
        Set<Booking> freeInterval;
        int size = 0;

        // when
        freeInterval = room.findAvailableBookings(fromDate, toDate, 3);

        // then
        assertThat(freeInterval.size(), is((equalTo(size))));
    }

    @Test
    public void checkCapacity() {
        // given
        int expectedCapacity = 2;

        //when
        int actualCapacity = room.getCapacity();

        //when
        assertThat(actualCapacity, is(equalTo(expectedCapacity)));
    }
}