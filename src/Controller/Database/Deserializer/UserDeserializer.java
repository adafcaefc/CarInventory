package Controller.Database.Deserializer;

import Model.Enum.UserLevel;
import Model.Record.Data.IData;
import Model.Record.Data.UserData;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDeserializer implements IDeserializer
{
    @Override
    public IData deserialize(ResultSet rs) throws SQLException
    {
        var user = new UserData();
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getString("salt"));
        user.setUserLevel(UserLevel.valueOf(rs.getInt("userLevel")));
        return user;
    }
}
