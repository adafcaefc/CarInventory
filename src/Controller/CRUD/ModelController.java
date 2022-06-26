package Controller.CRUD;

import Model.Record.ModelRecord;
import Model.Pool.ModelPool;
import View.Form.Input.ModelInputForm;

import javax.swing.*;
import java.util.ArrayList;

public class ModelController extends DataRecordController
{
    public ModelController(JTable table, UpdateListener updateListener)
    {
        super(table, updateListener);
    }

    @Override
    public void openCreateWindow(JFrame parent)
    {
        ModelInputForm form = new ModelInputForm(parent, false, null);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openModifyWindow(JFrame parent)
    {
        ModelRecord modelRecord = (ModelRecord) getSelectedItem(ModelPool.get());
        if (modelRecord == null) { return; }
        ModelInputForm form = new ModelInputForm(parent, true, modelRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        ModelRecord modelRecord = (ModelRecord) getSelectedItem(ModelPool.get());
        if (modelRecord == null) { return; }

        int modelIndex = ModelPool.get().getIndexForComponent(modelRecord);
        int childrenCount = modelRecord.countChildren();

        String deleteMsg = String.format(
                "Are you sure you want to delete model no.%d (%s)?",
                modelIndex + 1,
                modelRecord.getModelName());

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
            ModelPool.get().unregisterComponent(modelRecord);
            updateListener.onDataModelsChanged();
        }
    }

    @Override
    public void loadViewTable()
    {
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
            ModelRecord modelRecord = (ModelRecord) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(modelRecord.getModelName());
            innerData.add(modelRecord.getModelYear());
            innerData.add(modelRecord.getHasSunroof() ? "Yes" : "No");
            innerData.add(modelRecord.getDoorCount());
            innerData.add(modelRecord.getSeatCount());
            innerData.add(modelRecord.getFuelCapacity());
            innerData.add(modelRecord.getBrand().getBrandName());
            tableDataMatrix.add(innerData);
        }

        setTableSettings(header, tableDataMatrix);
    }
}
