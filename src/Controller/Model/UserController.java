package Controller.Model;

import Controller.Model.Listener.UpdateListener;
import Model.List.UserList;
import Model.List.VehicleList;
import Model.Model.UserDataModel;
import Model.Model.VehicleDataModel;
import View.Form.Input.UserInputForm;
import View.Form.User.RegistrationForm;

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

    public void openRegistrationWindow(JFrame parent)
    {
        RegistrationForm form = new RegistrationForm(parent, false, null);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openModifyWindow(JFrame parent)
    {
        UserDataModel userRecord = (UserDataModel) getSelectedItem(UserList.get());
        if (userRecord == null) { return; }
        UserInputForm form = new UserInputForm(parent, true, userRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        UserDataModel userRecord = (UserDataModel) getSelectedItem(UserList.get());
        if (userRecord == null) { return; }

        for (var objVehicle : VehicleList.get())
        {
            VehicleDataModel vehicle = (VehicleDataModel) objVehicle;
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
        String[] header = new String[]
                {
                        "User Name",
                        "User Type",
                        };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : UserList.get())
        {
            UserDataModel userRecord = (UserDataModel) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(userRecord.getUserName());
            innerData.add(userRecord.getUserLevel().name());
            tableDataMatrix.add(innerData);
        }

        setTableSettings(header, tableDataMatrix);
    }
}
