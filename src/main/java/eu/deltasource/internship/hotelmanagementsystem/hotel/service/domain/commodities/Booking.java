package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;


import java.time.LocalDate;

/**
 * The class Booking is consisted of 4 private members,
 * implements the interface Comparable
 * has getters,setters
 * method that removes existing booking
 */

public class Booking implements Comparable {


    private long guestID;
    private LocalDate from;
    private LocalDate to;


    /**
     * Default constructor
     */

    public Booking() {

    }

    /**
     * Parameterized constructor
     *
     * @param guestID guest ID
     */

    public Booking(long guestID) {
        this.guestID = guestID;
    }

    /**
     * Overloaded Parameterized constructor
     *
     * @param ID   guest ID
     * @param from from date
     * @param to   to date
     */

    public Booking(long ID, LocalDate from, LocalDate to) {
        this(ID);
        this.from = from;
        this.to = to;

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

    public long getGuestID() {
        return guestID;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setGuestID(long guestID) {
        this.guestID = guestID;
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