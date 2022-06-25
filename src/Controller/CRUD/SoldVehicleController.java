package Controller.CRUD;

import Model.DataModel.SoldVehicle;
import Model.DataPool.SoldVehiclePool;
import View.Form.InputForm.SoldVehicleForm;
import View.Form.ShowReceipt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SoldVehicleController extends DataRecordController
{
    public SoldVehicleController(JTable table, UpdateListener updateListener)
    {
        super(table, updateListener);
    }

    @Override
    public void openCreateWindow(JFrame parent)
    {
        JOptionPane.showMessageDialog(
                null,
                "You cannot insert sales manually",
                "Invalid Action",
                JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void openModifyWindow(JFrame parent)
    {
        SoldVehicle soldVehicle = (SoldVehicle) getSelectedItem(SoldVehiclePool.get());
        if (soldVehicle == null) { return; }
        SoldVehicleForm form = new SoldVehicleForm(parent, true, soldVehicle);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    public void openShowWindow(JFrame parent)
    {
        SoldVehicle soldVehicle = (SoldVehicle) getSelectedItem(SoldVehiclePool.get());
        if (soldVehicle == null) { return; }
        ShowReceipt form = new ShowReceipt(soldVehicle);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        SoldVehicle soldVehicle = (SoldVehicle) getSelectedItem(SoldVehiclePool.get());
        if (soldVehicle == null) { return; }
        int vehicleIndex = SoldVehiclePool.get().getIndexForComponent(soldVehicle);
        String deleteMsg = String.format("Are you sure you want to delete sales no.%d from the Sales Log?", vehicleIndex + 1);
        int choice = JOptionPane.showConfirmDialog(null, deleteMsg, "Delete Sales Log", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            SoldVehiclePool.get().unregisterComponent(soldVehicle);
            updateListener.onDataModelsChanged();
        }
    }

    @Override
    public void loadViewTable()
    {
        String[] header = new String[]
                {
                        "Vehicle ID",
                        "Plate Number",
                        "Paid Amount",
                        "Sale Date"
                };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : SoldVehiclePool.get())
        {
            SoldVehicle vehicleObject = (SoldVehicle) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(vehicleObject.getVIN());
            innerData.add(vehicleObject.getLicensePlate());
            innerData.add(vehicleObject.getPaidAmount());
            innerData.add(new SimpleDateFormat("dd-MMM-yyyy").format(vehicleObject.getDateOfSale().getTime()));
            tableDataMatrix.add(innerData);
        }

        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        for (ArrayList<Object> row : tableDataMatrix) { tableModel.addRow(row.toArray()); }

        table.setModel(tableModel);
        table.setDefaultEditor(Object.class, null); // disable editor
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
