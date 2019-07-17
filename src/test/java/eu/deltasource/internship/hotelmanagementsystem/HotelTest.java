package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.BedSize;
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

    // other are getters, setters and createReservation(..) just calls another method

    @Test
    void findAvailableRooms() {

        Set<AbstractCommodity> commodities = new HashSet<>();
        Set<Booking> bookings = new HashSet<>();
        List<Room> rooms = new ArrayList<>();


        // set of bookings
        bookings.add(new Booking(9413043456L, LocalDate.of(2019, 05, 23),
                LocalDate.of(2019, 05, 27)));

        // set of commodities
        commodities.add(new Bed(321, BedSize.TRIPLE));


        // array list of rooms
        rooms.add(new Room(32, commodities, null, bookings, (short) 1));


        Hotel hotel = new Hotel("Bordeaux", rooms);

        Manager manager = new Manager("John", "Tale", hotel);

        // given
        LocalDate from = LocalDate.of(2019, 7, 3);
        LocalDate to = LocalDate.of(2019, 7, 12);
        int numberOfPeople = 3;

        // when and then
        assertThat("There are available rooms", hotel.findAvailableRooms(from, to, numberOfPeople), is(not(equalTo(null))));


    }
}