package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * Declaring new enum type used to define different types of beds
 */
public enum BedType {

    SINGLE(1), DOUBLE(2), TRIPLE(3), QUAD(4), KING(5);

    private int size;

    BedType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
