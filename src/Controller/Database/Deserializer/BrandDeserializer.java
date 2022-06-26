package Controller.Database.Deserializer;

import Model.Model.Brand;
import Model.Model.DataRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDeserializer implements DataRecordDeserializer
{
    @Override
    public DataRecord deserialize(ResultSet rs) throws SQLException
    {
        var brand = new Brand();
        brand.setBrandName(rs.getString("brandName"));
        return brand;
    }
}
