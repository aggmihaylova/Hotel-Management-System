package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.MissingArgumentException;

import java.time.LocalDate;

/**
 * Represents booking for a room
 */
public class Booking {

    private long ID;
    private long guestID;
    private String guestName;
    private LocalDate from;
    private LocalDate to;

    /**
     * This is a constructor
     *
     * @param guestID guest's ID
     * @param from    date
     * @param to      date
     */
    public Booking(long ID, String guestName, long guestID, LocalDate from, LocalDate to) {
        setID(ID);
        setGuestID(guestID);
        setGuestName(guestName);
        saveDate(from, to);
    }

    /**
     * This is a constructor
     *
     * @param from date
     * @param to   date
     */
    public Booking(LocalDate from, LocalDate to) {
        saveDate(from, to);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    /**
     * Method that initializes/set the starting date
     *
     * @param from starting date
     * @throw InvalidBookingException if the date is null
     */
    public void setFrom(LocalDate from) {
        if (from == null) {
            throw new InvalidBookingException("Invalid date !");
        }

        this.from = from;
    }

    /**
     * Method that initializes/set the ending date
     *
     * @param to ending date
     * @throw InvalidBookingException if the date is null
     */
    public void setTo(LocalDate to) {
        if (to == null) {
            throw new InvalidBookingException("Invalid date !");
        }
        this.to = to;
    }

    /**
     * Method that initializes/set the guest's ID
     *
     * @param guestID guest's ID
     * @throw MissingArgumentException if the guestID is less than 10 digits
     */
    public void setGuestID(long guestID) {
        final long TEN_BILLION = 10_000_000_000L;
        if (guestID / TEN_BILLION != 0) {
            throw new MissingArgumentException("Invalid booking ID ");
        }
        this.guestID = guestID;

    }

    /**
     * Method that initializes/set the guest's name
     *
     * @param guestName guest's name
     * @throw MissingArgumentException if the name is null or empty
     */
    public void setGuestName(String guestName) {
        if (guestName == null || guestName.isEmpty()) {
            throw new MissingArgumentException("Invalid guest name");
        } else {
            this.guestName = guestName;
        }
    }

    /**
     * Method that initializes/set the booking ID
     *
     * @param ID booking ID
     * @throw MissingArgumentException() if the ID is negative
     */
    public void setID(long ID) {
        if (ID < 0) {
            throw new MissingArgumentException("Invalid booking ID !");
        }
        this.ID = ID;
    }

    /**
     * Guarantees that starting date is before ending date
     *
     * @param from date
     * @param to   date
     */
    public void saveDate(LocalDate from, LocalDate to) {
        setFrom(from);
        setTo(to);
        if (from.isAfter(to)) {
            throw new InvalidBookingException("Invalid reservation date");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

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