package Controller.CRUD;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.DataModel.User;
import Model.DataPool.UserPool;

public class UserController extends DataRecordController {
    @Override
    public void loadViewTable() {
        String[] header = new String[] {
                "User Name",
                "User Type",
        };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : UserPool.get()) {
            User user = (User) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(user.getUserName());
            innerData.add(user.getUserLevel().name());
            tableDataMatrix.add(innerData);
        }

        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        for (ArrayList<Object> row : tableDataMatrix) {
            tableModel.addRow(row.toArray());
        }

        table.setModel(tableModel);
        table.setDefaultEditor(Object.class, null); // disable editor
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void openDeleteWindow() {
        User user = (User) getSelectedItem(UserPool.get());
        if (user == null) {
            return;
        }
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
        if (choice == JOptionPane.YES_OPTION) {
            UserPool.get().unregisterComponent(user);
            updateListener.onDataModelsChanged();
        }
    }

    @Override
    public void openModifyWindow(JFrame parent) {

    }

    @Override
    public void openCreateWindow(JFrame parent) {

    }

    public UserController(JTable table, UpdateListener updateListener) {
        super(table, updateListener);
    }
}
