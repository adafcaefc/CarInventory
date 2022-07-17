package Controller.Database.Deserializer;

import Model.Record.Data.BrandData;
import Model.Record.Data.IData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDeserializer implements IDeserializer
{
    @Override
    public IData deserialize(ResultSet rs) throws SQLException
    {
        var brand = new BrandData();
        brand.setBrandName(rs.getString("brandName"));
        return brand;
    }
}
