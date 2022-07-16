package Controller.Database.Deserializer;

import Model.Model.IRecordDataModel;
import Model.Model.UserDataModel;
import Model.Model.UserLevel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDeserializer implements IDataRecordDeserializer
{
    @Override
    public IRecordDataModel deserialize(ResultSet rs) throws SQLException
    {
        var user = new UserDataModel();
        user.setUserName(rs.getString("userName"));
        user.setPassword(rs.getString("password"));
        user.setSalt(rs.getString("salt"));
        user.setUserLevel(UserLevel.valueOf(rs.getInt("userLevel")));
        return user;
    }
}
