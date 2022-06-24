package Model.DataModel;

import java.util.GregorianCalendar;

public class SoldVehicle extends DataRecord implements Cloneable
{
    private String brandName;
    private String modelName;
    private Integer modelYear = 0;
    private Boolean hasSunroof = false;
    private Integer doorCount = 0;
    private Integer seatCount = 0;
    private Double fuelCapacity = 0.0;
    private String VIN;
    private String licensePlate;
    private String color;
    private Double mileage = 0.0;
    private GregorianCalendar dateOfSale;
    private Double paidAmount;

    public SoldVehicle() { }

    public SoldVehicle(Vehicle sourceVehicle)
    {
        VIN = sourceVehicle.getVIN();
        licensePlate = sourceVehicle.getLicensePlate();
        color = sourceVehicle.getColor();
        mileage = sourceVehicle.getMileage();

        Model sourceModel = sourceVehicle.getModel();
        modelName = sourceModel.getModelName();
        modelYear = sourceModel.getModelYear();
        hasSunroof = sourceModel.getHasSunroof();
        doorCount = sourceModel.getDoorCount();
        seatCount = sourceModel.getSeatCount();
        fuelCapacity = sourceModel.getFuelCapacity();

        brandName = sourceModel.getBrand().getBrandName();
    }

    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName) { this.brandName = brandName; }

    public String getModelName()
    {
        return modelName;
    }

    public void setModelName(String modelName) { this.modelName = modelName; }

    public Integer getModelYear()
    {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) { this.modelYear = modelYear; }

    public Boolean getHasSunroof()
    {
        return hasSunroof;
    }

    public void setHasSunroof(Boolean hasSunroof) { this.hasSunroof = hasSunroof; }

    public Integer getDoorCount()
    {
        return doorCount;
    }

    public void setDoorCount(Integer doorCount) { this.doorCount = doorCount; }

    public Integer getSeatCount()
    {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) { this.seatCount = seatCount; }

    public Double getFuelCapacity()
    {
        return fuelCapacity;
    }

    public void setFuelCapacity(Double fuelCapacity) { this.fuelCapacity = fuelCapacity; }

    public String getVIN()
    {
        return VIN;
    }

    public void setVIN(String VIN) { this.VIN = VIN; }

    public String getLicensePlate()
    {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color) { this.color = color; }

    public Double getMileage()
    {
        return mileage;
    }

    public void setMileage(Double mileage) { this.mileage = mileage; }

    public GregorianCalendar getDateOfSale()
    {
        return dateOfSale;
    }

    public void setDateOfSale(GregorianCalendar dateOfSale) { this.dateOfSale = dateOfSale; }

    public Double getPaidAmount()
    {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount)
    {
        this.paidAmount = paidAmount;
    }

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        SoldVehicle newObj = new SoldVehicle();
        newObj.brandName = brandName;
        newObj.modelName = modelName;
        newObj.modelYear = modelYear;
        newObj.hasSunroof = hasSunroof;
        newObj.doorCount = doorCount;
        newObj.seatCount = seatCount;
        newObj.fuelCapacity = fuelCapacity;
        newObj.VIN = VIN;
        newObj.licensePlate = licensePlate;
        newObj.color = color;
        newObj.mileage = mileage;
        newObj.dateOfSale = dateOfSale;
        newObj.paidAmount = paidAmount;
        return newObj;
    }
}
