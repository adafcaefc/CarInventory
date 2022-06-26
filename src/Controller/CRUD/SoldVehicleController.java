package Controller.CRUD;

import Model.Record.SoldVehicleRecord;
import Model.Pool.SoldVehiclePool;
import View.Form.Input.SoldVehicleInputForm;
import View.Form.Information.ShowSoldVehicleForm;

import javax.swing.*;
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
        SoldVehicleRecord soldVehicleRecord = (SoldVehicleRecord) getSelectedItem(SoldVehiclePool.get());
        if (soldVehicleRecord == null) { return; }
        SoldVehicleInputForm form = new SoldVehicleInputForm(parent, true, soldVehicleRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    public void openShowWindow(JFrame parent)
    {
        SoldVehicleRecord soldVehicleRecord = (SoldVehicleRecord) getSelectedItem(SoldVehiclePool.get());
        if (soldVehicleRecord == null) { return; }
        ShowSoldVehicleForm form = new ShowSoldVehicleForm(soldVehicleRecord);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        SoldVehicleRecord soldVehicleRecord = (SoldVehicleRecord) getSelectedItem(SoldVehiclePool.get());
        if (soldVehicleRecord == null) { return; }
        int vehicleIndex = SoldVehiclePool.get().getIndexForComponent(soldVehicleRecord);
        String deleteMsg = String.format("Are you sure you want to delete sales no.%d from the Sales Log?", vehicleIndex + 1);
        int choice = JOptionPane.showConfirmDialog(null, deleteMsg, "Delete Sales Log", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            SoldVehiclePool.get().unregisterComponent(soldVehicleRecord);
            updateListener.onDataModelsChanged();
        }
    }

    @Override
    public void loadViewTable()
    {
        String[] header = new String[]
                {
                        "VehicleRecord ID",
                        "Plate Number",
                        "Paid Amount",
                        "Sale Date"
                };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : SoldVehiclePool.get())
        {
            SoldVehicleRecord vehicleObject = (SoldVehicleRecord) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(vehicleObject.getVIN());
            innerData.add(vehicleObject.getLicensePlate());
            innerData.add(vehicleObject.getPaidAmount());
            innerData.add(new SimpleDateFormat("dd-MMM-yyyy").format(vehicleObject.getDateOfSale().getTime()));
            tableDataMatrix.add(innerData);
        }

        setTableSettings(header, tableDataMatrix);
    }
}
