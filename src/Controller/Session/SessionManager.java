package Controller.Session;

import Controller.Utility.PasswordUtilities;
import Model.Record.Data.UserData;
import Model.Record.List.UserList;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;

public class SessionManager
{
    private static final SessionManager instance = new SessionManager();
    final String USERNAME_FILE = "SESSION_USERNAME.dat";
    final String PASSWORD_FILE = "SESSION_PASSWORD.dat";
    final String SALT_FILE = "SESSION_SALT.dat";
    UserData currentUserRecord = null;

    private SessionManager() { }

    public static SessionManager get() { return instance; }

    public static String loadStringFromFile(String fileName)
    {
        try
        {
            return Files.readString(Path.of(fileName));
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(
                    null, ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return "";
    }

    public static void saveStringToFile(String fileName, String data)
    {
        try
        {
            FileOutputStream file = new FileOutputStream(fileName);
            file.write(data.getBytes());
            file.close();
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(
                    null, ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public UserData getCurrentUser()
    {
        return currentUserRecord;
    }

    public boolean isLoggedIn()
    {
        return currentUserRecord != null;
    }

    public void logOut()
    {
        currentUserRecord = null;
        saveStringToFile(USERNAME_FILE, "");
        saveStringToFile(PASSWORD_FILE, "");
        saveStringToFile(SALT_FILE, "");
    }

    public void saveSession()
    {
        if (!isLoggedIn()) { return; }
        saveStringToFile(USERNAME_FILE, currentUserRecord.getUserName());
        saveStringToFile(PASSWORD_FILE, currentUserRecord.getPassword());
        saveStringToFile(SALT_FILE, currentUserRecord.getSalt());
    }

    public void loadSession()
    {
        if (!new File(USERNAME_FILE).isFile() || !new File(PASSWORD_FILE).isFile() || !new File(SALT_FILE).isFile())
        {
            return;
        }
        var userName = loadStringFromFile(USERNAME_FILE);
        var password = loadStringFromFile(PASSWORD_FILE);
        var salt = loadStringFromFile(SALT_FILE);

        for (var obj : UserList.get())
        {
            UserData userRecord = (UserData) obj;
            if (userRecord.getUserName().equals(userName) && userRecord.getPassword().equals(password))
            {
                currentUserRecord = userRecord;
                break;
            }
        }
    }

    public UserData logIn(String userName, String password) throws NoSuchAlgorithmException
    {
        for (var obj : UserList.get())
        {
            UserData userRecord = (UserData) obj;
            if (userRecord.getUserName().equals(userName) && userRecord.getPassword().equals(PasswordUtilities.sha256Salted(password, userRecord.getSalt())))
            {
                currentUserRecord = userRecord;
                saveSession();
                return userRecord;
            }
        }
        return null;
    }
}
