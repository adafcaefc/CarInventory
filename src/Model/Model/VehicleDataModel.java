package Model.Model;

import Model.Exception.DataNotBoundToList;
import Model.List.ModelList;

public class VehicleDataModel extends IRecordDataModel
{
    private String VIN;
    private String licensePlate;
    private String color;
    private Double mileage = 0.0;
    private Double discount = 0.2;
    private int price = 0;
    private UserDataModel seller = null;
    private UserDataModel buyer = null;

    public Double getDiscount()
    {
        return discount;
    }

    public void setDiscount(Double discount)
    {
        this.discount = discount;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public UserDataModel getSeller()
    {
        return seller;
    }

    public void setSeller(UserDataModel seller)
    {
        this.seller = seller;
    }

    public UserDataModel getBuyer()
    {
        return buyer;
    }

    public void setBuyer(UserDataModel buyer)
    {
        this.buyer = buyer;
    }

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