package Controller.Session;

import Controller.Utility.PasswordUtilities;
import Model.RecordModel.UserRecordModel;
import Model.RecordList.UserList;

import javax.swing.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;

public class SessionManager
{
    UserRecordModel currentUserRecord = null;
    String fileName = "JoeCarSession.dat";
    private SessionManager() { }
    private static final SessionManager instance = new SessionManager();
    public static SessionManager get() { return instance; }

    public UserRecordModel getCurrentUser()
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
    }

    public void saveSession()
    {
        if (!isLoggedIn()) { return; }
        try
        {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(new ActiveSession(currentUserRecord));

            out.close();
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

    public void loadSession()
    {
        if (!new File(fileName).isFile()) { return; }
        try
        {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            currentUserRecord = ((ActiveSession) in.readObject()).getUser();

            in.close();
            file.close();
        }
        catch (IOException | ClassNotFoundException  ex)
        {
            JOptionPane.showMessageDialog(
                    null, ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public UserRecordModel logIn(String userName, String password) throws NoSuchAlgorithmException
    {
        for (var obj : UserList.get())
        {
            UserRecordModel userRecord = (UserRecordModel) obj;
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
