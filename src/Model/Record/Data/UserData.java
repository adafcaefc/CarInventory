package Model.Record.Data;

import Controller.Utility.PasswordUtilities;
import Model.Enum.UserLevel;

import java.security.NoSuchAlgorithmException;

public class UserData extends IData
{
    private String userName;
    private String password;
    private String salt;
    private UserLevel userLevel;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getSalt()
    {
        return salt;
    }

    public void setSalt(String salt)
    {
        this.salt = salt;
    }

    public UserLevel getUserLevel()
    {
        return userLevel;
    }

    public void setUserLevel(UserLevel userLevel)
    {
        this.userLevel = userLevel;
    }

    public void setPasswordRaw(String str) throws NoSuchAlgorithmException
    {
        this.salt = PasswordUtilities.generateSalt();
        this.password = PasswordUtilities.sha256Salted(str, this.salt);
    }
}