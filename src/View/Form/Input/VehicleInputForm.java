package View.Form.Input;

import Controller.Utility.ValidationUtilities;
import Model.Data.IRecordData;
import Model.Data.ModelData;
import Model.Data.VehicleData;
import Model.List.ModelList;
import Model.List.VehicleList;
import View.Utility.SpringUtilities;

import javax.swing.*;

public class VehicleInputForm extends IBaseInputForm
{
    private final JTextField vinTextField = new JTextField();
    private final JTextField licensePlateTextField = new JTextField();
    private final JTextField colorTextField = new JTextField();
    private final JTextField mileageTextField = new JTextField();
    private final JComboBox<String> modelDropdownBox = new JComboBox<>();

    public VehicleInputForm(
            JFrame parentFrame,
            boolean updateRecord,
            VehicleData originalRecord)
    {
        super(updateRecord, originalRecord, VehicleList.get());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addComponentPair("VehicleRecord ID", vinTextField);
        addComponentPair("License Plate", licensePlateTextField);
        addComponentPair("Colour", colorTextField);
        addComponentPair("Mileage", mileageTextField);

        if (!updateRecord) { addComponentPair("Model", modelDropdownBox); }
        else { addComponentPair("Model", new JLabel(originalRecord.getModel().getModelName())); }

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
            ModelData modelRecord = (ModelData) obj;
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
    public IRecordData getFinishedRecord() throws Exception
    {
        IRecordData parentModel = ModelList.get().getComponentAt(modelDropdownBox.getSelectedIndex());
        VehicleData vehicleRecord = new VehicleData((ModelData) parentModel);
        vehicleRecord.setVIN(vinTextField.getText());
        vehicleRecord.setLicensePlate(licensePlateTextField.getText());
        vehicleRecord.setColor(colorTextField.getText());
        vehicleRecord.setMileage(Double.parseDouble(mileageTextField.getText()));
        return vehicleRecord;
    }

    public void loadVehicleData(VehicleData vehicleRecord)
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
