package Controller.CRUD;

import javax.swing.*;

import Model.DataModel.Model;
import Model.DataPool.ModelPool;

public class ModelController extends DataRecordController {


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
    public void loadViewTable()
    {

    }
}
