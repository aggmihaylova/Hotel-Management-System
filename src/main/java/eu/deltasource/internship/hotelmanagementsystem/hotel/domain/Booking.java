package eu.deltasource.internship.hotelmanagementsystem.hotel.domain;

import eu.deltasource.internship.hotelmanagementsystem.hotel.exceptions.InvalidBookingException;

import java.time.LocalDate;

/**
 * Represents booking for a room
 */
public class Booking {

    //  private long ID;
    private long guestID;
    private LocalDate from;
    private LocalDate to;

    /**
     * This is a constructor
     *
     * @param guestID guest's ID
     * @param from    date
     * @param to      date
     */
    public Booking(long guestID, LocalDate from, LocalDate to) {
        this.guestID = guestID;
        saveDate(from, to);
    }

    public Booking(LocalDate from, LocalDate to) {
        saveDate(from, to);
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


    /**
     * Checks if from date is null
     *
     * @param from starting date
     */
    public void setFrom(LocalDate from) {
        if (from == null)
            throw new InvalidBookingException("Invalid date !");
        this.from = from;
    }

    /**
     * Checks if to date is null
     *
     * @param to ending date
     */
    public void setTo(LocalDate to) {
        if (to == null)
            throw new InvalidBookingException("Invalid date !");
        this.to = to;
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