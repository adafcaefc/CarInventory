package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.RecordModel.BrandModel;
import Model.RecordModel.IDataRecordModel;
import Model.RecordModel.ModelModel;
import Model.RecordList.BrandList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelDeserializer implements DataRecordDeserializer
{
    public IDataRecordModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        BrandModel parentObj = (BrandModel) BrandList.get().getComponentAt(rs.getInt("parentBrandId"));
        var model = new ModelModel(parentObj);

        model.setModelName(rs.getString("modelName"));
        model.setModelYear(rs.getInt("modelYear"));
        model.setHasSunroof(rs.getInt("hasSunroof") == 1);
        model.setDoorCount(rs.getInt("doorCount"));
        model.setSeatCount(rs.getInt("seatCount"));
        model.setFuelCapacity(rs.getDouble("fuelCapacity"));

        return model;
    }
}

