package Controller.CRUD;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.DataModel.Vehicle;
import Model.DataPool.VehiclePool;

public class VehicleController extends DataRecordController {
    @Override
    public void loadViewTable() {
        String[] header = new String[] {
                "Vehicle ID",
                "Plate Number",
                "Colour",
                "Mileage",
                "Model"
        };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : VehiclePool.get()) {
            Vehicle vehicleObject = (Vehicle) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(vehicleObject.getVIN());
            innerData.add(vehicleObject.getLicensePlate());
            innerData.add(vehicleObject.getColor());
            innerData.add(vehicleObject.getMileage());
            innerData.add(vehicleObject.getModel().getModelName());
            tableDataMatrix.add(innerData);
        }

        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        for (ArrayList<Object> row : tableDataMatrix) {
            tableModel.addRow(row.toArray());
        }

        table.setModel(tableModel);
        table.setDefaultEditor(Object.class, null); // disable Editing
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void openDeleteWindow() {
        Vehicle vehicle = (Vehicle) getSelectedItem(VehiclePool.get());
        if (vehicle == null) {
            return;
        }
        int vehicleIndex = VehiclePool.get().getIndexForComponent(vehicle);
        String deleteMsg = String.format(
                "Are you sure you want to delete vehicle no.%d with Vehicle Identification Number %s?",
                vehicleIndex + 1,
                vehicle.getVIN());
        int choice = JOptionPane.showConfirmDialog(null, deleteMsg, "Delete Vehicle", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            VehiclePool.get().unregisterComponent(vehicle);
            updateListener.onDataModelsChanged();
        }
    }

    @Override
    public void openModifyWindow(JFrame parent) {

    }

    @Override
    public void openCreateWindow(JFrame parent) {

    }

    public VehicleController(JTable table, UpdateListener updateListener) {
        super(table, updateListener);
    }
}
