package eu.deltasource.internship.hotelmanagementsystem;

import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.AbstractCommodity;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Bed;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.BedSize;
import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.Booking;


import org.junit.jupiter.api.AfterEach;
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


class ManagerTest {

    // others are getters and setters

    private List<Room> rooms;
    private Set<AbstractCommodity> commodities;
    private Set<Booking> bookings;
    private Hotel hotel;
    private Manager manager;


    @BeforeEach
    public void SetUp() {
        // create ArrayList of rooms
        rooms = new ArrayList<>();

        // create HashSet of commodities (in this case - only one bed)
        commodities = new HashSet<>();
        AbstractCommodity bed = new Bed(123, BedSize.DOUBLE);
        commodities.add(bed);

        // create HashSet of bookings
        bookings = new HashSet<>();

        bookings.add(new Booking(0L, LocalDate.of(2019, 04, 30),
                LocalDate.of(2019, 05, 6)));
        bookings.add(new Booking(1L, LocalDate.of(2019, 05, 15),
                LocalDate.of(2019, 05, 20)));

        // add room in the arraylist of rooms
        rooms.add(new Room(100, commodities, null, bookings, (short) 1));


        hotel = new Hotel("Bordeaux", rooms);

        manager = new Manager("John", "Johnson", hotel);

    }

    @Test
    void createBooking() throws UnavailableRooms {

        // given
        Booking interval = new Booking(9505124521L, LocalDate.of(2019, 10, 3),
                LocalDate.of(2019, 5, 14));


        // when
        int roomID = manager.createBooking(interval.getFrom(), interval.getTo(), 2, 586);

        //then
        assertThat("The booking was successfull", roomID, is(equalTo(100)));

    }

    @AfterEach
    public void TearDown() {
        System.out.println("End of the test");
    }

}