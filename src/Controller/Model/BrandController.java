package Controller.Model;

import Controller.Model.Listener.IUpdateListener;
import Model.Record.Data.BrandData;
import Model.Record.List.BrandList;
import View.Form.Input.BrandInputForm;

import javax.swing.*;
import java.util.ArrayList;

public class BrandController extends IController
{
    public BrandController(JTable table, IUpdateListener updateListener)
    {
        super(table, updateListener);
    }

    @Override
    public void openCreateWindow(JFrame parent)
    {
        BrandInputForm form = new BrandInputForm(parent, false, null);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openModifyWindow(JFrame parent)
    {
        BrandData brandRecord = (BrandData) getSelectedItem(BrandList.get());
        if (brandRecord == null) { return; }
        BrandInputForm form = new BrandInputForm(parent, true, brandRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        BrandData brandRecord = (BrandData) getSelectedItem(BrandList.get());
        if (brandRecord == null) { return; }
        int brandIndex = BrandList.get().getIndexForComponent(brandRecord);
        int childrenCount = brandRecord.countChildren();
        String deleteMsg = String.format("Are you sure you want to delete model no.%d (%s)?", brandIndex + 1, brandRecord.getBrandName());
        if (childrenCount > 0)
        {
            deleteMsg += "\nAll " + childrenCount + " models with this brand will also be deleted";
        }
        int choice = JOptionPane.showConfirmDialog(null, deleteMsg, "Delete Brands", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            BrandList.get().unregisterComponent(brandRecord);
            updateListener.onUpdateRecord();
        }
    }

    @Override
    public void loadViewTable()
    {
        String[] header = new String[]{ "Brand Name" };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : BrandList.get())
        {
            BrandData brandRecord = (BrandData) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            innerData.add(brandRecord.getBrandName());
            tableDataMatrix.add(innerData);
        }

        setTableSettings(header, tableDataMatrix);
    }
}
