package Controller.CRUD;

import Model.Model.Vehicle;
import Model.Pool.VehiclePool;
import View.Form.Input.VehicleInputForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class VehicleController extends DataRecordController
{
    public VehicleController(JTable table, UpdateListener updateListener)
    {
        super(table, updateListener);
    }

    @Override
    public void openCreateWindow(JFrame parent)
    {
        VehicleInputForm form = new VehicleInputForm(parent, false, null);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openModifyWindow(JFrame parent)
    {
        Vehicle vehicle = (Vehicle) getSelectedItem(VehiclePool.get());
        if (vehicle == null) { return; }
        VehicleInputForm form = new VehicleInputForm(parent, true, vehicle);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        Vehicle vehicle = (Vehicle) getSelectedItem(VehiclePool.get());
        if (vehicle == null) { return; }
        int vehicleIndex = VehiclePool.get().getIndexForComponent(vehicle);
        String deleteMsg = String.format(
                "Are you sure you want to delete vehicle no.%d with Vehicle Identification Number %s?",
                vehicleIndex + 1,
                vehicle.getVIN());
        int choice = JOptionPane.showConfirmDialog(null, deleteMsg, "Delete Vehicle", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            VehiclePool.get().unregisterComponent(vehicle);
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
                        "Colour",
                        "Mileage",
                        "Model"
                };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : VehiclePool.get())
        {
            Vehicle vehicleObject = (Vehicle) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(vehicleObject.getVIN());
            innerData.add(vehicleObject.getLicensePlate());
            innerData.add(vehicleObject.getColor());
            innerData.add(vehicleObject.getMileage());
            innerData.add(vehicleObject.getModel().getModelName());
            tableDataMatrix.add(innerData);
        }

        setTableSettings(header, tableDataMatrix);
    }
}
