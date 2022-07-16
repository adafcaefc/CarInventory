package Controller.Session;

import Model.ArraySingleton.UserArraySingleton;
import Model.Model.UserDataModel;

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
        for (var obj : UserArraySingleton.get())
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
