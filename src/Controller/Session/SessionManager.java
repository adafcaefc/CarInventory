package Controller.Session;

import Controller.Utility.PasswordHelper;
import Model.Model.User;
import Model.Pool.UserPool;

import javax.swing.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;

// singleton
public class SessionManager
{
    User currentUser = null;
    String fileName = "JoeCarSession.dat";
    private SessionManager() { }
    private static final SessionManager instance = new SessionManager();
    public static SessionManager get() { return instance; }

    public User getCurrentUser()
    {
        return currentUser;
    }

    public boolean isLoggedIn()
    {
        return currentUser != null;
    }

    public void logOut()
    {
        currentUser = null;
    }

    public void saveSession()
    {
        if (!isLoggedIn()) { return; }
        try
        {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(new ActiveSession(currentUser));

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

            currentUser = ((ActiveSession) in.readObject()).getUser();

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

    public User logIn(String userName, String password) throws NoSuchAlgorithmException
    {
        for (var obj : UserPool.get())
        {
            User user = (User) obj;
            if (user.getUserName().equals(userName) && user.getPassword().equals(PasswordHelper.sha256Salted(password, user.getSalt())))
            {
                currentUser = user;
                saveSession();
                return user;
            }
        }
        return null;
    }
}
