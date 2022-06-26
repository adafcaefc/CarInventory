package Controller.CRUD;

import Model.Model.Brand;
import Model.Pool.BrandPool;
import View.Form.Input.BrandInputForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BrandController extends DataRecordController
{
    public BrandController(JTable table, UpdateListener updateListener)
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
        Brand brand = (Brand) getSelectedItem(BrandPool.get());
        if (brand == null) { return; }
        BrandInputForm form = new BrandInputForm(parent, true, brand);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        Brand brand = (Brand) getSelectedItem(BrandPool.get());
        if (brand == null) { return; }
        int brandIndex = BrandPool.get().getIndexForComponent(brand);
        int childrenCount = brand.countChildren();
        String deleteMsg = String.format("Are you sure you want to delete model no.%d (%s)?", brandIndex + 1, brand.getBrandName());
        if (childrenCount > 0)
        {
            deleteMsg += "\nAll " + childrenCount + " models with this brand will also be deleted";
        }
        int choice = JOptionPane.showConfirmDialog(null, deleteMsg, "Delete Brands", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            BrandPool.get().unregisterComponent(brand);
            updateListener.onDataModelsChanged();
        }
    }

    @Override
    public void loadViewTable()
    {
        String[] header = new String[]{ "Brand Name" };
        DefaultTableModel tableModel = new DefaultTableModel(header, 0);
        for (var obj : BrandPool.get())
        {
            var brandObject = (Brand) obj;
            tableModel.addRow(new Object[]{ brandObject.getBrandName() });
        }
        table.setModel(tableModel);
        table.setDefaultEditor(Object.class, null); // disable editor
    }
}
