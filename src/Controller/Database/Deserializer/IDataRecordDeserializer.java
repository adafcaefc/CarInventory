package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.Data.IRecordData;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDataRecordDeserializer
{
    IRecordData deserialize(ResultSet rs) throws SQLException, DataNotBoundToList;
}
