package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.*;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidArgumentException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomTest {

    private Set<AbstractCommodity> commodities;
    private Room room;
    private Booking firstBooking;
    private Booking secondBooking;
    private Booking thirdBooking;

    @BeforeEach
    public void setUp() {
        commodities = new HashSet<>();
        // create several commodities
        AbstractCommodity bed = new Bed(commodities.size() + 1, BedType.DOUBLE);
        AbstractCommodity toilet = new Toilet(commodities.size() + 1, "Blue");
        commodities.add(toilet);
        commodities.add(bed);

        createBookingsForCurrentRoom();

        int roomId = 1;
        room = new Room(roomId, commodities);

        room.createBooking(firstBooking);
        room.createBooking(secondBooking);
        room.createBooking(thirdBooking);
    }

    @Test
    public void removeExistingBooking() {
        // given
        // three bookings already exist
        int bookingsSetSize = 2;

        // when
        room.removeBooking(secondBooking);

        //then
        assertTrue(room.checkForAvailability(secondBooking));
        assertThat(room.getBookings().size(), is(equalTo(bookingsSetSize)));
    }

    @Test
    public void removeBookingThatDoesNotExist() {
        // given
        String guestName = "John Johnson";
        String guestId = "9704123456";
        LocalDate from = LocalDate.of(2019, 12, 6);
        LocalDate to = LocalDate.of(2019, 12, 12);
        Booking removeBooking = new Booking(1, guestName, guestId, from, to);

        //when and then
        assertThrows(InvalidBookingException.class, () -> room.removeBooking(removeBooking));
    }

    @Test
    public void checkIThereIsNoDuplicateBookingsDates() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 3, 16);
        LocalDate toDate = LocalDate.of(2019, 3, 25);
        String guestID = "9413043456";
        Booking newBooking = new Booking(1, "John Johnson", guestID, fromDate, toDate);

        // when
        boolean check = room.checkForAvailability(newBooking);

        // then
        assertThat(check, is(equalTo(true)));
    }

    @Test
    public void checkIThereIsDuplicateBookingsDates() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 5, 21);
        LocalDate toDate = LocalDate.of(2019, 5, 23);
        String guestID = "9604231345";
        Booking newBooking = new Booking(1, "John Johnson", guestID, fromDate, toDate);

        // when
        boolean check = room.checkForAvailability(newBooking);

        // then
        assertThat(check, is(equalTo(false)));
    }

    @Test
    public void findAvailableIntervalBookings() {
        // given
        LocalDate fromDate = LocalDate.of(2019, 05, 10);
        LocalDate toDate = LocalDate.of(2019, 05, 30);
        Set<Booking> freeInterval;
        int expectedSize = 3, days = 3;

        // when
        freeInterval = room.findAvailableBookings(fromDate, toDate, days);

        // then
        assertThat(freeInterval.size(), is((equalTo(expectedSize))));
    }

    @Test
    public void findUnavailableIntervalBookings() {
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
    public void createValidBooking() {
        // given
        int roomID = 1;
        LocalDate fromDate = LocalDate.of(2019, 7, 13);
        LocalDate toDate = LocalDate.of(2019, 7, 25);
        String guestID = "9304012345";
        Booking newBooking = new Booking(1, "Mariya Mariya", guestID, fromDate, toDate);

        // when and then
        assertThat(room.createBooking(newBooking), is(equalTo(roomID)));
        assertTrue(room.getBookings().contains(newBooking));
    }

    @Test
    public void createdBookingNullCheck() {
        // given
        Booking newBooking = null;

        // when and then
        assertThrows(InvalidBookingException.class, () -> room.createBooking(newBooking));
    }

    @Test
    public void roomCommoditiesNullCheck() {
        //given
        Set<AbstractCommodity> commodities = null;

        // when and then
        assertThrows(InvalidArgumentException.class, () -> room.setCommodities(commodities));
    }

    @Test
    public void checkValidRoomCommodity() {
        //given
        AbstractCommodity commodity = new Bed(commodities.size() + 1, BedType.KING);
        int capacity = 4;

        // when
        room.addCommodity(commodity);

        //then
        assertThat(room.getCapacity(), is(equalTo(capacity)));
    }

    @Test
    public void checkCapacity() {
        // given
        int expectedCapacity = 2;

        //when
        int actualCapacity = room.getCapacity();

        //then
        assertThat(actualCapacity, is(equalTo(expectedCapacity)));
    }

    private void createBookingsForCurrentRoom() {
        // dates
        LocalDate firstFromDate = LocalDate.of(2019, 05, 15);
        LocalDate firstToDate = LocalDate.of(2019, 05, 18);

        LocalDate secondFromDate = LocalDate.of(2019, 05, 21);
        LocalDate secondToDate = LocalDate.of(2019, 05, 23);

        LocalDate thirdFromDate = LocalDate.of(2019, 3, 21);
        LocalDate thirdToDate = LocalDate.of(2019, 3, 26);

        // create several bookings
        String firstGuestID = "9413043456";
        firstBooking = new Booking(1, "Daniel Danielson", firstGuestID, firstFromDate, firstToDate);

        String secondGuestID = "9413043456";
        secondBooking = new Booking(2, "Heath Heathens", secondGuestID, secondFromDate, secondToDate);

        String thirdGuestID = "9204124563";
        thirdBooking = new Booking(3, "John Miller", thirdGuestID, thirdFromDate, thirdToDate);
    }
}