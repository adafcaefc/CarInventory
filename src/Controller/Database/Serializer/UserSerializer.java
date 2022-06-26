package Controller.Database.Serializer;

import Model.Record.DataRecord;
import Model.Record.UserRecord;
import Model.Pool.UserPool;

import java.util.HashMap;

public class UserSerializer implements DataRecordSerializer
{
    @Override
    public HashMap<String, String> serialize(DataRecord component)
    {
        var user = (UserRecord) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = UserPool.get().getIndexForComponent(user);

        map.put("userId", String.valueOf(objIndex));
        map.put("userName", user.getUserName());
        map.put("password", user.getPassword());
        map.put("salt", user.getSalt());
        map .put("userLevel", String.valueOf(user.getUserLevel().getValue()));

        return map;
    }
}
