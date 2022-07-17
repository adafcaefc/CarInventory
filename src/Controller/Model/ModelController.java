package Controller.Model;

import Controller.Model.Listener.IUpdateListener;
import Controller.Model.Table.TableData;
import Model.Record.Data.ModelData;
import Model.Record.Data.UserData;
import Model.Record.List.ModelList;
import View.Form.Input.ModelInputForm;

import javax.swing.*;
import java.util.ArrayList;

public class ModelController extends IController
{
    public ModelController(JTable table, IUpdateListener updateListener)
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
        ModelData modelRecord = (ModelData) getSelectedItem(ModelList.get());
        if (modelRecord == null) { return; }
        ModelInputForm form = new ModelInputForm(parent, true, modelRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        ModelData modelRecord = (ModelData) getSelectedItem(ModelList.get());
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
        ArrayList<TableData> entries = new ArrayList<>();
        entries.add(new TableData("Model Name", (n) -> ((ModelData) n).getModelName()));
        entries.add(new TableData("Year", (n) -> ((ModelData) n).getModelYear()));
        entries.add(new TableData("Sunroof", (n) -> ((ModelData) n).getHasSunroof() ? "Yes" : "No"));
        entries.add(new TableData("Doors", (n) -> ((ModelData) n).getDoorCount()));
        entries.add(new TableData("Seats", (n) -> ((ModelData) n).getSeatCount()));
        entries.add(new TableData("Fuel", (n) -> ((ModelData) n).getFuelCapacity()));
        entries.add(new TableData("Brand", (n) -> ((ModelData) n).getBrand().getBrandName()));
        loadTableData(entries, ModelList.get());
    }
}
