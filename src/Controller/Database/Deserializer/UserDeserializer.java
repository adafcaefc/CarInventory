package Controller.Database.Deserializer;

import Model.Model.DataRecord;
import Model.Model.User;
import Model.Model.UserLevel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDeserializer implements DataRecordDeserializer
{
    @Override
    public DataRecord deserialize(ResultSet rs) throws SQLException
    {
        var user = new User();
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getString("salt"));
        user.setUserLevel(UserLevel.valueOf(rs.getInt("userLevel")));
        return user;
    }
}
