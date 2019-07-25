package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    private LocalDate from;
    private LocalDate to;
    private String guestName = "Mariya Miller";
    private String guestID = "9405124562";


    @BeforeEach
    public void setUp() {

        from = LocalDate.of(2019, 8, 10);
        to = LocalDate.of(2019, 8, 14);
    }

    @Test
    public void checkIfFromIsBeforeTo() {
        // given
        long ID = 0L;

        //when
        Booking newBooking = new Booking(ID, guestName, guestID, from, to);

        //then
        assertTrue(newBooking.getFrom().equals(from));
    }

    @Test
    public void checkIfFromIsAfterTo() {
        // given
        LocalDate from = LocalDate.of(2019, 8, 24);
        LocalDate fromDate = to;
        long ID = 1L;

        //when and then I
        assertThrows(InvalidBookingException.class, () -> new Booking(ID, guestName, guestID, from, to));

        // when and then II
        assertThrows(InvalidBookingException.class,()->new Booking(ID,guestName,guestID,fromDate,to));
    }

    @Test
    public void checkIfFromIsInvalid() {
        // given
        LocalDate from = null;
        long ID = 2L;

        // when and then
        assertThrows(InvalidBookingException.class, () -> new Booking(ID, guestName, guestID, from, to));
    }

    @Test
    public void checkIfFromIsValid() {
        // given
        long ID = 3L;

        // when and then
        assertDoesNotThrow(() -> new Booking(ID, guestName, guestID, from, to));
    }

    @Test
    public void checkIfToIsInvalid() {
        // given
        LocalDate to = null;
        long ID = 4L;

        // when and then
        assertThrows(InvalidBookingException.class, () -> new Booking(ID, guestName, guestID, from, to));
    }

    @Test
    public void checkIfToIsValid() {
        // given
        long ID = 5L;

        // when and then
        assertDoesNotThrow(() -> new Booking(ID, guestName, guestID, from, to));
    }

    @Test
    public void checkIfGuestNameIsValid() {
        // given
        long ID = 6L;

        // when and then
        assertDoesNotThrow(() -> new Booking(ID, guestName, guestID, from, to));
    }

    @Test
    public void checkIfGuestNameIsInvalid() {
        // given
        String guestName = "";
        long ID = 7L;

        // when and then
        assertThrows(MissingArgumentException.class, () -> new Booking(ID, guestName, guestID, from, to));
    }

    @Test
    public void checkIfGuestIDIsInvalid() {
        // given
        String guestID = "9123L";
        long ID = 8L;

        // when and then
        assertThrows(MissingArgumentException.class, () -> new Booking(ID, guestName, guestID, from, to));
    }

    @Test
    public void checkIfGuestIDIsValid() {
        // given
        long ID = 9L;

        // when and then
        assertDoesNotThrow(() -> new Booking(ID, guestName, guestID, from, to));
    }
}