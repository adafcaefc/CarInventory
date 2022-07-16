package Model.Model;

import Model.Exception.DataNotBoundToList;
import Model.ArraySingleton.BrandArraySingleton;

public class ModelDataModel extends IRecordDataModel
{
    private String modelName;
    private Integer modelYear = 0;
    private Boolean hasSunroof = false;
    private Integer doorCount = 0;
    private Integer seatCount = 0;
    private Double fuelCapacity = 0.0;

    public ModelDataModel(BrandDataModel brandRecord) throws DataNotBoundToList
    {
        if (brandRecord == null) { return; }
        if (!BrandArraySingleton.get().componentIsRegisteredAtPool(brandRecord))
        {
            throw new DataNotBoundToList("The 'brand' object passed to bindToBrand does not exist inside BrandPool");
        }
        brandRecord.addChild(this);
    }

    public BrandDataModel getBrand()
    {
        return (BrandDataModel) getParent();
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