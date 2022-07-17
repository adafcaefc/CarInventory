package View.Form.Input;

import Controller.Utility.ValidationUtilities;
import Model.ArraySingleton.BrandArraySingleton;
import Model.ArraySingleton.ModelArraySingleton;
import Model.Model.BrandDataModel;
import Model.Model.IRecordDataModel;
import Model.Model.ModelDataModel;

import javax.swing.*;
import java.awt.*;

public class ModelInputForm extends IBaseInputForm
{
    private final JTextField nameTextField = new JTextField();
    private final JTextField yearTextField = new JTextField();
    private final JSpinner doorCountSpinner = new JSpinner();
    private final JComboBox<String> brandDropdownBox = new JComboBox<>();
    private final JCheckBox hasSunroofCheckbox = new JCheckBox();
    private final JTextField capacityTextField = new JTextField();
    private final JSpinner seatCountSpinner = new JSpinner();

    public ModelInputForm(
            JFrame parentFrame,
            boolean updateRecord,
            ModelDataModel originalRecord)
    throws HeadlessException
    {
        super(updateRecord, originalRecord, ModelArraySingleton.get());
        setTitle("Model Form");

        bindButtons(okButton, cancelButton);

        addComponentPair("Name", nameTextField);
        addComponentPair("Year", yearTextField);
        addComponentPair("Door Count", doorCountSpinner);
        addComponentPair("Seat Count", seatCountSpinner);
        addComponentPair("Fuel Capacity", capacityTextField);
        addComponentPair("Sunroof", hasSunroofCheckbox);

        if (!updateRecord) { addComponentPair("Brand", brandDropdownBox); }
        else { addComponentPair("Brand", new JLabel(originalRecord.getBrand().getBrandName())); }

        buildForm(parentFrame);

        populateBrandCombobox();
        loadModelData(originalRecord);
    }

    @Override
    public boolean validateInputs()
    {
        boolean inputIsValid = true;

        JComponent[] uiInputs = new JComponent[]
                {
                        nameTextField,
                        yearTextField,
                        capacityTextField,
                        doorCountSpinner,
                        seatCountSpinner,
                        };

        for (JComponent c : uiInputs) { c.setBackground(Color.WHITE); }

        if (!ValidationUtilities.isWholeNumber(yearTextField.getText()))
        {
            yearTextField.setBackground(getErrorBackgroundColor());
            yearTextField.setText("");
            inputIsValid = false;
        }

        if (!ValidationUtilities.isNumeric(capacityTextField.getText()) || capacityTextField.getText().isEmpty())
        {
            capacityTextField.setBackground(getErrorBackgroundColor());
            capacityTextField.setText("");
            inputIsValid = false;
        }

        if (nameTextField.getText().isEmpty())
        {
            inputIsValid = false;
            nameTextField.setBackground(getErrorBackgroundColor());
        }

        return inputIsValid;
    }

    @Override
    public IRecordDataModel getFinishedRecord() throws Exception
    {
        IRecordDataModel parentBrand = BrandArraySingleton.get().getComponentAt(brandDropdownBox.getSelectedIndex());
        ModelDataModel modelRecord = new ModelDataModel((BrandDataModel) parentBrand);
        modelRecord.setModelName(nameTextField.getText());
        modelRecord.setModelYear(Integer.parseInt(yearTextField.getText()));
        modelRecord.setHasSunroof(hasSunroofCheckbox.isSelected());
        modelRecord.setDoorCount((int) doorCountSpinner.getValue());
        modelRecord.setSeatCount((int) seatCountSpinner.getValue());
        modelRecord.setFuelCapacity(Double.parseDouble(capacityTextField.getText()));
        return modelRecord;
    }

    public void populateBrandCombobox()
    {
        for (var obj : BrandArraySingleton.get())
        {
            BrandDataModel brandRecord = (BrandDataModel) obj;
            brandDropdownBox.addItem(brandRecord.getBrandName());
        }
        brandDropdownBox.setSelectedItem(null);
    }

    public void loadModelData(ModelDataModel modelRecord)
    {
        if (modelRecord == null) { return; }
        nameTextField.setText(modelRecord.getModelName());
        yearTextField.setText(modelRecord.getModelYear().toString());
        hasSunroofCheckbox.setSelected(modelRecord.getHasSunroof());
        doorCountSpinner.setValue(modelRecord.getDoorCount());
        seatCountSpinner.setValue(modelRecord.getSeatCount());
        capacityTextField.setText(modelRecord.getFuelCapacity().toString());
        int brandIndex = BrandArraySingleton.get().getIndexForComponent(modelRecord.getBrand());
        brandDropdownBox.setSelectedIndex(brandIndex);
    }
}
