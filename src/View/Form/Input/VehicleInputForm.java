package View.Form.Input;

import Controller.Utility.ValidationHelper;
import Model.Model.DataRecord;
import Model.Model.Model;
import Model.Model.Vehicle;
import Model.Pool.ModelPool;
import Model.Pool.VehiclePool;
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
            Vehicle originalRecord)
    {
        super(updateRecord, originalRecord, VehiclePool.get());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addLabeledComponent("Vehicle ID", vinTextField);
        addLabeledComponent("License Plate", licensePlateTextField);
        addLabeledComponent("Colour", colorTextField);
        addLabeledComponent("Mileage", mileageTextField);

        if (!updateRecord) { addLabeledComponent("Model", modelDropdownBox); }
        else { addLabeledComponent("Model", new JLabel(originalRecord.getModel().getModelName())); }

        mainBody.add(okButton);
        mainBody.add(cancelButton);

        setTitle("Vehicle Form");

        SpringUtilities.makeCompactGrid(mainBody, 6, 2, 6, 6, 6, 6);

        pack();
        setLocationRelativeTo(parentFrame);

        populateModelCombobox();
        loadVehicleData(originalRecord);
    }

    private void populateModelCombobox()
    {
        for (var obj : ModelPool.get())
        {
            Model model = (Model) obj;
            modelDropdownBox.addItem(model.getModelName());
        }
        modelDropdownBox.setSelectedItem(null);
    }

    @Override
    public boolean validateInputs()
    {
        boolean inputIsValid = true;
        if (!ValidationHelper.isNumeric(mileageTextField.getText()))
        {
            inputIsValid = false;
            mileageTextField.setBackground(getErrorBackgroundColor());
        }
        return inputIsValid;
    }

    @Override
    public DataRecord getFinishedRecord() throws Exception
    {
        DataRecord parentModel = ModelPool.get().getComponentAt(modelDropdownBox.getSelectedIndex());
        Vehicle vehicle = new Vehicle((Model) parentModel);
        vehicle.setVIN(vinTextField.getText());
        vehicle.setLicensePlate(licensePlateTextField.getText());
        vehicle.setColor(colorTextField.getText());
        vehicle.setMileage(Double.parseDouble(mileageTextField.getText()));
        return vehicle;
    }

    public void loadVehicleData(Vehicle vehicleObj)
    {
        if (vehicleObj == null) { return; }
        vinTextField.setText(vehicleObj.getVIN());
        licensePlateTextField.setText(vehicleObj.getLicensePlate());
        colorTextField.setText(vehicleObj.getColor());
        mileageTextField.setText(vehicleObj.getMileage().toString());
        int modelIndex = ModelPool.get().getIndexForComponent(vehicleObj.getModel());
        modelDropdownBox.setSelectedIndex(modelIndex);
    }
}
