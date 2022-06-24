package Model.DataModel;

import Model.Exception.DataNotBoundToPool;
import Model.DataPool.ModelPool;

public class Vehicle extends DataRecord
{
    private String VIN;
    private String licensePlate;
    private String color;
    private Double mileage = 0.0;

    public Vehicle(Model model) throws DataNotBoundToPool
    {
        if (model == null) { return; }
        if (!ModelPool.get().componentIsRegisteredAtPool(model))
        {
            throw new DataNotBoundToPool("The 'model' object passed to bindToModel does not exist inside ModelPool");
        }
        model.addChild(this);
    }

    public Model getModel()
    {
        return (Model) getParent();
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