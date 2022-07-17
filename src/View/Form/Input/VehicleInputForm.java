package View.Form.Input;

import Controller.Utility.ValidationUtilities;
import Model.ArraySingleton.ModelArraySingleton;
import Model.ArraySingleton.UserArraySingleton;
import Model.ArraySingleton.VehicleArraySingleton;
import Model.Model.*;

import javax.swing.*;
import java.awt.*;

public class VehicleInputForm extends IBaseInputForm
{
    private final JTextField vinTextField = new JTextField();
    private final JTextField licensePlateTextField = new JTextField();
    private final JTextField colorTextField = new JTextField();
    private final JTextField mileageTextField = new JTextField();
    private final JSpinner priceSpinner = new JSpinner();
    private final JTextField discountTextField = new JTextField();
    private final JComboBox<String> modelDropdownBox = new JComboBox<>();
    private final JComboBox<String> sellerDropdownBox = new JComboBox<>();
    private final JComboBox<String> buyerDropdownBox = new JComboBox<>();

    public VehicleInputForm(
            JFrame parentFrame,
            boolean updateRecord,
            VehicleDataModel originalRecord)
    {
        super(updateRecord, originalRecord, VehicleArraySingleton.get());

        setTitle("Vehicle Form");

        bindButtons(okButton, cancelButton);

        addComponentPair("Vehicle ID", vinTextField);
        addComponentPair("License Plate", licensePlateTextField);
        addComponentPair("Colour", colorTextField);
        addComponentPair("Mileage", mileageTextField);
        addComponentPair("Price", priceSpinner);
        addComponentPair("Discount", discountTextField);
        addComponentPair("Seller", sellerDropdownBox);
        addComponentPair("Buyer", buyerDropdownBox);

        if (!updateRecord) { addComponentPair("Model", modelDropdownBox); }
        else { addComponentPair("Model", new JLabel(originalRecord.getModel().getModelName())); }

        buildForm(parentFrame);

        populateModelCombobox();
        populateUserCombobox();

        loadVehicleData(originalRecord);
    }

    private void populateModelCombobox()
    {
        for (var obj : ModelArraySingleton.get())
        {
            ModelDataModel modelRecord = (ModelDataModel) obj;
            modelDropdownBox.addItem(modelRecord.getModelName());
        }
        modelDropdownBox.setSelectedItem(null);
    }

    private void populateUserCombobox()
    {
        sellerDropdownBox.addItem("-");
        buyerDropdownBox.addItem("-");
        for (var obj : UserArraySingleton.get())
        {
            UserDataModel userData = (UserDataModel) obj;
            sellerDropdownBox.addItem(userData.getUserName());
            buyerDropdownBox.addItem(userData.getUserName());
        }
        sellerDropdownBox.setSelectedItem(null);
        buyerDropdownBox.setSelectedItem(null);
    }

    @Override
    public boolean validateInputs()
    {
        boolean inputIsValid = true;

        JComponent[] uiInputs = new JComponent[]
                {
                        mileageTextField,
                        discountTextField,
                        sellerDropdownBox,
                        buyerDropdownBox,
                        };

        for (JComponent c : uiInputs) { c.setBackground(Color.WHITE); }

        if (!ValidationUtilities.isNumeric(mileageTextField.getText()))
        {
            inputIsValid = false;
            mileageTextField.setBackground(getErrorBackgroundColor());
            mileageTextField.setText("");
        }

        if (!ValidationUtilities.isNumeric(discountTextField.getText()))
        {
            inputIsValid = false;
            discountTextField.setBackground(getErrorBackgroundColor());
            discountTextField.setText("");
        }

        if (buyerDropdownBox.getSelectedIndex() != 0)
        {
            UserDataModel buyer = (UserDataModel) UserArraySingleton.get().getComponentAt(buyerDropdownBox.getSelectedIndex() - 1);
            if (buyer.getUserLevel() != UserLevel.REGULAR_USER && buyer.getUserLevel() != UserLevel.VIP_USER)
            {
                inputIsValid = false;
                buyerDropdownBox.setBackground(getErrorBackgroundColor());
            }
        }

        if (sellerDropdownBox.getSelectedIndex() != 0)
        {
            UserDataModel seller = (UserDataModel) UserArraySingleton.get().getComponentAt(sellerDropdownBox.getSelectedIndex() - 1);
            if (seller.getUserLevel() != UserLevel.SALES_MANAGER)
            {
                inputIsValid = false;
                sellerDropdownBox.setBackground(getErrorBackgroundColor());
            }
        }

        return inputIsValid;
    }

    @Override
    public IRecordDataModel getFinishedRecord() throws Exception
    {
        IRecordDataModel parentModel = ModelArraySingleton.get().getComponentAt(modelDropdownBox.getSelectedIndex());
        VehicleDataModel vehicleRecord = new VehicleDataModel((ModelDataModel) parentModel);
        vehicleRecord.setVIN(vinTextField.getText());
        vehicleRecord.setLicensePlate(licensePlateTextField.getText());
        vehicleRecord.setColor(colorTextField.getText());
        vehicleRecord.setMileage(Double.parseDouble(mileageTextField.getText()));
        vehicleRecord.setPrice((int) priceSpinner.getValue());
        vehicleRecord.setDiscount(Double.parseDouble(discountTextField.getText()));

        if (sellerDropdownBox.getSelectedIndex() > 0)
        {
            vehicleRecord.setSeller((UserDataModel) UserArraySingleton.get().getComponentAt(sellerDropdownBox.getSelectedIndex() - 1));
        }
        if (buyerDropdownBox.getSelectedIndex() > 0)
        {
            vehicleRecord.setBuyer((UserDataModel) UserArraySingleton.get().getComponentAt(buyerDropdownBox.getSelectedIndex() - 1));
        }

        return vehicleRecord;
    }

    public void loadVehicleData(VehicleDataModel vehicleRecord)
    {
        if (vehicleRecord == null) { return; }
        vinTextField.setText(vehicleRecord.getVIN());
        licensePlateTextField.setText(vehicleRecord.getLicensePlate());
        colorTextField.setText(vehicleRecord.getColor());
        mileageTextField.setText(vehicleRecord.getMileage().toString());
        int modelIndex = ModelArraySingleton.get().getIndexForComponent(vehicleRecord.getModel());
        modelDropdownBox.setSelectedIndex(modelIndex);
        priceSpinner.setValue(vehicleRecord.getPrice());
        discountTextField.setText(vehicleRecord.getDiscount().toString());
        if (vehicleRecord.getBuyer() != null)
        {
            int buyerIndex = UserArraySingleton.get().getIndexForComponent(vehicleRecord.getBuyer());
            buyerDropdownBox.setSelectedIndex(buyerIndex + 1);
        }
        if (vehicleRecord.getSeller() != null)
        {
            int sellerIndex = UserArraySingleton.get().getIndexForComponent(vehicleRecord.getSeller());
            sellerDropdownBox.setSelectedIndex(sellerIndex + 1);
        }
    }
}
