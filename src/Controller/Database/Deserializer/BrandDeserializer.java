package Controller.Database.Deserializer;

import Model.Record.BrandRecord;
import Model.Record.DataRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDeserializer implements DataRecordDeserializer
{
    @Override
    public DataRecord deserialize(ResultSet rs) throws SQLException
    {
        var brand = new BrandRecord();
        brand.setBrandName(rs.getString("brandName"));
        return brand;
    }
}
