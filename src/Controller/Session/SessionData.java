package Controller.Session;

import Model.Data.UserData;
import Model.List.UserList;

public class SessionData implements java.io.Serializable
{
    private final String userName;
    private final String password;
    private final String salt;

    public SessionData(UserData userRecord)
    {
        this.userName = userRecord.getUserName();
        this.password = userRecord.getPassword();
        this.salt = userRecord.getSalt();
    }

    public boolean isTheSame(UserData userRecord)
    {
        return userRecord.getUserName().equals(userName)
                && userRecord.getPassword().equals(password)
                && userRecord.getSalt().equals(salt);
    }

    public UserData getUser()
    {
        for (var obj : UserList.get())
        {
            UserData userRecord = (UserData) obj;
            if (isTheSame(userRecord))
            {
                return userRecord;
            }
        }
        return null;
    }
}
