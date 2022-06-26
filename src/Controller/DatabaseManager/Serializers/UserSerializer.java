package Controller.DatabaseManager.Serializers;

import Model.DataModel.DataRecord;
import Model.DataModel.User;
import Model.DataPool.UserPool;

import java.util.HashMap;

public class UserSerializer implements DataRecordSerializer {
    @Override
    public HashMap<String, String> serialize(DataRecord component) {
        var user = (User) component;

        HashMap<String, String> map = new HashMap<>();
        int objIndex = UserPool.get().getIndexForComponent(user);

        map.put("userId", String.valueOf(objIndex));
        map.put("salt", user.getSalt());
        map.put("userLevel", String.valueOf(user.getUserLevel().getValue()));
        map.put("userName", user.getUserName());
        map.put("password", user.getPassword());

        return map;
    }
}
