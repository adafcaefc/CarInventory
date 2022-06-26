package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToPool;
import Model.Record.BrandRecord;
import Model.Record.DataRecord;
import Model.Record.ModelRecord;
import Model.Pool.BrandPool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelDeserializer implements DataRecordDeserializer
{
    public DataRecord deserialize(ResultSet rs) throws SQLException, DataNotBoundToPool
    {
        BrandRecord parentObj = (BrandRecord) BrandPool.get().getComponentAt(rs.getInt("parentBrandId"));
        var model = new ModelRecord(parentObj);

        model.setModelName(rs.getString("modelName"));
        model.setModelYear(rs.getInt("modelYear"));
        model.setHasSunroof(rs.getInt("hasSunroof") == 1);
        model.setDoorCount(rs.getInt("doorCount"));
        model.setSeatCount(rs.getInt("seatCount"));
        model.setFuelCapacity(rs.getDouble("fuelCapacity"));

        return model;
    }
}

