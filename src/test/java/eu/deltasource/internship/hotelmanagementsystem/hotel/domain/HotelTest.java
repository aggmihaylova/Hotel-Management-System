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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {

    private Hotel hotel;
    private Set<AbstractCommodity> commodities;
    private Set<Booking> bookings;
    private List<Room> rooms;

    @BeforeEach
    void setUp() {
        commodities = new HashSet<>();
        bookings = new HashSet<>();
        rooms = new ArrayList<>();
        Set<LocalDate> maintenanceDates = new HashSet<>();

        // set of bookings
        long guestID = 9504124582L;
        LocalDate fromDate = LocalDate.of(2019, 05, 23);
        LocalDate toDate = LocalDate.of(2019, 05, 27);
        Booking newBooking = new Booking(guestID, fromDate, toDate);

        bookings.add(newBooking);

        rooms.add(new Room(rooms.size() + 1, commodities, maintenanceDates, bookings));

        hotel = new Hotel("Bordeaux", rooms);
    }

    @Test
    public void checkAndFindAvailableRooms() {
        // given
        LocalDate from = LocalDate.of(2019, 7, 3);
        LocalDate to = LocalDate.of(2019, 7, 12);
        int numberOfPeople = 3;
        int sizeOfFreeRooms = 1;
        List<Room> freeRooms;

        // when
        freeRooms = hotel.findAvailableRooms(from, to, numberOfPeople);

        // then
     //   assertThat("There are no available rooms", freeRooms.size(), equalTo(sizeOfFreeRooms));
    }

    @Test
    public void checkAndFindUnavailableRooms() {
        // given
        LocalDate from = LocalDate.of(2019, 3, 3);
        LocalDate to = LocalDate.of(2019, 3, 12);
        int numberOfPeople = 12;
        int size = 0;

        // when
        List<Room> freeRoom = hotel.findAvailableRooms(from, to, numberOfPeople);

        // then
        assertThat("There are available rooms", freeRoom.size(), equalTo(size));
    }

    @Test
    public void checkIfRoomsAreNull() {
        //given
        List<Room> newRooms = null;

        // when and then
        assertThrows(MissingArgumentException.class, () -> hotel.setRooms(newRooms));
    }

    @Test
    public void checkIfRoomsAreNotNull() {
        // given and when
        hotel.setRooms(rooms);
        int minValue = 1;

        //then
        assertTrue(hotel.getRooms().size() >= minValue);
    }

    @Test
    public void checkIfHotelNameValid() {
        //given
        String hotelName = "Rose";

        //when
        hotel.setName(hotelName);

        //then
        assertTrue(hotel.getName().equals(hotelName));
    }

    @Test
    public void checkIfHotelNameIsInvalid() {
        //given
        String hotelName = null;
        String nameHotel = "";

        //when 1 and then 1
        assertThrows(MissingArgumentException.class, () -> hotel.setName(hotelName));

        // when 2 and then 2
        assertThrows(MissingArgumentException.class, () -> hotel.setName(hotelName));
    }
}
