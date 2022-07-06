package Controller.Model;

import Controller.Model.Listener.UpdateListener;
import Model.Data.ModelDataModel;
import Model.List.ModelList;
import View.Form.Input.ModelInputForm;

import javax.swing.*;
import java.util.ArrayList;

public class ModelController extends IDataRecordController
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
        ModelDataModel modelRecord = (ModelDataModel) getSelectedItem(ModelList.get());
        if (modelRecord == null) { return; }
        ModelInputForm form = new ModelInputForm(parent, true, modelRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        ModelDataModel modelRecord = (ModelDataModel) getSelectedItem(ModelList.get());
        if (modelRecord == null) { return; }

        int modelIndex = ModelList.get().getIndexForComponent(modelRecord);
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
            ModelList.get().unregisterComponent(modelRecord);
            updateListener.onUpdateRecord();
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
        for (var obj : ModelList.get())
        {
            ModelDataModel modelRecord = (ModelDataModel) obj;
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
