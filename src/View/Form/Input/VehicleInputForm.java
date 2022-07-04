package View.Form.Input;

import Controller.Utility.ValidationUtilities;
import Model.RecordModel.DataRecordModel;
import Model.RecordModel.ModelRecordModel;
import Model.RecordModel.VehicleRecordModel;
import Model.RecordList.ModelList;
import Model.RecordList.VehicleList;
import View.Utility.SpringUtilities;

import javax.swing.*;

public class VehicleInputForm extends BaseInputForm
{
    private final JTextField vinTextField = new JTextField();
    private final JTextField licensePlateTextField = new JTextField();
    private final JTextField colorTextField = new JTextField();
    private final JTextField mileageTextField = new JTextField();
    private final JComboBox<String> modelDropdownBox = new JComboBox<>();

    public VehicleInputForm(
            JFrame parentFrame,
            boolean updateRecord,
            VehicleRecordModel originalRecord)
    {
        super(updateRecord, originalRecord, VehicleList.get());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addLabeledComponent("VehicleRecord ID", vinTextField);
        addLabeledComponent("License Plate", licensePlateTextField);
        addLabeledComponent("Colour", colorTextField);
        addLabeledComponent("Mileage", mileageTextField);

        if (!updateRecord) { addLabeledComponent("Model", modelDropdownBox); }
        else { addLabeledComponent("Model", new JLabel(originalRecord.getModel().getModelName())); }

        mainBody.add(okButton);
        mainBody.add(cancelButton);

        setTitle("VehicleRecord Form");

        SpringUtilities.makeCompactGrid(mainBody, 6, 2, 6, 6, 6, 6);

        pack();
        setLocationRelativeTo(parentFrame);

        populateModelCombobox();
        loadVehicleData(originalRecord);
    }

    private void populateModelCombobox()
    {
        for (var obj : ModelList.get())
        {
            ModelRecordModel modelRecord = (ModelRecordModel) obj;
            modelDropdownBox.addItem(modelRecord.getModelName());
        }
        modelDropdownBox.setSelectedItem(null);
    }

    @Override
    public boolean validateInputs()
    {
        boolean inputIsValid = true;
        if (!ValidationUtilities.isNumeric(mileageTextField.getText()))
        {
            inputIsValid = false;
            mileageTextField.setBackground(getErrorBackgroundColor());
        }
        return inputIsValid;
    }

    @Override
    public DataRecordModel getFinishedRecord() throws Exception
    {
        DataRecordModel parentModel = ModelList.get().getComponentAt(modelDropdownBox.getSelectedIndex());
        VehicleRecordModel vehicleRecord = new VehicleRecordModel((ModelRecordModel) parentModel);
        vehicleRecord.setVIN(vinTextField.getText());
        vehicleRecord.setLicensePlate(licensePlateTextField.getText());
        vehicleRecord.setColor(colorTextField.getText());
        vehicleRecord.setMileage(Double.parseDouble(mileageTextField.getText()));
        return vehicleRecord;
    }

    public void loadVehicleData(VehicleRecordModel vehicleRecord)
    {
        if (vehicleRecord == null) { return; }
        vinTextField.setText(vehicleRecord.getVIN());
        licensePlateTextField.setText(vehicleRecord.getLicensePlate());
        colorTextField.setText(vehicleRecord.getColor());
        mileageTextField.setText(vehicleRecord.getMileage().toString());
        int modelIndex = ModelList.get().getIndexForComponent(vehicleRecord.getModel());
        modelDropdownBox.setSelectedIndex(modelIndex);
    }
}
