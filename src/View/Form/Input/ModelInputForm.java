package View.Form.Input;

import Controller.Utility.ValidationUtilities;
import Model.RecordModel.BrandModel;
import Model.RecordModel.IDataRecordModel;
import Model.RecordModel.ModelModel;
import Model.RecordList.BrandList;
import Model.RecordList.ModelList;
import View.Utility.SpringUtilities;

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
            ModelModel originalRecord)
    throws HeadlessException
    {
        super(updateRecord, originalRecord, ModelList.get());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addLabeledComponent("Name", nameTextField);
        addLabeledComponent("Year", yearTextField);
        addLabeledComponent("Door Count", doorCountSpinner);
        addLabeledComponent("Seat Count", seatCountSpinner);
        addLabeledComponent("Fuel Capacity", capacityTextField);
        addLabeledComponent("Sunroof", hasSunroofCheckbox);

        if (!updateRecord) { addLabeledComponent("Brand", brandDropdownBox); }
        else { addLabeledComponent("Brand", new JLabel(originalRecord.getBrand().getBrandName())); }

        mainBody.add(okButton);
        mainBody.add(cancelButton);

        setTitle("Model Form");

        SpringUtilities.makeCompactGrid(mainBody, 8, 2, 6, 6, 6, 6);

        pack();
        setLocationRelativeTo(parentFrame);

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
    public IDataRecordModel getFinishedRecord() throws Exception
    {
        IDataRecordModel parentBrand = BrandList.get().getComponentAt(brandDropdownBox.getSelectedIndex());
        ModelModel modelRecord = new ModelModel((BrandModel) parentBrand);
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
        for (var obj : BrandList.get())
        {
            BrandModel brandRecord = (BrandModel) obj;
            brandDropdownBox.addItem(brandRecord.getBrandName());
        }
        brandDropdownBox.setSelectedItem(null);
    }

    public void loadModelData(ModelModel modelRecord)
    {
        if (modelRecord == null) { return; }
        nameTextField.setText(modelRecord.getModelName());
        yearTextField.setText(modelRecord.getModelYear().toString());
        hasSunroofCheckbox.setSelected(modelRecord.getHasSunroof());
        doorCountSpinner.setValue(modelRecord.getDoorCount());
        seatCountSpinner.setValue(modelRecord.getSeatCount());
        capacityTextField.setText(modelRecord.getFuelCapacity().toString());
        int brandIndex = BrandList.get().getIndexForComponent(modelRecord.getBrand());
        brandDropdownBox.setSelectedIndex(brandIndex);
    }
}
