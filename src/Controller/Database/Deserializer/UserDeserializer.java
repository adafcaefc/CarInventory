package Controller.Database.Deserializer;

import Model.RecordModel.DataRecordModel;
import Model.RecordModel.UserRecordModel;
import Model.RecordModel.UserLevel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDeserializer implements DataRecordDeserializer
{
    @Override
    public DataRecordModel deserialize(ResultSet rs) throws SQLException
    {
        var user = new UserRecordModel();
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getString("salt"));
        user.setUserLevel(UserLevel.valueOf(rs.getInt("userLevel")));
        return user;
    }
}
