package Controller.Database.Deserializer;

import Model.Exception.InvalidData;
import Model.Record.Data.BrandData;
import Model.Record.Data.IData;
import Model.Record.Data.ModelData;
import Model.Record.List.BrandList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelDeserializer implements IDeserializer
{
    public IData deserialize(ResultSet rs) throws SQLException, InvalidData
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

