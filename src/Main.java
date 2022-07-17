import Controller.Database.DatabaseManager;
import Controller.Session.SessionManager;
import View.MainWindow;

public class Main
{
    public static void main(String[] args)
    {
        DatabaseManager.get().connect("jdbc:mysql://localhost:3307/joecar", "root", "");
        DatabaseManager.get().loadData();
        SessionManager.get().loadSession();
        new Thread(() -> new MainWindow().setVisible(true)).start();
    }
}