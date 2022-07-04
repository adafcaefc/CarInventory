package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.RecordModel.DataRecordModel;
import Model.RecordModel.ModelRecordModel;
import Model.RecordModel.VehicleRecordModel;
import Model.RecordList.ModelList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements DataRecordDeserializer
{
    public DataRecordModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        ModelRecordModel parentObj = (ModelRecordModel) ModelList.get().getComponentAt(rs.getInt("parentModelId"));
        var vehicle = new VehicleRecordModel(parentObj);

        vehicle.setVIN(rs.getString("VIN"));
        vehicle.setLicensePlate(rs.getString("licensePlate"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setMileage(rs.getDouble("mileage"));

        return vehicle;
    }
}