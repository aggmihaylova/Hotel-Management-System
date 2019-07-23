package eu.deltasource.internship.hotelmanagementsystem.hotel.othertests;


import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Booking;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Hotel;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Manager;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Room;
import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities.*;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


public class ScenarioTest {

    @Test
    public void test() {
/*
        // given

        // bookings for the first room
        LocalDate firstRoomFirstFromDate = LocalDate.of(2019, 5, 17);
        LocalDate firstRoomFristToDate = LocalDate.of(2019, 7, 19);
        LocalDate firstRoomSecondFromDate = LocalDate.of(2019, 7, 19);
        LocalDate firstRoomSecondToDate = LocalDate.of(2019, 7, 21);

        Set<Booking> bookingsFirstRoom = new HashSet<>();
        bookingsFirstRoom.add(new Booking(0L, firstRoomFirstFromDate, firstRoomFristToDate));
        bookingsFirstRoom.add(new Booking(0L, firstRoomSecondFromDate, firstRoomSecondToDate));

        // bookings for the second room

        LocalDate secondRoomFromDate = LocalDate.of(2019, 7, 15);
        LocalDate secondRoomToDate = LocalDate.of(2019, 7, 17);

        Set<Booking> bookingsSecondRoom = new HashSet<>();
        bookingsSecondRoom.add(new Booking(0L, secondRoomFromDate, secondRoomToDate));

        //bookings the third room

        LocalDate thirdRoomFromDate = LocalDate.of(2019, 7, 25);
        LocalDate thirdRoomToDate = LocalDate.of(2019, 7, 29);

        Set<Booking> bookingsThirdRoom = new HashSet<>();
        bookingsThirdRoom.add(new Booking(0L, thirdRoomFromDate, thirdRoomToDate));


        // commodities for the first room

        Set<AbstractCommodity> firstRoom = new HashSet<>();
        firstRoom.add(new Bed(firstRoom.size() + 1, BedType.DOUBLE));
        firstRoom.add(new Toilet(firstRoom.size() + 1, "Green"));
        firstRoom.add(new Shower(firstRoom.size() + 1, true));

        // commodities for the second room

        Set<AbstractCommodity> secondRoom = new HashSet<>();
        secondRoom.add(new Bed(secondRoom.size() + 1, BedType.SINGLE));
        secondRoom.add(new Bed(secondRoom.size() + 1, BedType.SINGLE));
        secondRoom.add(new Bed(secondRoom.size() + 1, BedType.DOUBLE));
        secondRoom.add(new Toilet(secondRoom.size() + 1, "Blue"));
        secondRoom.add(new Toilet(secondRoom.size() + 1, "Red"));
        secondRoom.add(new Shower(secondRoom.size() + 1, true));

        // commodities for the third room

        Set<AbstractCommodity> thirdRoom = new HashSet<>();
        thirdRoom.add(new Bed(thirdRoom.size() + 1, BedType.TRIPLE));
        thirdRoom.add(new Bed(thirdRoom.size() + 1, BedType.QUAD));
        thirdRoom.add(new Toilet(thirdRoom.size() + 1, "Yellow"));
        thirdRoom.add(new Shower(thirdRoom.size() + 1, true));


        Set<LocalDate> maintenanceDatesSet = new HashSet<>();
        maintenanceDatesSet.add(LocalDate.of(2019, 04, 06));


        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(rooms.size() + 1, firstRoom, maintenanceDatesSet, bookingsFirstRoom));
        rooms.add(new Room(rooms.size() + 1, secondRoom, maintenanceDatesSet, bookingsSecondRoom));
        rooms.add(new Room(rooms.size() + 1, thirdRoom, maintenanceDatesSet, bookingsThirdRoom));

        Manager manager = new Manager("Pete", "Hennessy");
        Hotel hotel = new Hotel("Rose", rooms);
        manager.setHotel(hotel);

        // booking dates
        LocalDate firstFromDate = LocalDate.of(2019, 7, 20);
        LocalDate firstToDate = LocalDate.of(2019, 7, 25);

        LocalDate secondFromDate = LocalDate.of(2019, 7, 22);
        LocalDate secondToDate = LocalDate.of(2019, 7, 23);

        LocalDate thirdFromDate = LocalDate.of(2019, 7, 19);
        LocalDate thirdToDate = LocalDate.of(2019, 7, 21);

        Booking firstBookingInterval = new Booking(0L, firstFromDate, firstToDate);
        Booking secondBookingInterval = new Booking(0L, secondFromDate, secondToDate);
        Booking thirdBookingInterval = new Booking(0L, thirdFromDate, thirdToDate);


        // when 1
        int roomID = manager.createBooking(firstBookingInterval.getFrom(), firstBookingInterval.getTo(), 2, 567);

        // then 1
        assertThat("Unavailable room with bed for 2", roomID, is(equalTo(1)));


        // when 2
        roomID = manager.createBooking(secondBookingInterval.getFrom(), secondBookingInterval.getTo(),
                1, 482);

        // then 2
        assertThat("Available rooms ", roomID, is(equalTo(2)));


        // when
        roomID = manager.createBooking(thirdBookingInterval.getFrom(), thirdBookingInterval.getTo(),
                1, 482);

        // then 3
        assertThat("Available rooms ", roomID, is(equalTo(2)));

    }*/
    }
}