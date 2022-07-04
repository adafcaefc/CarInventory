package Controller.Session;

import Model.RecordModel.UserRecordModel;
import Model.RecordList.UserList;

public class ActiveSession implements java.io.Serializable
{
    private final String userName;
    private final String password;
    private final String salt;

    public ActiveSession(UserRecordModel userRecord)
    {
        this.userName = userRecord.getUserName();
        this.password = userRecord.getPassword();
        this.salt = userRecord.getSalt();
    }

    public boolean isTheSame(UserRecordModel userRecord)
    {
        return userRecord.getUserName().equals(userName)
                && userRecord.getPassword().equals(password)
                && userRecord.getSalt().equals(salt);
    }

    public UserRecordModel getUser()
    {
        for (var obj : UserList.get())
        {
            UserRecordModel userRecord = (UserRecordModel) obj;
            if (isTheSame(userRecord))
            {
                return userRecord;
            }
        }
        return null;
    }
}
