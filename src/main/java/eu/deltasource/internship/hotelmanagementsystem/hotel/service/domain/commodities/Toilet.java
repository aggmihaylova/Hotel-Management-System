package eu.deltasource.internship.hotelmanagementsystem.hotel.service.domain.commodities;

/**
 * Toilet is part of the set of commodities
 */
public class Toilet extends AbstractCommodity {

    private boolean hasWaterSavingTechnology;
    private String color;

    /**
     * Parametrized constructor
     *
     * @param hasWaterSavingTechnology toilet's extra
     * @param color                    toilet's color
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

    /**
     * {@inheritDoc}
     */
    public void prepare() {
        System.out.println("The toilet is being cleaned");
    }
}
