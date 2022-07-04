package Controller.CRUD;

import Model.RecordModel.VehicleModel;
import Model.RecordList.VehicleList;
import View.Form.Input.VehicleInputForm;

import javax.swing.*;
import java.util.ArrayList;

public class VehicleController extends IDataRecordController
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
        VehicleModel vehicleRecord = (VehicleModel) getSelectedItem(VehicleList.get());
        if (vehicleRecord == null) { return; }
        VehicleInputForm form = new VehicleInputForm(parent, true, vehicleRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        VehicleModel vehicleRecord = (VehicleModel) getSelectedItem(VehicleList.get());
        if (vehicleRecord == null) { return; }
        int vehicleIndex = VehicleList.get().getIndexForComponent(vehicleRecord);
        String deleteMsg = String.format(
                "Are you sure you want to delete vehicleRecord no.%d with VehicleRecord Identification Number %s?",
                vehicleIndex + 1,
                vehicleRecord.getVIN());
        int choice = JOptionPane.showConfirmDialog(null, deleteMsg, "Delete VehicleRecord", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            VehicleList.get().unregisterComponent(vehicleRecord);
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
                        "Colour",
                        "Mileage",
                        "Model"
                };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : VehicleList.get())
        {
            VehicleModel vehicleRecord = (VehicleModel) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(vehicleRecord.getVIN());
            innerData.add(vehicleRecord.getLicensePlate());
            innerData.add(vehicleRecord.getColor());
            innerData.add(vehicleRecord.getMileage());
            innerData.add(vehicleRecord.getModel().getModelName());
            tableDataMatrix.add(innerData);
        }

        setTableSettings(header, tableDataMatrix);
    }
}
