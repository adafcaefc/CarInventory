package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.Data.IRecordData;
import Model.Data.ModelData;
import Model.Data.VehicleData;
import Model.List.ModelList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements DataRecordDeserializer
{
    public IRecordData deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        ModelData parentObj = (ModelData) ModelList.get().getComponentAt(rs.getInt("parentModelId"));
        var vehicle = new VehicleData(parentObj);

        vehicle.setVIN(rs.getString("VIN"));
        vehicle.setLicensePlate(rs.getString("licensePlate"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setMileage(rs.getDouble("mileage"));

        return vehicle;
    }
}