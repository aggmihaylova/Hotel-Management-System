package commodities;

public class Shower extends AbstractCommodity {

    private boolean isFloorHeating;

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

    public void prepare() {
        System.out.println("The shower is being cleaned");
    }


}
