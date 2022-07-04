package Controller.Database.Deserializer;

import Model.RecordModel.BrandRecordModel;
import Model.RecordModel.DataRecordModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDeserializer implements DataRecordDeserializer
{
    @Override
    public DataRecordModel deserialize(ResultSet rs) throws SQLException
    {
        var brand = new BrandRecordModel();
        brand.setBrandName(rs.getString("brandName"));
        return brand;
    }
}
