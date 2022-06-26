package Controller.Database.Deserializer;

import Model.Model.DataRecord;
import Model.Exception.DataNotBoundToPool;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataRecordDeserializer
{
    DataRecord deserialize(ResultSet rs) throws SQLException, DataNotBoundToPool;
}
