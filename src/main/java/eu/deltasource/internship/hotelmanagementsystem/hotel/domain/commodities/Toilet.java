package eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities;

import lombok.Getter;

/**
 * Toilet is part of the set of commodities
 */
public class Toilet extends AbstractCommodity {

    @Getter
    private String color;

    /**
     * This is a constructor
     *
     * @param color toilet's color
     */
    public Toilet(int inventoryNumber, String color) {
        super(inventoryNumber);
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
    public void prepare() {
        System.out.println("The toilet is being cleaned");
    }
}
