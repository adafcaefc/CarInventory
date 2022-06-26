package Model.Model;

import Model.Exception.DataNotBoundToPool;
import Model.Pool.BrandPool;

public class Model extends DataRecord
{
    private String modelName;
    private Integer modelYear = 0;
    private Boolean hasSunroof = false;
    private Integer doorCount = 0;
    private Integer seatCount = 0;
    private Double fuelCapacity = 0.0;

    public Model(Brand brand) throws DataNotBoundToPool
    {
        if (brand == null) { return; }
        if (!BrandPool.get().componentIsRegisteredAtPool(brand))
        {
            throw new DataNotBoundToPool("The 'brand' object passed to bindToBrand does not exist inside BrandPool");
        }
        brand.addChild(this);
    }

    public Brand getBrand()
    {
        return (Brand) getParent();
    }

    public String getModelName()
    {
        return this.modelName;
    }

    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public Integer getModelYear()
    {
        return this.modelYear;
    }

    public void setModelYear(Integer modelYear)
    {
        this.modelYear = modelYear;
    }

    public Boolean getHasSunroof()
    {
        return this.hasSunroof;
    }

    public void setHasSunroof(Boolean newSunroof)
    {
        this.hasSunroof = newSunroof;
    }

    public Integer getDoorCount()
    {
        return this.doorCount;
    }

    public void setDoorCount(Integer newDoorCount)
    {
        this.doorCount = newDoorCount;
    }

    public Integer getSeatCount()
    {
        return this.seatCount;
    }

    public void setSeatCount(Integer newSeatCount)
    {
        this.seatCount = newSeatCount;
    }

    public Double getFuelCapacity()
    {
        return this.fuelCapacity;
    }

    public void setFuelCapacity(Double newFuelCapacity)
    {
        this.fuelCapacity = newFuelCapacity;
    }
}