package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * Shower is part of the set of commodities
 */
public class Shower extends AbstractCommodity {

    private boolean hasFloorHeating;

    /**
     * Parametrized constructor
     *
     * @param hasFloorHeating shower's extra
     */
    public Shower(boolean hasFloorHeating) {
        super();
        this.hasFloorHeating = hasFloorHeating;
    }

    public boolean HasFloorHeating() {
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
