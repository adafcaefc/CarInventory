package Controller.CRUD;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Model.DataModel.Model;
import Model.DataPool.ModelPool;

import java.util.ArrayList;

public class ModelController extends DataRecordController {

    public ModelController(JTable table, UpdateListener updateListener) {
        super(table, updateListener);
    }
    @Override
    public void openDeleteWindow()
    {
        Model model = (Model) getSelectedItem(ModelPool.get());
        if (model == null) { return; }

        int modelIndex = ModelPool.get().getIndexForComponent(model);
        int childrenCount = model.countChildren();

        String deleteMsg = String.format(
                "Are you sure you want to delete model no.%d (%s)?",
                modelIndex + 1,
                model.getModelName());

        if (childrenCount > 0)
        {
            deleteMsg += "\nAll " + childrenCount + " vehicles with this model will also be deleted";
        }

        int choice = JOptionPane.showConfirmDialog(
                null,
                deleteMsg,
                "Delete Model",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION)
        {
            ModelPool.get().unregisterComponent(model);
            updateListener.onDataModelsChanged();
        }
    }

    @Override
    public void openCreateWindow(JFrame parent) {

    }

    @Override
    public void openModifyWindow(JFrame parent) {

    }

    @Override
    public void loadViewTable() {
        String[] header = new String[]
                {
                        "Name",
                        "Year",
                        "Sunroof",
                        "Doors",
                        "Seats",
                        "Fuel",
                        "Brand",
                };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : ModelPool.get())
        {
            Model modelObject = (Model) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(modelObject.getModelName());
            innerData.add(modelObject.getModelYear());
            innerData.add(modelObject.getHasSunroof() ? "Yes" : "No");
            innerData.add(modelObject.getDoorCount());
            innerData.add(modelObject.getSeatCount());
            innerData.add(modelObject.getFuelCapacity());
            innerData.add(modelObject.getBrand().getBrandName());
            tableDataMatrix.add(innerData);
        }

        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        for (ArrayList<Object> row : tableDataMatrix) { tableModel.addRow(row.toArray()); }

        table.setModel(tableModel);
        table.setDefaultEditor(Object.class, null); // disable editor
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
