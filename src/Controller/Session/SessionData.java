package Controller.Session;

import Model.Data.UserDataModel;
import Model.List.UserList;

public class SessionData implements java.io.Serializable
{
    private final String userName;
    private final String password;
    private final String salt;

    public SessionData(UserDataModel userRecord)
    {
        this.userName = userRecord.getUserName();
        this.password = userRecord.getPassword();
        this.salt = userRecord.getSalt();
    }

    public boolean isTheSame(UserDataModel userRecord)
    {
        return userRecord.getUserName().equals(userName)
                && userRecord.getPassword().equals(password)
                && userRecord.getSalt().equals(salt);
    }

    public UserDataModel getUser()
    {
        for (var obj : UserList.get())
        {
            UserDataModel userRecord = (UserDataModel) obj;
            if (isTheSame(userRecord))
            {
                return userRecord;
            }
        }
        return null;
    }
}
