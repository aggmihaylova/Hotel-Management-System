package eu.deltasource.internship.hotelmanagementsystem.hotel.domain.commodities;

import eu.deltasource.internship.hotelmanagementsystem.hotel.domain.Booking;
import lombok.Getter;

/**
 * The class AbstractCommodity represents set of commodities in each room
 */
abstract public class AbstractCommodity {

    @Getter
    protected int inventoryNumber;

    /**
     * This is a default constructor
     * assigns value to the inventory number
     */
    public AbstractCommodity(int inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Booking))
            return false;

        if (this == obj)
            return true;

        AbstractCommodity abstractCommodity = (AbstractCommodity) obj;

        return this.inventoryNumber == abstractCommodity.inventoryNumber;
    }

    @Override
    public int hashCode() {
        return inventoryNumber;
    }

    /**
     * The room is being prepared !
     */
    abstract public void prepare();
}