package main.hotel.service.domain.commodities;

import main.java.Room;

import java.time.LocalDate;

/**
 * The class Booking is consisted of 4 private members,
 * implements the interface Comparable
 * has getters,setters
 * method that removes existing booking
 */

public class Booking implements Comparable {


    private long ID;
    private LocalDate from;
    private LocalDate to;
    private String guestName;

    /**
     * Default constructor
     */

    public Booking() {

    }

    /**
     * Parameterized constructor
     *
     * @param ID        guest ID
     * @param guestName guest name
     */

    public Booking(long ID, String guestName) {
        this.ID = ID;
        this.guestName = guestName;
    }

    /**
     * Overloaded Parameterized constructor
     *
     * @param ID        guest ID
     * @param from      from date
     * @param to        to date
     * @param guestName guest Name
     */

    public Booking(long ID, LocalDate from, LocalDate to, String guestName) {
        this(ID, guestName);
        this.from = from;
        this.to = to;

    }

    public long getID() {
        return ID;
    }

    public String getGuestName() {
        return guestName;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * Guarantee that from Date is before to Date
     *
     * @param from date
     * @param to   date
     */

    public void saveDate(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            System.out.println("Invalid reservation date ");
        } else {
            setFrom(from);
            setTo(to);
        }
    }

    /**
     * This method removes some booking and adds new
     *
     * @param newBooking - new reservation
     * @param bookedRoom - room with that booking
     * @throws Exception - if the removed booking does not exist
     */


    public void changeReservationDate(Booking newBooking, Room bookedRoom) throws Exception {

        try {
            bookedRoom.removeBooking(this);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }

        this.from = newBooking.from;
        this.to = newBooking.to;
        this.ID = newBooking.ID;
        this.guestName = newBooking.guestName;

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

        return (((from.getYear() == book.getFrom().getYear()) && (from.getMonthValue() == book.getFrom().getMonthValue()) &&
                (from.getDayOfMonth() == book.getFrom().getDayOfMonth())) && ((to.getYear() == book.getTo().getYear() && to.getMonthValue() == book.getTo().getMonthValue()) &&
                (to.getDayOfMonth() == book.getTo().getDayOfMonth())));

    }

    @Override
    public int hashCode() {
        int hash = 3;

        hash = 31 * hash + from.hashCode();
        hash = 31 * hash + to.hashCode();

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