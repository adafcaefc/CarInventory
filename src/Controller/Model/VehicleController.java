package Controller.Model;

import Controller.Model.Listener.IUpdateListener;
import Controller.Model.Table.TableData;
import Controller.Session.SessionManager;
import Model.Enum.UserLevel;
import Model.Record.Data.TransactionData;
import Model.Record.Data.UserData;
import Model.Record.Data.VehicleData;
import Model.Record.List.VehicleList;
import View.Form.Input.VehicleInputForm;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class VehicleController extends IController
{
    public VehicleController(JTable table, IUpdateListener updateListener)
    {
        super(table, updateListener);
    }

    @Override
    public void openCreateWindow(JFrame parent)
    {
        VehicleInputForm form = new VehicleInputForm(parent, false, null);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openModifyWindow(JFrame parent)
    {
        VehicleData vehicleRecord = (VehicleData) getSelectedItem(VehicleList.get());
        if (vehicleRecord == null) { return; }
        VehicleInputForm form = new VehicleInputForm(parent, true, vehicleRecord);
        form.bindUpdateListener(updateListener);
        form.setVisible(true);
    }

    @Override
    public void openDeleteWindow()
    {
        VehicleData vehicleRecord = (VehicleData) getSelectedItem(VehicleList.get());
        if (vehicleRecord == null) { return; }
        int vehicleIndex = VehicleList.get().getIndexForComponent(vehicleRecord);
        String deleteMsg = String.format(
                "Are you sure you want to delete vehicleRecord no.%d with VehicleRecord Identification Number %s?",
                vehicleIndex + 1,
                vehicleRecord.getVIN());
        int choice = JOptionPane.showConfirmDialog(null, deleteMsg, "Delete VehicleRecord", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
            VehicleList.get().unregisterComponent(vehicleRecord);
            updateListener.onUpdateRecord();
        }
    }

    @Override
    public void loadViewTable()
    {
        ArrayList<TableData> entries = new ArrayList<>();
        entries.add(new TableData("Vehicle ID", (n) -> ((VehicleData) n).getVIN()));
        entries.add(new TableData("Plate", (n) -> ((VehicleData) n).getLicensePlate()));
        entries.add(new TableData("Model", (n) -> ((VehicleData) n).getModel().getModelName()));

        if (SessionManager.get().isLoggedIn())
        {
            UserLevel level = SessionManager.get().getCurrentUser().getUserLevel();
            if (level == UserLevel.ADMIN || level == UserLevel.PRODUCT_MANAGER || level == UserLevel.SALES_MANAGER)
            {
                entries.add(new TableData("Colour", (n) -> ((VehicleData) n).getColor()));
                entries.add(new TableData("Mileage", (n) -> ((VehicleData) n).getMileage()));
                entries.add(new TableData("Price", (n) -> ((VehicleData) n).getPrice()));
                entries.add(new TableData("VIP Discount", (n) -> String.format("%.2f%%", ((VehicleData) n).getDiscount() * 100.)));
                entries.add(new TableData("Paid", (n) -> String.format("%.2f%%", ((VehicleData) n).getPercentagePaid() * 100.)));
                entries.add(new TableData("Seller", (n) -> ((VehicleData) n).getSeller() == null ? "-" : ((VehicleData) n).getSeller().getUserName()));
                entries.add(new TableData("Buyer", (n) -> ((VehicleData) n).getBuyer() == null ? "-" : ((VehicleData) n).getBuyer().getUserName()));
            }
            else if (level == UserLevel.REGULAR_USER)
            {
                entries.add(new TableData("Price", (n) -> ((VehicleData) n).getPrice()));
                entries.add(new TableData("Seller", (n) -> ((VehicleData) n).getSeller() == null ? "Available" : "Sold"));
            }
            else if (level == UserLevel.VIP_USER)
            {
                entries.add(new TableData("Price", (n) -> (int)(((VehicleData) n).getPrice() * (1. - ((VehicleData) n).getDiscount()))));
                entries.add(new TableData("Seller", (n) -> ((VehicleData) n).getSeller() == null ? "Available" : "Sold"));
            }
        }
        loadTableData(entries, VehicleList.get());
    }
}
