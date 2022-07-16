package Controller.Database.Deserializer;

import Model.Model.BrandData;
import Model.Model.IRecordData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDeserializer implements IDataRecordDeserializer
{
    @Override
    public IRecordData deserialize(ResultSet rs) throws SQLException
    {
        var brand = new BrandData();
        brand.setBrandName(rs.getString("brandName"));
        return brand;
    }
}
