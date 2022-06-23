package Model.DataModel;
public class Vehicle extends DataRecord
{
    private String VIN;
    private String licensePlate;
    private String color;
    private Double mileage = 0.0;

    public String getVIN()
    {
        return this.VIN;
    }

    public void setVIN(String newVIN)
    {
        this.VIN = newVIN;
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