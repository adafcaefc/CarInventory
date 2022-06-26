package Controller.Database.Deserializer;

import Model.Model.DataRecord;
import Model.Model.Model;
import Model.Model.Vehicle;
import Model.Exception.DataNotBoundToPool;
import Model.Pool.ModelPool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements DataRecordDeserializer
{
    public DataRecord deserialize(ResultSet rs) throws SQLException, DataNotBoundToPool
    {
        Model parentObj = (Model) ModelPool.get().getComponentAt(rs.getInt("parentModelId"));
        var vehicle = new Vehicle(parentObj);

        vehicle.setVIN(rs.getString("VIN"));
        vehicle.setLicensePlate(rs.getString("licensePlate"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setMileage(rs.getDouble("mileage"));

        return vehicle;
    }
}