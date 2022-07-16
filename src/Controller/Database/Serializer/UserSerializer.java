package Controller.Database.Serializer;

import Model.Model.IRecordData;
import Model.Model.UserData;
import Model.List.UserList;

import java.util.HashMap;

public class UserSerializer implements IDataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IRecordData component)
    {
        var user = (UserData) component;

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
