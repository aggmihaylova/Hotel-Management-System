package eu.deltasource.internship.hotelmanagementsystem;


import eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities.*;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


/**
 * UNDER CONSTRUCTION
 */


public class TestScenario {


    @Test
    public void test() throws NoRoomsAvailableException {

        // given

        // bookings for the first room

        Set<Booking> bookingsFirstRoom = new HashSet<>();
        bookingsFirstRoom.add(new Booking(9503123552L, LocalDate.of(2019, 5, 17),
                LocalDate.of(2019, 6, 6)));
        bookingsFirstRoom.add(new Booking(9412032156L, LocalDate.of(2019, 6, 25),
                LocalDate.of(2019, 7, 13)));

        // bookings for the second room

        Set<Booking> bookingsSecondRoom = new HashSet<>();
        bookingsSecondRoom.add(new Booking(9621123421L, LocalDate.of(2019, 7, 22),
                LocalDate.of(2019, 7, 23)));


        //bookings the third room

        Set<Booking> bookingsThirdRoom = new HashSet<>();
        bookingsThirdRoom.add(new Booking(0L, LocalDate.of(2019, 7, 19),
                LocalDate.of(2019, 7, 21)));


        Manager manager = new Manager("Pete", "Hennessy");

        // commodities for the first room

        Set<AbstractCommodity> firstRoom = new HashSet<>();
        firstRoom.add(new Bed(BedType.DOUBLE));
        firstRoom.add(new Toilet(true, "Green"));
        firstRoom.add(new Shower(true));

        // commodities for the first room

        Set<AbstractCommodity> secondRoom = new HashSet<>();
        secondRoom.add(new Bed(BedType.SINGLE));
        secondRoom.add(new Bed(BedType.SINGLE));
        secondRoom.add(new Bed(BedType.DOUBLE));
        secondRoom.add(new Toilet(false, "Blue"));
        secondRoom.add(new Toilet(true, "Red"));
        secondRoom.add(new Shower(true));

        // commodities for the third room

        Set<AbstractCommodity> thirdRoom = new HashSet<>();
        thirdRoom.add(new Bed(BedType.TRIPLE));
        thirdRoom.add(new Bed(BedType.QUAD));
        thirdRoom.add(new Toilet(false, "Yellow"));
        thirdRoom.add(new Shower(true));


        Set<LocalDate> maintenanceDatesSet = new HashSet<>();
        maintenanceDatesSet.add(LocalDate.of(2019, 04, 06));


        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, firstRoom, maintenanceDatesSet, bookingsFirstRoom));
        rooms.add(new Room(2, secondRoom, maintenanceDatesSet, bookingsSecondRoom));
        rooms.add(new Room(3, thirdRoom, maintenanceDatesSet, bookingsThirdRoom));


        Hotel hotel = new Hotel("Rose", rooms);
        manager.setHotel(hotel);

        // required intervals

        Booking firstBookingInterval = new Booking(0L, LocalDate.of(2019, 7, 20),
                LocalDate.of(2019, 7, 25));
        Booking secondBookingInterval = new Booking(0L, LocalDate.of(2019, 7, 22),
                LocalDate.of(2019, 7, 23));
        Booking thirdBookingInterval = new Booking(0L, LocalDate.of(2019, 7, 19),
                LocalDate.of(2019, 7, 21));

        // requested beds

        Bed firstRoomBed = new Bed(BedType.DOUBLE);
        Bed secondRoomBed = new Bed(BedType.KING);
        Bed thirdRoomBed = new Bed(BedType.QUAD);

        // when 1
        int roomID = manager.createBooking(firstBookingInterval.getFrom(), firstBookingInterval.getTo(),
                2, 567);

        // then
        assertThat("Unavailable room with bed for 2", roomID, is(equalTo(1)));


        // when 2
        //    roomID = manager.createBooking(secondBookingInterval.getFrom(), secondBookingInterval.getTo(),
        //              1, 482);

        // then
//        assertThat("Available rooms ", roomID, is(equalTo(null)));


        // when 3
        // room = manager.tryReservation(thirdBookingInterval, thirdRoomBed);

        //then
        //  assertThat("Free date", room, is(equalTo(null))); // busy date


    }
}

