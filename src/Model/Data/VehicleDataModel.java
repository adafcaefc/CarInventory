package Model.Data;

import Model.Exception.DataNotBoundToList;
import Model.List.ModelList;

public class VehicleDataModel extends IRecordDataModel
{
    private String VIN;
    private String licensePlate;
    private String color;
    private Double mileage = 0.0;

    public VehicleDataModel(ModelDataModel modelRecord) throws DataNotBoundToList
    {
        if (modelRecord == null) { return; }
        if (!ModelList.get().componentIsRegisteredAtPool(modelRecord))
        {
            throw new DataNotBoundToList("The 'model' object passed to bindToModel does not exist inside ModelPool");
        }
        modelRecord.addChild(this);
    }

    public ModelDataModel getModel()
    {
        return (ModelDataModel) getParent();
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