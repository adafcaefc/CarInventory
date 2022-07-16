package Controller.Database.Deserializer;

import Model.Exception.InvalidData;
import Model.Record.Data.IData;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDeserializer
{
    IData deserialize(ResultSet rs) throws SQLException, InvalidData;
}
