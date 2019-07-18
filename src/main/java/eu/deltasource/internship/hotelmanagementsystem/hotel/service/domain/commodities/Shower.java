package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * Class bed has 2 private members and one inherited from the abstract class AbstractCommodity
 * methods - getters,setters,constructor and implemented method prepare()
 */


public class Shower extends AbstractCommodity {

    private boolean isFloorHeating;

    /**
     * @param isFloorHeating shower's extra
     */

    public Shower(boolean isFloorHeating) {
        super();
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
