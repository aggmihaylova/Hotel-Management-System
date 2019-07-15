package main.hotel.service.domain.commodities;

/**
 * Class bed has 2 private members and one inherited from the abstract class AbstractCommodity
 * <p>
 * as methods - getters,setters and implementation of the base class method prepare()
 */

public class Toilet extends AbstractCommodity {

    private boolean hasWaterSavingTechnology;
    private String color;

    /**
     * Parametrized constructor
     *
     * @param inventoryNumber          Toilet's inventory number
     * @param hasWaterSavingTechnology Toilet's extra feature
     * @param color                    Toilet's color
     */

    public Toilet(int inventoryNumber, boolean hasWaterSavingTechnology, String color) {
        super(inventoryNumber);
        this.hasWaterSavingTechnology = hasWaterSavingTechnology;
        this.color = color;
    }

    /**
     * {@inheritDoc}
     */
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
