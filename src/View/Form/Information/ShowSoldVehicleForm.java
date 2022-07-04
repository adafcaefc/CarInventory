package View.Form.Information;

import Model.RecordModel.SoldVehicleModel;
import View.Form.IBaseForm;
import View.Utility.SpringUtilities;

import javax.swing.*;

public class ShowSoldVehicleForm extends IBaseForm
{
    private final SoldVehicleModel soldVehicleRecord;

    @Override
    public void bindButtons(JButton okButton, JButton cancelButton)
    {
        okButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> dispose());
    }

    public ShowSoldVehicleForm(SoldVehicleModel soldVehicleRecord)
    {
        super();

        this.soldVehicleRecord = soldVehicleRecord;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addLabeledComponent("VehicleRecord ID", new JLabel(soldVehicleRecord.getVIN()));
        addLabeledComponent("Plate Number", new JLabel(soldVehicleRecord.getLicensePlate()));
        addLabeledComponent("Colour", new JLabel(soldVehicleRecord.getColor()));
        addLabeledComponent("Mileage", new JLabel(String.valueOf(soldVehicleRecord.getMileage())));
        addLabeledComponent("Model", new JLabel(soldVehicleRecord.getModelName()));
        addLabeledComponent("Model Year", new JLabel(String.valueOf(soldVehicleRecord.getModelYear())));
        addLabeledComponent("Sunroof", new JLabel(soldVehicleRecord.getHasSunroof() ? "Yes" : "No"));
        addLabeledComponent("Number of Doors", new JLabel(String.valueOf(soldVehicleRecord.getDoorCount())));
        addLabeledComponent("Number of Seats", new JLabel(String.valueOf(soldVehicleRecord.getSeatCount())));
        addLabeledComponent("Fuel Capacity", new JLabel(String.valueOf(soldVehicleRecord.getFuelCapacity())));
        addLabeledComponent("Brand", new JLabel(soldVehicleRecord.getBrandName()));

        mainBody.add(cancelButton);
        mainBody.add(okButton);

        setTitle("VehicleRecord Sold");

        cancelButton.setVisible(false);
        okButton.setText("Close");

        SpringUtilities.makeCompactGrid(mainBody, 12, 2, 6, 6, 6, 6);

        pack();
    }
}
