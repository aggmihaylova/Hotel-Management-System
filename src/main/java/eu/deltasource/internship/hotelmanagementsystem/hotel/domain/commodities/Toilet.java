package eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities;

/**
 * Toilet is part of the set of commodities
 */
public class Toilet extends AbstractCommodity {

    private String color;

    /**
     * This is a constructor
     * * @param color toilet's color
     */
    public Toilet(int inventoryNumber, String color) {
        super(inventoryNumber);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    /**
     * {@inheritDoc}
     */
    public void prepare() {
        System.out.println("The toilet is being cleaned");
    }
}