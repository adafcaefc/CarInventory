package View.Form.User;

import Controller.Session.SessionManager;
import Model.Record.Data.IData;
import Model.Record.Data.TransactionData;
import Model.Record.Data.VehicleData;
import Model.Record.List.VehicleList;
import View.Form.Input.TransactionForm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class DoTransactionForm extends TransactionForm
{
    @Override
    public void populateVehicleCombobox()
    {
        vehicleComboBox.removeAllItems();
        for (var obj : VehicleList.get())
        {
            VehicleData vehicleData = (VehicleData) obj;
            if (vehicleData.getBuyer() == SessionManager.get().getCurrentUser())
            {
                vehicleTransactionList.add(vehicleData);
                vehicleComboBox.addItem(vehicleData.getVIN() + " - " + vehicleData.getModel().getModelName());
            }
        }
        vehicleComboBox.setSelectedItem(null);
    }

    @Override
    public IData getFinishedRecord() throws Exception
    {
        IData parentVehicle = vehicleTransactionList.get(vehicleComboBox.getSelectedIndex());
        TransactionData modifiedRecord = new TransactionData((VehicleData) parentVehicle);

        var date = new GregorianCalendar(
                (int) yearSpinner.getValue(),
                monthComboBox.getSelectedIndex(),
                dayComboBox.getSelectedIndex() + 1);

        modifiedRecord.setDateOfTransaction(date);
        modifiedRecord.setPaidAmount((int) paidAmountSpinner.getValue());

        return modifiedRecord;
    }

    public DoTransactionForm(
            JFrame parentFrame,
            boolean updateRecord,
            TransactionData originalRecord)
    throws HeadlessException
    {
        super(parentFrame, updateRecord, originalRecord);
    }
}
