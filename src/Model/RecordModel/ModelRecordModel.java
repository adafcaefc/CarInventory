package Model.RecordModel;

import Model.Exception.DataNotBoundToList;
import Model.RecordList.BrandList;

public class ModelRecordModel extends DataRecordModel
{
    private String modelName;
    private Integer modelYear = 0;
    private Boolean hasSunroof = false;
    private Integer doorCount = 0;
    private Integer seatCount = 0;
    private Double fuelCapacity = 0.0;

    public ModelRecordModel(BrandRecordModel brandRecord) throws DataNotBoundToList
    {
        if (brandRecord == null) { return; }
        if (!BrandList.get().componentIsRegisteredAtPool(brandRecord))
        {
            throw new DataNotBoundToList("The 'brand' object passed to bindToBrand does not exist inside BrandPool");
        }
        brandRecord.addChild(this);
    }

    public BrandRecordModel getBrand()
    {
        return (BrandRecordModel) getParent();
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