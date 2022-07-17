package Controller.Model;

import Controller.Model.Listener.IUpdateListener;
import Controller.Model.Table.TableData;
import Model.Record.Data.TransactionData;
import Model.Record.Data.UserData;
import Model.Record.Data.VehicleData;
import Model.Record.List.TransactionList;
import View.Form.Input.TransactionForm;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TransactionController extends IController
{
    public TransactionController(JTable table, IUpdateListener updateListener)
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
        TransactionData soldVehicleRecord = (TransactionData) getSelectedItem(TransactionList.get());
        if (soldVehicleRecord == null) { return; }
        TransactionForm form = new TransactionForm(parent, true, soldVehicleRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        TransactionData soldVehicleRecord = (TransactionData) getSelectedItem(TransactionList.get());
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
        ArrayList<TableData> entries = new ArrayList<>();
        entries.add(new TableData("Vehicle ID", (n) -> ((TransactionData) n).getVehicle().getVIN()));
        entries.add(new TableData("Plate", (n) -> ((TransactionData) n).getVehicle().getLicensePlate()));
        entries.add(new TableData("Amount", (n) -> ((TransactionData) n).getPaidAmount()));
        entries.add(new TableData("Date", (n) -> (new SimpleDateFormat("dd-MMM-yyyy").format(((TransactionData) n).getDateOfTransaction().getTime()))));
        entries.add(new TableData("Seller", (n) -> ((TransactionData) n).getVehicle().getSeller() == null ? "-" : ((TransactionData) n).getVehicle().getSeller().getUserName()));
        entries.add(new TableData("Buyer", (n) -> ((TransactionData) n).getVehicle().getBuyer() == null ? "-" : ((TransactionData) n).getVehicle().getBuyer().getUserName()));
        loadTableData(entries, TransactionList.get());
    }
}
