package Model.Record.Data;

import Model.Enum.UserLevel;
import Model.Exception.InvalidData;
import Model.Record.List.ModelList;
import Model.Record.List.TransactionList;

public class VehicleData extends IData
{
    private String VIN;
    private String licensePlate;
    private String color;
    private Double mileage = 0.0;
    private Double discount = 0.2;
    private int price = 0;
    private UserData seller = null;
    private UserData buyer = null;

    public VehicleData(ModelData modelRecord) throws InvalidData
    {
        if (modelRecord == null) { return; }
        if (!ModelList.get().componentIsRegisteredAtPool(modelRecord))
        {
            throw new InvalidData("The 'model' object passed to bindToModel does not exist inside ModelPool");
        }
        modelRecord.addChild(this);
    }

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

    public UserData getSeller()
    {
        return seller;
    }

    public void setSeller(UserData seller)
    {
        this.seller = seller;
    }

    public UserData getBuyer()
    {
        return buyer;
    }

    public void setBuyer(UserData buyer)
    {
        this.buyer = buyer;
    }

    public ModelData getModel()
    {
        return (ModelData) getParent();
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

    public int getPaidAmount()
    {
        int total = 0;
        for (var obj : TransactionList.get())
        {
            TransactionData transactionData = (TransactionData) obj;
            if (transactionData.getVehicle() == this)
            {
                total += transactionData.getPaidAmount();
            }
        }
        return total;
    }

    public double getRealPrice()
    {
        return buyer.getUserLevel() == UserLevel.VIP_USER
               ? (double) (price) * (1. - discount)
               : (double) price;
    }

    public double getPercentagePaid()
    {
        if (seller == null || buyer == null) { return 0.; }
        return (double) getPaidAmount() / (double) getRealPrice();
    }
}