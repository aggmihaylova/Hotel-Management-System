package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HotelTest {

    private String hotelName = "Rose";
    private Hotel hotel = new Hotel(hotelName);
    private Set<AbstractCommodity> commodities = new HashSet<>();
    private Set<Booking> bookings = new HashSet<>();
    private List<Room> rooms = new ArrayList<>();
    private Set<LocalDate> maintenanceDates = new HashSet<>();

    @BeforeEach
    public void setUp() {
        // set of bookings
        String guestID = "9504124582";
        LocalDate fromDate = LocalDate.of(2019, 05, 23);
        LocalDate toDate = LocalDate.of(2019, 05, 27);
        Booking newBooking = new Booking(bookings.size() + 1, "John Miller", guestID, fromDate, toDate);

        Bed bed = new Bed(commodities.size() + 1, BedType.TRIPLE);
        commodities.add(bed);

        bookings.add(newBooking);

        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));

        hotel.setRooms(rooms);
    }

    @Test
    public void checkForAvailableRooms() {
        // given
        LocalDate from = LocalDate.of(2019, 7, 3);
        LocalDate to = LocalDate.of(2019, 7, 12);
        int numberOfPeople = 3;
        int sizeOfFreeRooms = 1;
        List<Room> freeRooms;

        // when
        freeRooms = hotel.findAvailableRooms(from, to, numberOfPeople, 5);

        // then
        assertThat(freeRooms.size(), is(equalTo(sizeOfFreeRooms)));
    }

    @Test
    public void checkUnavailableRooms() {
        // given
        LocalDate from = LocalDate.of(2019, 3, 3);
        LocalDate to = LocalDate.of(2019, 3, 12);
        int numberOfPeople = 12;
        int size = 0;

        // when
        List<Room> freeRoom = hotel.findAvailableRooms(from, to, numberOfPeople, 6);

        // then
        assertThat(freeRoom.size(), is(equalTo(size)));
    }

    @Test
    public void checkRoomsNull() {
        //given
        List<Room> newRooms = null;

        // when and then
        assertThrows(MissingArgumentException.class, () -> hotel.setRooms(newRooms));
    }

    @Test
    public void checkInvalidHotelName() {
        //given
        String hotelName = null;
        String nameHotel = "";

        //when I and then I
        assertThrows(MissingArgumentException.class, () -> hotel.setName(hotelName));

        // when II and then II
        assertThrows(MissingArgumentException.class, () -> hotel.setName(nameHotel));
    }

    @Test
    public void checkIfBookingUnsuccessful() {
        //given
        Room room = null;
        String guestName = "Maya Miller";
        LocalDate from = LocalDate.of(2019, 4, 21);
        LocalDate to = LocalDate.of(2019, 4, 27);
        String guestID = "9103124562";
        Booking booking = new Booking(bookings.size() + 1, guestName, guestID, from, to);

        //when and then
        assertThrows(MissingArgumentException.class, () -> hotel.createReservation(booking, room));
    }

    @Test
    public void checkIfBookingSuccessful() {
        //given
        int roomID = 1;
        Set<Booking> bookings = new HashSet<>();
        Room room = new Room(roomID, commodities, maintenanceDates, bookings);
        String guestName = "Maya Miller";
        LocalDate from = LocalDate.of(2019, 4, 21);
        LocalDate to = LocalDate.of(2019, 4, 27);
        String guestID = "9103124562";
        Booking booking = new Booking(bookings.size() + 1, guestName, guestID, from, to);

        //when
        int bookedRoomID = hotel.createReservation(booking, room);

        //then
        assertTrue(bookedRoomID == roomID);
    }
}
