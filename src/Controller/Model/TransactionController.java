package Controller.Model;

import Controller.Model.Listener.UpdateListener;
import Model.List.TransactionList;
import Model.Model.TransactionDataModel;
import Model.Model.UserDataModel;
import Model.Model.VehicleDataModel;
import View.Form.Input.TransactionForm;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TransactionController extends IDataRecordController
{
    public TransactionController(JTable table, UpdateListener updateListener)
    {
        super(table, updateListener);
    }

    @Override
    public void openCreateWindow(JFrame parent)
    {
        TransactionForm form = new TransactionForm(parent, false, null);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openModifyWindow(JFrame parent)
    {
        TransactionDataModel soldVehicleRecord = (TransactionDataModel) getSelectedItem(TransactionList.get());
        if (soldVehicleRecord == null) { return; }
        TransactionForm form = new TransactionForm(parent, true, soldVehicleRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        TransactionDataModel soldVehicleRecord = (TransactionDataModel) getSelectedItem(TransactionList.get());
        if (soldVehicleRecord == null) { return; }
        int vehicleIndex = TransactionList.get().getIndexForComponent(soldVehicleRecord);
        String deleteMsg = String.format("Are you sure you want to delete sales no.%d from the Sales Log?", vehicleIndex + 1);
        int choice = JOptionPane.showConfirmDialog(null, deleteMsg, "Delete Sales Log", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            TransactionList.get().unregisterComponent(soldVehicleRecord);
            updateListener.onUpdateRecord();
        }
    }

    @Override
    public void loadViewTable()
    {
        String[] header = new String[]
                {
                        "Vehicle ID",
                        "Plate",
                        "Amount",
                        "Date",
                        "Seller",
                        "Buyer"
                };

        var tableDataMatrix = new ArrayList<ArrayList<Object>>();
        for (var obj : TransactionList.get())
        {
            TransactionDataModel vehicleObject = (TransactionDataModel) obj;
            ArrayList<Object> innerData = new ArrayList<>();
            VehicleDataModel vehicle = (VehicleDataModel) obj.getParent();
            UserDataModel seller = vehicle.getSeller();
            UserDataModel buyer = vehicle.getBuyer();
            innerData.add(vehicle.getVIN());
            innerData.add(vehicle.getLicensePlate());
            innerData.add(vehicleObject.getPaidAmount());
            innerData.add(new SimpleDateFormat("dd-MMM-yyyy").format(vehicleObject.getDateOfTransaction().getTime()));
            innerData.add(seller == null ? "-" : seller.getUserName());
            innerData.add(buyer == null ? "-" : buyer.getUserName());
            tableDataMatrix.add(innerData);
        }

        setTableSettings(header, tableDataMatrix);
    }
}
