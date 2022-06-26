package Controller.DatabaseManager.Deserializers;

import Model.DataModel.DataRecord;
import Model.DataModel.Model;
import Model.DataModel.Vehicle;
import Model.DataPool.ModelPool;
import Model.Exception.DataNotBoundToPool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements DataRecordDeserializer {
    public DataRecord deserialize(ResultSet rs) throws SQLException, DataNotBoundToPool {
        Model parentObj = (Model) ModelPool.get().getComponentAt(rs.getInt("parentModelId"));
        var vehicle = new Vehicle(parentObj);

        vehicle.setVIN(rs.getString("VIN"));
        vehicle.setLicensePlate(rs.getString("licensePlate"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setMileage(rs.getDouble("mileage"));

        return vehicle;
    }
}