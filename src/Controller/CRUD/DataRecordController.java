package Controller.CRUD;

import Model.Model.DataRecord;
import Model.Pool.DataRecordPool;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public abstract class DataRecordController
{
    protected JTable table;
    protected UpdateListener updateListener;

    public DataRecordController(JTable table, UpdateListener updateListener)
    {
        this.table = table;
        this.updateListener = updateListener;
    }

    public final DataRecord getSelectedItem(DataRecordPool pool)
    {
        int row = table.getSelectedRow();
        if (row == -1 || row >= pool.countRegisteredComponents()) { return null; }
        return pool.getComponentAt(row);
    }

    public void setTableSettings(
            String[] header,
            ArrayList<ArrayList<Object>> tableDataMatrix)
    {
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        for (ArrayList<Object> row : tableDataMatrix) { tableModel.addRow(row.toArray()); }

        table.setModel(tableModel);
        table.setDefaultEditor(Object.class, null);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public abstract void openCreateWindow(JFrame parent);

    public abstract void openModifyWindow(JFrame parent);

    public abstract void openDeleteWindow();

    public abstract void loadViewTable();
}

