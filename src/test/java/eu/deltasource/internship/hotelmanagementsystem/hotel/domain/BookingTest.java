package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidArgumentException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private LocalDate from;
    private LocalDate to;

    @BeforeEach
    public void setUp() {
        from = LocalDate.of(2019, 8, 10);
        to = LocalDate.of(2019, 8, 14);
    }

    @Test
    public void createBookingWithInvalidId() {
        // given
        String guestName = "John Peterson";
        String guestId = "9105124562";
        long id = -5;

        // when and then
        assertThrows(InvalidArgumentException.class, () -> new Booking(id, guestName, guestId, from, to));
    }

    @Test
    public void createBookingWithValidDates() {
        // given
        // from and to dates already exist
        String guestName = "Mariya Miller";
        String guestId = "9405124562";
        long id = 0L;

        //when
        Booking newBooking = new Booking(id, guestName, guestId, from, to);

        //then
        assertEquals(newBooking.getFrom(), from);
    }

    @Test
    public void createBookingWithInvalidDates() {
        // given
        // from and to dates already exist
        String guestName = "Mariya Miller";
        String guestId = "9405124562";
        LocalDate from = LocalDate.of(2019, 8, 24);
        LocalDate fromDate = to;
        long id = 1L;

        //when and then
        // starting date is after ending date
        assertThrows(InvalidBookingException.class, () -> new Booking(id, guestName, guestId, from, to));
        // booking starts and ends on the same days
        assertThrows(InvalidBookingException.class, () -> new Booking(id, guestName, guestId, fromDate, to));
    }

    @Test
    public void fromDateNullCheck() {
        // given
        //to date already exists
        String guestName = "Mariya Miller";
        String guestId = "9405124562";
        LocalDate from = null;
        long id = 1L;

        // when and then
        assertThrows(InvalidBookingException.class, () -> new Booking(id, guestName, guestId, from, to));
    }

    @Test
    public void toDateNullCheck() {
        // given
        // from date already exists
        String guestName = "Mariya Miller";
        String guestId = "9405124562";
        LocalDate to = null;
        long id = 1L;

        // when and then
        assertThrows(InvalidBookingException.class, () -> new Booking(id, guestName, guestId, from, to));
    }

    @Test
    public void createBookingWithValidGuestName() {
        // given
        // from and to dates already exist
        String guestName = "John Peterson";
        String guesttId = "9105124562";
        long id = 1L;

        // when and then
        assertDoesNotThrow(() -> new Booking(id, guestName, guesttId, from, to));
    }

    @Test
    public void createBookingWithInvalidGuestName() {
        // given
        // from and to dates already exist
        String guestId = "9405124562";
        String guestName = "";
        long id = 1L;

        // when and then
        assertThrows(InvalidArgumentException.class, () -> new Booking(id, guestName, guestId, from, to));
    }

    @Test
    public void createBookingWithInvalidGuestId() {
        // given
        String guestName = "John Peterson";
        String guestId = "9123L";
        long id = 8L;

        // when and then
        assertThrows(InvalidArgumentException.class, () -> new Booking(id, guestName, guestId, from, to));
    }

    @Test
    public void createBookingWithValidGuestId() {
        // given
        String guestName = "John Peterson";
        String guestID = "9105124562";
        long id = 1L;

        // when and then
        assertDoesNotThrow(() -> new Booking(id, guestName, guestID, from, to));
    }

    @AfterEach
    void tearDown() {
        from = null;
        to = null;
    }
}