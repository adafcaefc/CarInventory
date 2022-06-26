package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToPool;
import Model.Record.DataRecord;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataRecordDeserializer
{
    DataRecord deserialize(ResultSet rs) throws SQLException, DataNotBoundToPool;
}
