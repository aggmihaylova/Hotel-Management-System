package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidArgumentException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import org.junit.jupiter.api.AfterEach;
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
import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {

    private final String HOTEL_NAME = "Rose";
    private Hotel hotel;


    @BeforeEach
    public void setUp() {
        hotel = new Hotel(HOTEL_NAME);
    }

    @Test
    public void findAvailableRoomsForSpecificPeriod() {
        // given
        createHotelRooms();
        LocalDate from = LocalDate.of(2019, 7, 3);
        LocalDate to = LocalDate.of(2019, 7, 12);
        int numberOfPeople = 1;
        int numberOfFreeRooms = 1;
        int days = 5;

        // when
        List<Room> freeRooms = hotel.findAvailableRooms(from, to, numberOfPeople, days);

        // then
        assertThat(freeRooms.size(), is(equalTo(numberOfFreeRooms)));
    }

    @Test
    public void findUnavailableRoomsForSpecificPeriod() {
        // given
        createHotelRooms();
        LocalDate from = LocalDate.of(2019, 05, 19);
        LocalDate to = LocalDate.of(2019, 05, 25);
        int numberOfPeople = 7;
        int size = 0;
        int days = 6;

        // when
        List<Room> freeRoom = hotel.findAvailableRooms(from, to, numberOfPeople, days);

        // then
        assertThat(freeRoom.size(), is(equalTo(size)));
    }

    @Test
    public void setRoomsNullCheck() {
        //given
        List<Room> newRooms = null;
        List<Room> rooms = new ArrayList<>();
        rooms.add(null);

        // when and then
        assertThrows(InvalidArgumentException.class, () -> hotel.setRooms(newRooms));
        assertThrows(InvalidArgumentException.class, () -> hotel.setRooms(rooms));
    }

    @Test
    public void checkInvalidHotelName() {
        //given
        String hotelName = null;
        String nameHotel = "";

        //when and then
        assertThrows(InvalidArgumentException.class, () -> hotel.setName(hotelName));
        assertThrows(InvalidArgumentException.class, () -> hotel.setName(nameHotel));
    }

    @Test
    public void createBookingUnsuccessfully() {
        //given
        Room room = null;
        String guestName = "Maya Miller";
        LocalDate from = LocalDate.of(2019, 4, 21);
        LocalDate to = LocalDate.of(2019, 4, 27);
        String guestID = "9103124562";
        Booking booking = new Booking(1, guestName, guestID, from, to);

        //when and then
        assertThrows(InvalidArgumentException.class, () -> hotel.createReservation(booking, room));
    }

    @Test
    public void createBookingSuccessfully() {
        //given
        createHotelRooms();
        int roomId = 1;
        Room room = hotel.getRooms().get(0);
        String guestName = "Maya Miller";
        LocalDate from = LocalDate.of(2019, 4, 21);
        LocalDate to = LocalDate.of(2019, 4, 27);
        String guestID = "9103124562";
        Booking booking = new Booking(2, guestName, guestID, from, to);

        //when
        int bookedRoomId = hotel.createReservation(booking, room);

        //then
        assertEquals(roomId, bookedRoomId);
        assertTrue(hotel.getRooms().get(0).getBookings().contains(booking));
    }

    @Test
    public void removeExistingBooking() {
        //given
        createHotelRooms();
        int bookingId = 1;
        Room room = hotel.getRooms().get(0);
        String guestID = "9504124582";
        String guestName = "John Miller";
        LocalDate fromDate = LocalDate.of(2019, 05, 23);
        LocalDate toDate = LocalDate.of(2019, 05, 27);
        Booking booking = new Booking(bookingId, guestName, guestID, fromDate, toDate);
        int hotelRoomBookingsSize = 0;

        //when
        hotel.removeCurrentBooking(room, booking);

        //then
        assertEquals(hotelRoomBookingsSize, hotel.getRooms().get(0).getBookings().size());
    }

    @Test
    public void removeBookingThatDoesNotExist() {
        //given
        createHotelRooms();
        int bookingId = 1;
        Room room = hotel.getRooms().get(0);
        String guestID = "9509124582";
        String guestName = "Maya Miller";
        LocalDate fromDate = LocalDate.of(2019, 05, 5);
        LocalDate toDate = LocalDate.of(2019, 05, 12);
        Booking booking = new Booking(bookingId, guestName, guestID, fromDate, toDate);

        //when and then
        assertThrows(InvalidBookingException.class, () -> hotel.removeCurrentBooking(room, booking));
    }

    @AfterEach
    public void tearDown() {
        hotel = null;
    }

    void createHotelRooms() {
        List<Room> rooms = new ArrayList<>();
        Set<AbstractCommodity> commodities;

        int id = 1;
        String guestID = "9504124582";
        String guestName = "John Miller";
        LocalDate fromDate = LocalDate.of(2019, 05, 23);
        LocalDate toDate = LocalDate.of(2019, 05, 27);
        Booking booking = new Booking(id, guestName, guestID, fromDate, toDate);

        commodities = new HashSet<>();
        Bed bed = new Bed(commodities.size() + 1, BedType.DOUBLE);
        commodities.add(bed);

        rooms.add(new Room(rooms.size() + 1, commodities));
        rooms.get(0).createBooking(booking);

        hotel.setRooms(rooms);
    }
}