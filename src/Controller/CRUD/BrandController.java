package Controller.CRUD;

import Model.DataModel.Brand;
import Model.DataPool.BrandPool;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BrandController extends DataRecordController
{
    public BrandController(JTable table, UpdateListener updateListener)
    {
        super(table, updateListener);
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
