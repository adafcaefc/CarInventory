package Controller.CRUD;

import Model.Model.User;
import Model.Pool.UserPool;
import View.Form.Input.UserInputForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class UserController extends DataRecordController
{
    public UserController(JTable table, UpdateListener updateListener)
    {
        super(table, updateListener);
    }

    @Override
    public void openCreateWindow(JFrame parent)
    {
        UserInputForm form = new UserInputForm(parent, false, null);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openModifyWindow(JFrame parent)
    {
        User user = (User) getSelectedItem(UserPool.get());
        if (user == null) { return; }
        UserInputForm form = new UserInputForm(parent, true, user);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        User user = (User) getSelectedItem(UserPool.get());
        if (user == null) { return; }
        int userIndex = UserPool.get().getIndexForComponent(user);
        String deleteMsg = String.format(
                "Are you sure you want to delete user no.%d (%s)?",
                userIndex + 1,
                user.getUserName());
        int choice = JOptionPane.showConfirmDialog(
                null,
                deleteMsg,
                "Delete User",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            UserPool.get().unregisterComponent(user);
            updateListener.onDataModelsChanged();
        }
    }

    @Override
    public void loadViewTable()
    {
        String[] header = new String[]
                {
                        "User Name",
                        "User Type",
                };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : UserPool.get())
        {
            User user = (User) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(user.getUserName());
            innerData.add(user.getUserLevel().name());
            tableDataMatrix.add(innerData);
        }

        setTableSettings(header, tableDataMatrix);
    }
}
