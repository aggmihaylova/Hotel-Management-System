package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.BedType;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;
import org.junit.jupiter.api.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class HotelTest {

    @Test
    void findAvailableRooms() {

        Set<AbstractCommodity> commodities = new HashSet<>();
        Set<Booking> bookings = new HashSet<>();
        List<Room> rooms = new ArrayList<>();

        // set of bookings
        LocalDate fromDate = LocalDate.of(2019, 05, 23);
        LocalDate toDate = LocalDate.of(2019, 05, 27);

        bookings.add(new Booking(9413043456L, fromDate, toDate));

        // set of commodities
        commodities.add(new Bed(BedType.TRIPLE));

        // set of maintenance dates
        Set<LocalDate> maintenanceDates = new HashSet<>();
        LocalDate maintenanceDate = LocalDate.of(2019, 5, 21);
        maintenanceDates.add(maintenanceDate);

        // array list of rooms
        rooms.add(new Room(commodities, maintenanceDates, bookings));


        Hotel hotel = new Hotel("Bordeaux", rooms);

        // given
        LocalDate from = LocalDate.of(2019, 7, 3);
        LocalDate to = LocalDate.of(2019, 7, 12);
        int numberOfPeople = 3;

        // when
        List<Room> freeRoom = hotel.findAvailableRooms(from, to, numberOfPeople);

        // then
        assertThat("There are available rooms", freeRoom, is(not(equalTo(null))));
    }
}