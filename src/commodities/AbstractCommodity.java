package commodities;

abstract public class AbstractCommodity {

    private int inventoryNumber;


    public AbstractCommodity(int inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(int inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Booking))
            return false;

        if (this == obj)
            return true;

        AbstractCommodity abstractCommodity = (AbstractCommodity) obj;

        return this.inventoryNumber == abstractCommodity.getInventoryNumber();

    }

    @Override
    public int hashCode() {
        int hash = 3;

        hash *= this.inventoryNumber;

        return hash;

    }

    abstract public void prepare();


}
