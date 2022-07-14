package View.Form.Information;

import Model.Data.SoldVehicleData;
import View.Form.IBaseForm;
import View.Utility.SpringUtilities;

import javax.swing.*;

public class ShowSoldVehicleForm extends IBaseForm
{
    private final SoldVehicleData soldVehicleRecord;

    @Override
    public void bindButtons(JButton okButton, JButton cancelButton)
    {
        okButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> dispose());
    }

    public ShowSoldVehicleForm(SoldVehicleData soldVehicleRecord)
    {
        super();

        this.soldVehicleRecord = soldVehicleRecord;

        setTitle("VehicleRecord Sold");

        bindButtons(okButton, cancelButton);

        addComponentPair("VehicleRecord ID", new JLabel(soldVehicleRecord.getVIN()));
        addComponentPair("Plate Number", new JLabel(soldVehicleRecord.getLicensePlate()));
        addComponentPair("Colour", new JLabel(soldVehicleRecord.getColor()));
        addComponentPair("Mileage", new JLabel(String.valueOf(soldVehicleRecord.getMileage())));
        addComponentPair("Model", new JLabel(soldVehicleRecord.getModelName()));
        addComponentPair("Model Year", new JLabel(String.valueOf(soldVehicleRecord.getModelYear())));
        addComponentPair("Sunroof", new JLabel(soldVehicleRecord.getHasSunroof() ? "Yes" : "No"));
        addComponentPair("Number of Doors", new JLabel(String.valueOf(soldVehicleRecord.getDoorCount())));
        addComponentPair("Number of Seats", new JLabel(String.valueOf(soldVehicleRecord.getSeatCount())));
        addComponentPair("Fuel Capacity", new JLabel(String.valueOf(soldVehicleRecord.getFuelCapacity())));
        addComponentPair("Brand", new JLabel(soldVehicleRecord.getBrandName()));

        cancelButton.setVisible(false);
        okButton.setText("Close");

        buildForm();
    }
}
