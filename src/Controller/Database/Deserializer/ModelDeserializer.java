package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.RecordModel.BrandRecordModel;
import Model.RecordModel.DataRecordModel;
import Model.RecordModel.ModelRecordModel;
import Model.RecordList.BrandList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelDeserializer implements DataRecordDeserializer
{
    public DataRecordModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        BrandRecordModel parentObj = (BrandRecordModel) BrandList.get().getComponentAt(rs.getInt("parentBrandId"));
        var model = new ModelRecordModel(parentObj);

        model.setModelName(rs.getString("modelName"));
        model.setModelYear(rs.getInt("modelYear"));
        model.setHasSunroof(rs.getInt("hasSunroof") == 1);
        model.setDoorCount(rs.getInt("doorCount"));
        model.setSeatCount(rs.getInt("seatCount"));
        model.setFuelCapacity(rs.getDouble("fuelCapacity"));

        return model;
    }
}

