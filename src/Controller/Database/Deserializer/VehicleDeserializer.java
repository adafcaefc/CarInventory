package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.Data.IRecordDataModel;
import Model.Data.ModelDataModel;
import Model.Data.VehicleDataModel;
import Model.List.ModelList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements IDataRecordDeserializer
{
    public IRecordDataModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        ModelDataModel parentObj = (ModelDataModel) ModelList.get().getComponentAt(rs.getInt("parentModelId"));
        var vehicle = new VehicleDataModel(parentObj);

        vehicle.setVIN(rs.getString("VIN"));
        vehicle.setLicensePlate(rs.getString("licensePlate"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setMileage(rs.getDouble("mileage"));

        return vehicle;
    }
}