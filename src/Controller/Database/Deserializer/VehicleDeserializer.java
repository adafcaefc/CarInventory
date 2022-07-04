package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.RecordModel.IDataRecordModel;
import Model.RecordModel.ModelModel;
import Model.RecordModel.VehicleModel;
import Model.RecordList.ModelList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleDeserializer implements DataRecordDeserializer
{
    public IDataRecordModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        ModelModel parentObj = (ModelModel) ModelList.get().getComponentAt(rs.getInt("parentModelId"));
        var vehicle = new VehicleModel(parentObj);

        vehicle.setVIN(rs.getString("VIN"));
        vehicle.setLicensePlate(rs.getString("licensePlate"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setMileage(rs.getDouble("mileage"));

        return vehicle;
    }
}