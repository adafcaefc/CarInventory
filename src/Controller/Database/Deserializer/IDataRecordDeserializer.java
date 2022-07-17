package Controller.Database.Deserializer;

import Model.Exception.DataNotBoundToList;
import Model.Model.IRecordDataModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDataRecordDeserializer
{
    IRecordDataModel deserialize(ResultSet rs) throws SQLException, DataNotBoundToList;
}
