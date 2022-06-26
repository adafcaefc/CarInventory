import Controller.Database.DatabaseManager;
import Controller.Session.SessionManager;
import View.MainWindow;

public class Main
{
    public static void main(String[] args)
    {
        var databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/joecar", "root", "");
        databaseManager.loadData();
        SessionManager.get().loadSession();
        new Thread(() -> new MainWindow(databaseManager).setVisible(true)).start();
    }
}