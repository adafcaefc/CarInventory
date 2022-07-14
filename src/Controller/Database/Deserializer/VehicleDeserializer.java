package Controller.Database.Deserializer;

import Model.Data.UserData;
import Model.Exception.DataNotBoundToList;
import Model.Data.IRecordData;
import Model.Data.ModelData;
import Model.Data.VehicleData;
import Model.List.ModelList;
import Model.List.UserList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements IDataRecordDeserializer
{
    public IRecordData deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        ModelData parentObj = (ModelData) ModelList.get().getComponentAt(rs.getInt("parentModelId"));
        var vehicle = new VehicleData(parentObj);

        vehicle.setVIN(rs.getString("VIN"));
        vehicle.setLicensePlate(rs.getString("licensePlate"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setMileage(rs.getDouble("mileage"));

        vehicle.setDiscount(rs.getDouble("discount"));
        vehicle.setPrice(rs.getInt("price"));

        int buyerObjIndex = rs.getInt("buyerUserId");
        if (buyerObjIndex != -1) { vehicle.setBuyer((UserData) UserList.get().getComponentAt(buyerObjIndex)); }

        int sellerObjIndex = rs.getInt("sellerUserId");
        if (sellerObjIndex != -1) { vehicle.setSeller((UserData) UserList.get().getComponentAt(sellerObjIndex)); }

        return vehicle;
    }
}