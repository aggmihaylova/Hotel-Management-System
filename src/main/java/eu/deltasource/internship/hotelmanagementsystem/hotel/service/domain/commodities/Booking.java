package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

import eu.deltasource.internship.hotelmanagementsystem.InvalidBookingException;
import eu.deltasource.internship.hotelmanagementsystem.InvalidIDException;

import java.time.LocalDate;

/**
 * Represents a Booking for a room
 */
public class Booking implements Comparable {

    private long ID;
    private long guestID;
    private LocalDate from;
    private LocalDate to;


    /**
     * Default constructor
     */
    public Booking() {
    }

    /**
     * @param guestID
     */
    public Booking(long guestID) {
        this.guestID = guestID;
    }

    /**
     * @param guestID guest's ID
     * @param from    date
     * @param to      date
     */
    public Booking(long guestID, LocalDate from, LocalDate to) {
        this(guestID);
        this.from = from;
        this.to = to;
    }

    /**
     * Guarantees that from date is before to date
     *
     * @param from date
     * @param to   date
     */
    public boolean saveDate(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new InvalidBookingException("Invalid reservation date");
        } else {
            setFrom(from);
            setTo(to);
        }
        return true;
    }

    /**
     * @param guestID guest's ID
     * @return true if ID is valid
     */
    public boolean saveGuestID(long guestID) {

        if (guestID % 100000000L != 0) {
            this.guestID = guestID;
            return true;
        } else {
            throw new InvalidIDException("Invalid ID !");
        }
    }

    public long getGuestID() {
        return guestID;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public void setTo(LocalDate to) {
        this.to = to;
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

    @Override
    public int compareTo(Object other) {

        Booking otherDate = (Booking) other;

        int cmp = (from.getYear() - otherDate.getFrom().getYear());
        if (cmp == 0) {
            cmp = (from.getMonthValue() - otherDate.from.getMonthValue());
            if (cmp == 0) {
                cmp = (from.getDayOfMonth() - otherDate.getFrom().getDayOfMonth());
            }
        }
        return cmp;

    }
}