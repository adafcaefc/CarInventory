package Controller.DatabaseManager.Deserializers;

import Model.DataModel.DataRecord;
import Model.DataModel.User;
import Model.DataModel.UserLevel;

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
