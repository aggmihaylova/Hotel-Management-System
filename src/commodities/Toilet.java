package commodities;

public class Toilet extends AbstractCommodity {

    private boolean hasWaterSavingTechnology;
    private String color;

    public Toilet(int inventoryNumber, boolean hasWaterSavingTechnology, String color) {
        super(inventoryNumber);
        this.hasWaterSavingTechnology = hasWaterSavingTechnology;
        this.color = color;
    }

    public void prepare() {
        System.out.println("The toilet is being cleaned");
    }

    public boolean isHasWaterSavingTechnology() {
        return hasWaterSavingTechnology;
    }

    public String getColor() {
        return color;
    }

    public void setHasWaterSavingTechnology(boolean hasWaterSavingTechnology) {
        this.hasWaterSavingTechnology = hasWaterSavingTechnology;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
