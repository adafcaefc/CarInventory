package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToPool;
import Model.Record.DataRecord;
import Model.Record.ModelRecord;
import Model.Record.VehicleRecord;
import Model.Pool.ModelPool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements DataRecordDeserializer
{
    public DataRecord deserialize(ResultSet rs) throws SQLException, DataNotBoundToPool
    {
        ModelRecord parentObj = (ModelRecord) ModelPool.get().getComponentAt(rs.getInt("parentModelId"));
        var vehicle = new VehicleRecord(parentObj);

        vehicle.setVIN(rs.getString("VIN"));
        vehicle.setLicensePlate(rs.getString("licensePlate"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setMileage(rs.getDouble("mileage"));

        return vehicle;
    }
}