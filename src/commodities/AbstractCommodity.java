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

    abstract public void prepare();


}
