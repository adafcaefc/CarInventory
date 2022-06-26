package Controller.Database.Deserializer;

import Model.Record.DataRecord;
import Model.Record.UserRecord;
import Model.Record.UserLevel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDeserializer implements DataRecordDeserializer
{
    @Override
    public DataRecord deserialize(ResultSet rs) throws SQLException
    {
        var user = new UserRecord();
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getString("salt"));
        user.setUserLevel(UserLevel.valueOf(rs.getInt("userLevel")));
        return user;
    }
}
