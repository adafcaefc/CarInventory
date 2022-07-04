package Controller.Session;

import Model.RecordModel.UserModel;
import Model.RecordList.UserList;

public class ActiveSession implements java.io.Serializable
{
    private final String userName;
    private final String password;
    private final String salt;

    public ActiveSession(UserModel userRecord)
    {
        this.userName = userRecord.getUserName();
        this.password = userRecord.getPassword();
        this.salt = userRecord.getSalt();
    }

    public boolean isTheSame(UserModel userRecord)
    {
        return userRecord.getUserName().equals(userName)
                && userRecord.getPassword().equals(password)
                && userRecord.getSalt().equals(salt);
    }

    public UserModel getUser()
    {
        for (var obj : UserList.get())
        {
            UserModel userRecord = (UserModel) obj;
            if (isTheSame(userRecord))
            {
                return userRecord;
            }
        }
        return null;
    }
}
