package Controller.Database.Deserializer;

import Model.Exception.InvalidData;
import Model.Record.Data.IData;
import Model.Record.Data.ModelData;
import Model.Record.Data.UserData;
import Model.Record.Data.VehicleData;
import Model.Record.List.ModelList;
import Model.Record.List.UserList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements IDeserializer
{
    public IData deserialize(ResultSet rs) throws SQLException, InvalidData
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