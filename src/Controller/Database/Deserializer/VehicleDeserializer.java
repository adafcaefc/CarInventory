package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.ArraySingleton.ModelArraySingleton;
import Model.ArraySingleton.UserArraySingleton;
import Model.Model.IRecordDataModel;
import Model.Model.ModelDataModel;
import Model.Model.UserDataModel;
import Model.Model.VehicleDataModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements IDataRecordDeserializer
{
    public IRecordDataModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        ModelDataModel parentObj = (ModelDataModel) ModelArraySingleton.get().getComponentAt(rs.getInt("parentModelId"));
        var vehicle = new VehicleDataModel(parentObj);

        vehicle.setVIN(rs.getString("VIN"));
        vehicle.setLicensePlate(rs.getString("licensePlate"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setMileage(rs.getDouble("mileage"));

        vehicle.setDiscount(rs.getDouble("discount"));
        vehicle.setPrice(rs.getInt("price"));

        int buyerObjIndex = rs.getInt("buyerUserId");
        if (buyerObjIndex != -1) { vehicle.setBuyer((UserDataModel) UserArraySingleton.get().getComponentAt(buyerObjIndex)); }

        int sellerObjIndex = rs.getInt("sellerUserId");
        if (sellerObjIndex != -1) { vehicle.setSeller((UserDataModel) UserArraySingleton.get().getComponentAt(sellerObjIndex)); }

        return vehicle;
    }
}