package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * Class bed has 1 private member and one inherited from the abstract class AbstractCommodity
 * <p>
 * as methods - getters,setters and implementation of the base class method prepare()
 */


public class Shower extends AbstractCommodity {

    private boolean isFloorHeating;

    /**
     * Parametrized constructor
     *
     * @param inventoryNumber shower's inventory number
     * @param isFloorHeating  shower's extra feature
     */

    public Shower(int inventoryNumber, boolean isFloorHeating) {
        super(inventoryNumber);
        this.isFloorHeating = isFloorHeating;
    }

    public boolean isFloorHeating() {
        return isFloorHeating;
    }

    public void setFloorHeating(boolean floorHeating) {
        isFloorHeating = floorHeating;
    }

    /**
     * {@inheritDoc}
     */
    public void prepare() {
        System.out.println("The shower is being cleaned");
    }


}
