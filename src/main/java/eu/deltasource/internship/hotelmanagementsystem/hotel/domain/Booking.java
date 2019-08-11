package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidArgumentException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import lombok.Getter;

import java.time.LocalDate;

/**
 * Represents booking for a room
 */
@Getter
public class Booking {

    private long ID;
    private String guestID;
    private String guestName;
    private LocalDate from;
    private LocalDate to;

    /**
     * This is a constructor
     *
     * @param guestID guest's ID
     * @param from    starting date
     * @param to      ending date
     */
    public Booking(long ID, String guestName, String guestID, LocalDate from, LocalDate to) {
        setID(ID);
        setGuestID(guestID);
        setGuestName(guestName);
        saveDate(from, to);
    }

    /**
     * This is a constructor
     *
     * @param from starting date
     * @param to   ending date
     */
    public Booking(LocalDate from, LocalDate to) {
        saveDate(from, to);
    }

    /**
     * Initializes the starting date
     *
     * @param from starting date
     */
    public void setFrom(LocalDate from) {
        if (from == null) {
            throw new InvalidBookingException("Invalid starting date");
        }
        this.from = from;
    }

    /**
     * Initializes the ending date
     *
     * @param to ending date
     */
    public void setTo(LocalDate to) {
        if (to == null) {
            throw new InvalidBookingException("Invalid ending date");
        }
        this.to = to;
    }

    /**
     * Initializes the guest's ID
     *
     * @param guestID guest's ID
     * @throw InvalidArgumentException if the guestID is less than 10 digits
     */
    public void setGuestID(String guestID) {
        if (guestID.length() < 10) {
            throw new InvalidArgumentException("Invalid guest ID");
        }
        this.guestID = guestID;
    }

    /**
     * Initializes/sets the guest's name
     *
     * @param guestName guest's name
     */
    public void setGuestName(String guestName) {
        if (guestName == null || guestName.isEmpty()) {
            throw new InvalidArgumentException("Invalid guest name");
        }
        this.guestName = guestName;
    }

    /**
     * Initializes the booking ID
     *
     * @param ID booking ID
     */
    public void setID(long ID) {
        if (ID < 0) {
            throw new InvalidArgumentException("Invalid booking ID");
        }
        this.ID = ID;
    }

    /**
     * Validates dates
     *
     * @param from starting date
     * @param to   ending date
     */
    public void saveDate(LocalDate from, LocalDate to) {
        setFrom(from);
        setTo(to);
        if (from.isAfter(to) || from.isEqual(to)) {
            throw new InvalidBookingException("Invalid reservation date");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Booking))
            return false;

        if (this == obj)
            return true;

        Booking book = (Booking) obj;

        return (((from.getYear() == book.from.getYear()) && (from.getMonthValue() == book.from.getMonthValue()) &&
                (from.getDayOfMonth() == book.from.getDayOfMonth())) && ((to.getYear() == book.to.getYear()
                && to.getMonthValue() == book.to.getMonthValue()) && (to.getDayOfMonth() == book.to.getDayOfMonth())));
    }

    @Override
    public int hashCode() {
        int hash = 3;

        hash = 31 * hash + from.hashCode();
        hash += 31 * hash + to.hashCode();

        return hash;
    }
}