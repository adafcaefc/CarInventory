package Controller.Database.Serializer;

import Model.Record.Data.IData;
import Model.Record.Data.UserData;
import Model.Record.List.UserList;

import java.util.HashMap;

public class UserSerializer implements ISerializer
{
    @Override
    public HashMap<String, String> serialize(IData component)
    {
        var user = (UserData) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = UserList.get().getIndexForComponent(user);

        map.put("userId", String.valueOf(objIndex));
        map.put("userName", user.getUserName());
        map.put("password", user.getPassword());
        map.put("salt", user.getSalt());
        map.put("userLevel", String.valueOf(user.getUserLevel().getValue()));

        return map;
    }
}
