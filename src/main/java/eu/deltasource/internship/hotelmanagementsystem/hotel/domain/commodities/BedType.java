package eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities;

import lombok.Getter;

/**
 * Declaring new enum type used to define different types of beds
 */
@Getter
public enum BedType {

    SINGLE(1), DOUBLE(2), KING(2);

    private int size;

    BedType(int size) {
        this.size = size;
    }
}