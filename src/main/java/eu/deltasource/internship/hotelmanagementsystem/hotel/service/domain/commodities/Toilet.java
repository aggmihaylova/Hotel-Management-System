package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * Class bed has 2 private members and one inherited from the abstract class AbstractCommodity
 * methods - getters,setters,constructor and implemented method prepare()
 */

public class Toilet extends AbstractCommodity {

    private boolean hasWaterSavingTechnology;
    private String color;

    /**
     *
     * @param hasWaterSavingTechnology toilet's extra
     * @param color toilet's color
     */

    public Toilet(boolean hasWaterSavingTechnology, String color) {
        super();
        this.hasWaterSavingTechnology = hasWaterSavingTechnology;
        this.color = color;
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


    /**
     * {@inheritDoc}
     */
    public void prepare() {
        System.out.println("The toilet is being cleaned");
    }


}
