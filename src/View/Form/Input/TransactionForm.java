package View.Form.Input;

import Model.Model.*;
import Model.List.TransactionList;
import Model.List.VehicleList;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TransactionForm extends IBaseInputForm
{
    private final JSpinner paidAmountSpinner = new JSpinner();
    private final JComboBox<Integer> dayComboBox = new JComboBox<>();
    private final JComboBox<String> monthComboBox = new JComboBox<>();
    private final JComboBox<String> vehicleComboBox = new JComboBox<>();
    private final JSpinner yearSpinner = new JSpinner();

    public TransactionForm(
            JFrame parentFrame,
            boolean updateRecord,
            TransactionDataModel originalRecord) throws HeadlessException
    {
        super(updateRecord, originalRecord, TransactionList.get());

        setTitle("Transaction Form");

        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addComponentPair("Amount", paidAmountSpinner);
        addComponentPair("Day", dayComboBox);
        addComponentPair("Month", monthComboBox);
        addComponentPair("Year", yearSpinner);

        if (!updateRecord) { addComponentPair("Vehicle", vehicleComboBox); }
        else { addComponentPair("Vehicle", new JLabel(originalRecord.getVehicle().getVIN())); }

        buildForm(parentFrame);

        setupYearComboBox();
        setupMonthComboBox();
        populateDateCheckbox();
        populateVehicleCombobox();

        loadSoldVehicleData(originalRecord);
    }

    public void populateVehicleCombobox()
    {
        for (var obj : VehicleList.get())
        {
            VehicleDataModel vehicleData = (VehicleDataModel) obj;
            vehicleComboBox.addItem(vehicleData.getVIN() + " - " + vehicleData.getModel().getModelName());
        }
        vehicleComboBox.setSelectedItem(null);
    }

    @Override
    public IRecordDataModel getFinishedRecord() throws Exception
    {
        IRecordDataModel parentVehicle = VehicleList.get().getComponentAt(vehicleComboBox.getSelectedIndex());
        TransactionDataModel modifiedRecord = new TransactionDataModel((VehicleDataModel) parentVehicle);

        var date = new GregorianCalendar(
                (int) yearSpinner.getValue(),
                monthComboBox.getSelectedIndex(),
                dayComboBox.getSelectedIndex() + 1);

        modifiedRecord.setDateOfTransaction(date);
        modifiedRecord.setPaidAmount((int) paidAmountSpinner.getValue());

        return modifiedRecord;
    }

    @Override
    public boolean validateInputs()
    {
        return true;
    }

    public void populateDateCheckbox()
    {
        try
        {
            dayComboBox.removeAllItems();
            int date = (int) yearSpinner.getValue();
            int month = monthComboBox.getSelectedIndex();
            Calendar calendar = new GregorianCalendar(date, month, 1);

            for (int i = 1; i <= calendar.getActualMaximum(Calendar.DATE); i++)
            {
                dayComboBox.addItem(i);
            }
        }
        catch (NumberFormatException ex)
        {
            for (int i = 1; i <= 31; i++) { dayComboBox.addItem(i); }
        }
    }

    public void setupYearComboBox()
    {
        int currentYear = GregorianCalendar.getInstance().getWeekYear();
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(currentYear, 1900, 2100, 1);
        yearSpinner.setModel(spinnerModel);
        yearSpinner.setValue(currentYear);
        yearSpinner.setEditor(new JSpinner.NumberEditor(yearSpinner, "#"));
        yearSpinner.addChangeListener(e -> populateDateCheckbox());
    }

    public void loadSoldVehicleData(TransactionDataModel soldVehicleRecord)
    {
        var default_date = new GregorianCalendar();
        yearSpinner.setValue(default_date.get(Calendar.YEAR));
        monthComboBox.setSelectedIndex(default_date.get(Calendar.MONTH));
        dayComboBox.setSelectedIndex(default_date.get(Calendar.DAY_OF_MONTH) - 1);

        if (soldVehicleRecord == null) { return; }
        var date = soldVehicleRecord.getDateOfTransaction();
        paidAmountSpinner.setValue(soldVehicleRecord.getPaidAmount());
        yearSpinner.setValue(date.get(Calendar.YEAR));
        monthComboBox.setSelectedIndex(date.get(Calendar.MONTH));
        dayComboBox.setSelectedIndex(date.get(Calendar.DAY_OF_MONTH) - 1);
        int vehicleIndex = VehicleList.get().getIndexForComponent(soldVehicleRecord.getVehicle());
        vehicleComboBox.setSelectedIndex(vehicleIndex);
    }

    public void setupMonthComboBox()
    {
        monthComboBox.addItem("January");
        monthComboBox.addItem("February");
        monthComboBox.addItem("March");
        monthComboBox.addItem("April");
        monthComboBox.addItem("May");
        monthComboBox.addItem("June");
        monthComboBox.addItem("July");
        monthComboBox.addItem("August");
        monthComboBox.addItem("September");
        monthComboBox.addItem("October");
        monthComboBox.addItem("November");
        monthComboBox.addItem("December");
        monthComboBox.addActionListener(e -> populateDateCheckbox());
    }
}
