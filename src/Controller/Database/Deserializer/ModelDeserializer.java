package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.ArraySingleton.BrandArraySingleton;
import Model.Model.BrandDataModel;
import Model.Model.IRecordDataModel;
import Model.Model.ModelDataModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelDeserializer implements IDataRecordDeserializer
{
    public IRecordDataModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        BrandDataModel parentObj = (BrandDataModel) BrandArraySingleton.get().getComponentAt(rs.getInt("parentBrandId"));
        var model = new ModelDataModel(parentObj);

        model.setModelName(rs.getString("modelName"));
        model.setModelYear(rs.getInt("modelYear"));
        model.setHasSunroof(rs.getInt("hasSunroof") == 1);
        model.setDoorCount(rs.getInt("doorCount"));
        model.setSeatCount(rs.getInt("seatCount"));
        model.setFuelCapacity(rs.getDouble("fuelCapacity"));

        return model;
    }
}

