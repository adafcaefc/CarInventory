package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.Data.BrandData;
import Model.Data.IRecordData;
import Model.Data.ModelData;
import Model.List.BrandList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelDeserializer implements DataRecordDeserializer
{
    public IRecordData deserialize(ResultSet rs) throws SQLException, DataNotBoundToList
    {
        BrandData parentObj = (BrandData) BrandList.get().getComponentAt(rs.getInt("parentBrandId"));
        var model = new ModelData(parentObj);

        model.setModelName(rs.getString("modelName"));
        model.setModelYear(rs.getInt("modelYear"));
        model.setHasSunroof(rs.getInt("hasSunroof") == 1);
        model.setDoorCount(rs.getInt("doorCount"));
        model.setSeatCount(rs.getInt("seatCount"));
        model.setFuelCapacity(rs.getDouble("fuelCapacity"));

        return model;
    }
}

