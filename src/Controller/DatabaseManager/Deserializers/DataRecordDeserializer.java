package Controller.DatabaseManager.Deserializers;

import Model.DataModel.DataRecord;
import Model.Exception.DataNotBoundToPool;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataRecordDeserializer
{
    DataRecord deserialize(ResultSet rs) throws SQLException, DataNotBoundToPool;
}
