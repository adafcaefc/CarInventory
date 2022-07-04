package Controller.Database.Deserializer;

import Model.RecordModel.BrandModel;
import Model.RecordModel.IDataRecordModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BrandDeserializer implements DataRecordDeserializer
{
    @Override
    public IDataRecordModel deserialize(ResultSet rs) throws SQLException
    {
        var brand = new BrandModel();
        brand.setBrandName(rs.getString("brandName"));
        return brand;
    }
}
