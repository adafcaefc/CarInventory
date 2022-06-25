package Controller.Session;

import Model.DataModel.User;
import Model.DataPool.UserPool;

public class UserSession implements java.io.Serializable
{
    private final String userName;
    private final String password;
    private final String salt;

    public UserSession(User user)
    {
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.salt = user.getSalt();
    }

    public boolean isTheSame(User user)
    {
        return user.getUserName().equals(userName)
                && user.getPassword().equals(password)
                && user.getSalt().equals(salt);
    }

    public User getUser()
    {
        for (var obj : UserPool.get())
        {
            User user = (User) obj;
            if (isTheSame(user))
            {
                return user;
            }
        }
        return null;
    }
}
