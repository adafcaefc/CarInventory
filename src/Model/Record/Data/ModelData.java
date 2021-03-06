package Model.Record.Data;

import Model.Exception.InvalidData;
import Model.Record.List.BrandList;

public class ModelData extends IData
{
    private String modelName;
    private Integer modelYear = 0;
    private Boolean hasSunroof = false;
    private Integer doorCount = 0;
    private Integer seatCount = 0;
    private Double fuelCapacity = 0.0;

    public ModelData(BrandData brandRecord) throws InvalidData
    {
        if (brandRecord == null) { return; }
        if (!BrandList.get().componentIsRegisteredAtPool(brandRecord))
        {
            throw new InvalidData("The 'brand' object passed to bindToBrand does not exist inside BrandPool");
        }
        brandRecord.addChild(this);
    }

    public BrandData getBrand()
    {
        return (BrandData) getParent();
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