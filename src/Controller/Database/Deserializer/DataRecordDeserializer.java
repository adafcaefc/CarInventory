package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.RecordModel.DataRecordModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataRecordDeserializer
{
    DataRecordModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList;
}
