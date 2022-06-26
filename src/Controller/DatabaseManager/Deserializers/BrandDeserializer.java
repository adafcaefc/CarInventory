package Controller.DatabaseManager.Deserializers;

import Model.DataModel.Brand;
import Model.DataModel.DataRecord;

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
