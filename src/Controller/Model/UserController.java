package Controller.Model;

import Controller.Model.Listener.IUpdateListener;
import Controller.Model.Table.TableData;
import Model.Record.Data.UserData;
import Model.Record.Data.VehicleData;
import Model.Record.List.UserList;
import Model.Record.List.VehicleList;
import View.Form.Input.UserInputForm;
import View.Form.User.RegistrationForm;

import javax.swing.*;
import java.util.ArrayList;

public class UserController extends IController
{
    public UserController(JTable table, IUpdateListener updateListener)
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

    public void openRegistrationWindow(JFrame parent)
    {
        RegistrationForm form = new RegistrationForm(parent, false, null);
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

        for (var objVehicle : VehicleList.get())
        {
            VehicleData vehicle = (VehicleData) objVehicle;
            if (vehicle.getBuyer() == userRecord || vehicle.getSeller() == userRecord)
            {
                String message = String.format(
                        "Error deleting user \"%s\" because the user is connected to vehicle \"%s - %s\"",
                        userRecord.getUserName(),
                        vehicle.getVIN(),
                        vehicle.getModel().getModelName());
                JOptionPane.showMessageDialog(
                        null,
                        message,
                        "Could not delete user",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

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
        ArrayList<TableData> entries = new ArrayList<>();
        entries.add(new TableData("User Name", (n) -> ((UserData) n).getUserName()));
        entries.add(new TableData("User Type", (n) -> ((UserData) n).getUserLevel().name()));
        loadTableData(entries, UserList.get());
    }
}
