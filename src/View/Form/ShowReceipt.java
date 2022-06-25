package View.Form;

import Model.DataModel.SoldVehicle;
import View.Utility.SpringUtilities;

import javax.swing.*;

public class ShowReceipt extends BaseForm
{
    private final SoldVehicle soldVehicle;

    @Override
    public void bindButtons(JButton okButton, JButton cancelButton)
    {
        okButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> dispose());
    }

    public ShowReceipt(SoldVehicle soldVehicle)
    {
        super();

        this.soldVehicle = soldVehicle;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addLabeledComponent("Vehicle ID", new JLabel(soldVehicle.getVIN()));
        addLabeledComponent("Plate Number", new JLabel(soldVehicle.getLicensePlate()));
        addLabeledComponent("Colour", new JLabel(soldVehicle.getColor()));
        addLabeledComponent("Mileage", new JLabel(String.valueOf(soldVehicle.getMileage())));
        addLabeledComponent("Model", new JLabel(soldVehicle.getModelName()));
        addLabeledComponent("Model Year", new JLabel(String.valueOf(soldVehicle.getModelYear())));
        addLabeledComponent("Sunroof", new JLabel(soldVehicle.getHasSunroof() ? "Yes" : "No"));
        addLabeledComponent("Number of Doors", new JLabel(String.valueOf(soldVehicle.getDoorCount())));
        addLabeledComponent("Number of Seats", new JLabel(String.valueOf(soldVehicle.getSeatCount())));
        addLabeledComponent("Fuel Capacity", new JLabel(String.valueOf(soldVehicle.getFuelCapacity())));
        addLabeledComponent("Brand", new JLabel(soldVehicle.getBrandName()));

        mainBody.add(cancelButton);
        mainBody.add(okButton);

        setTitle("Vehicle Sold");

        cancelButton.setVisible(false);
        okButton.setText("Close");

        SpringUtilities.makeCompactGrid(mainBody, 12, 2, 6, 6, 6, 6);

        pack();
    }
}
