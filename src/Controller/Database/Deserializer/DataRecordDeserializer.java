package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.RecordModel.IDataRecordModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataRecordDeserializer
{
    IDataRecordModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList;
}
