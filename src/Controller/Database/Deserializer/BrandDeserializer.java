package Controller.Database.Deserializer;

import Model.Data.BrandDataModel;
import Model.Data.IRecordDataModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDeserializer implements IDataRecordDeserializer
{
    @Override
    public IRecordDataModel deserialize(ResultSet rs) throws SQLException
    {
        var brand = new BrandDataModel();
        brand.setBrandName(rs.getString("brandName"));
        return brand;
    }
}
