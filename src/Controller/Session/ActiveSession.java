package Controller.Session;

import Model.Record.UserRecord;
import Model.Pool.UserPool;

public class ActiveSession implements java.io.Serializable
{
    private final String userName;
    private final String password;
    private final String salt;

    public ActiveSession(UserRecord userRecord)
    {
        this.userName = userRecord.getUserName();
        this.password = userRecord.getPassword();
        this.salt = userRecord.getSalt();
    }

    public boolean isTheSame(UserRecord userRecord)
    {
        return userRecord.getUserName().equals(userName)
                && userRecord.getPassword().equals(password)
                && userRecord.getSalt().equals(salt);
    }

    public UserRecord getUser()
    {
        for (var obj : UserPool.get())
        {
            UserRecord userRecord = (UserRecord) obj;
            if (isTheSame(userRecord))
            {
                return userRecord;
            }
        }
        return null;
    }
}
