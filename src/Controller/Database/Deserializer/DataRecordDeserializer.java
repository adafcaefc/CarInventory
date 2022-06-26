package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToPool;
import Model.Model.DataRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataRecordDeserializer
{
    DataRecord deserialize(ResultSet rs) throws SQLException, DataNotBoundToPool;
}
