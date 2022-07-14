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

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

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

        mainBody.add(cancelButton);
        mainBody.add(okButton);

        setTitle("VehicleRecord Sold");

        cancelButton.setVisible(false);
        okButton.setText("Close");

        SpringUtilities.makeCompactGrid(mainBody, 12, 2, 6, 6, 6, 6);

        pack();
    }
}
