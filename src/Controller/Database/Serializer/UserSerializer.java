package Controller.Database.Serializer;

import Model.RecordModel.IDataRecordModel;
import Model.RecordModel.UserModel;
import Model.RecordList.UserList;

import java.util.HashMap;

public class UserSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IDataRecordModel component)
    {
        var user = (UserModel) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = UserList.get().getIndexForComponent(user);

        map.put("userId", String.valueOf(objIndex));
        map.put("userName", user.getUserName());
        map.put("password", user.getPassword());
        map.put("salt", user.getSalt());
        map .put("userLevel", String.valueOf(user.getUserLevel().getValue()));

        return map;
    }
}
