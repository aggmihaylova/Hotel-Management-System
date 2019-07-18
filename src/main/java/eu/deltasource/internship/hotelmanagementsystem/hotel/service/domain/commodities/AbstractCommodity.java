package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * The class AbstractCommodity represents set of commodities in each room
 */
abstract public class AbstractCommodity {

    private static int COUNTER;
    protected int globalInventoryCounter;

    /**
     * Default constructor
     * assigns value to the inventory number
     */
    public AbstractCommodity() {
        globalInventoryCounter = ++COUNTER;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Booking))
            return false;

        if (this == obj)
            return true;

        AbstractCommodity abstractCommodity = (AbstractCommodity) obj;

        return this.globalInventoryCounter == abstractCommodity.globalInventoryCounter;
    }

    @Override
    public int hashCode() {
        int hash = 3;

        hash *= globalInventoryCounter;

        return hash;

    }

    /**
     * The room is being prepared !
     */
    abstract public void prepare();
}
