package Model.Record;

import Model.Exception.DataNotBoundToPool;
import Model.Pool.ModelPool;

public class VehicleRecord extends DataRecord
{
    private String VIN;
    private String licensePlate;
    private String color;
    private Double mileage = 0.0;

    public VehicleRecord(ModelRecord modelRecord) throws DataNotBoundToPool
    {
        if (modelRecord == null) { return; }
        if (!ModelPool.get().componentIsRegisteredAtPool(modelRecord))
        {
            throw new DataNotBoundToPool("The 'model' object passed to bindToModel does not exist inside ModelPool");
        }
        modelRecord.addChild(this);
    }

    public ModelRecord getModel()
    {
        return (ModelRecord) getParent();
    }

    public String getVIN()
    {
        return this.VIN;
    }

    public void setVIN(String numVIN)
    {
        this.VIN = numVIN;
    }

    public String getLicensePlate()
    {
        return this.licensePlate;
    }

    public void setLicensePlate(String newLicensePlate)
    {
        this.licensePlate = newLicensePlate;
    }

    public String getColor()
    {
        return this.color;
    }

    public void setColor(String newColor)
    {
        this.color = newColor;
    }

    public Double getMileage()
    {
        return this.mileage;
    }

    public void setMileage(Double newMillage)
    {
        this.mileage = newMillage;
    }
}