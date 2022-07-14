package Controller.Model;

import Controller.Model.Listener.UpdateListener;
import Model.Data.UserData;
import Model.List.UserList;
import View.Form.Input.UserInputForm;

import javax.swing.*;
import java.util.ArrayList;

public class UserController extends IDataRecordController
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
        UserData userRecord = (UserData) getSelectedItem(UserList.get());
        if (userRecord == null) { return; }
        UserInputForm form = new UserInputForm(parent, true, userRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        UserData userRecord = (UserData) getSelectedItem(UserList.get());
        if (userRecord == null) { return; }
        int userIndex = UserList.get().getIndexForComponent(userRecord);
        String deleteMsg = String.format(
                "Are you sure you want to delete userRecord no.%d (%s)?",
                userIndex + 1,
                userRecord.getUserName());
        int choice = JOptionPane.showConfirmDialog(
                null,
                deleteMsg,
                "Delete UserRecord",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            UserList.get().unregisterComponent(userRecord);
            updateListener.onUpdateRecord();
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
        for (var obj : UserList.get())
        {
            UserData userRecord = (UserData) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(userRecord.getUserName());
            innerData.add(userRecord.getUserLevel().name());
            tableDataMatrix.add(innerData);
        }

        setTableSettings(header, tableDataMatrix);
    }
}
