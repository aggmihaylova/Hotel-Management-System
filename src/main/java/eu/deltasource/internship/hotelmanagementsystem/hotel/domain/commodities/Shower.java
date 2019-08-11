package eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities;

/**
 * Shower is part of the set of commodities
 */
public class Shower extends AbstractCommodity {

    private boolean hasFloorHeating;

    /**
     * This is a constructor
     *
     * @param hasFloorHeating shower's extra
     */
    public Shower(int inventoryNumber, boolean hasFloorHeating) {
        super(inventoryNumber);
        this.hasFloorHeating = hasFloorHeating;
    }

    public boolean hasFloorHeating() {
        return hasFloorHeating;
    }

    public void turnOffFloorHeating() {
        hasFloorHeating = false;
    }

    public void turnOnFloorHeating() {
        hasFloorHeating = true;
    }

    /**
     * {@inheritDoc}
     */
    public void prepare() {
        System.out.println("The shower is being cleaned");
    }
}