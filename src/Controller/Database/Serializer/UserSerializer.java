package Controller.Database.Serializer;

import Model.ArraySingleton.UserArraySingleton;
import Model.Model.IRecordDataModel;
import Model.Model.UserDataModel;

import java.util.HashMap;

public class UserSerializer implements IDataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(IRecordDataModel component)
    {
        var user = (UserDataModel) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = UserArraySingleton.get().getIndexForComponent(user);

        map.put("userId", String.valueOf(objIndex));
        map.put("userName", user.getUserName());
        map.put("password", user.getPassword());
        map.put("salt", user.getSalt());
        map.put("userLevel", String.valueOf(user.getUserLevel().getValue()));

        return map;
    }
}
