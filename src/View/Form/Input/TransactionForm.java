package View.Form.Input;

import Controller.Utility.ValidationUtilities;
import Model.Data.IRecordData;
import Model.Data.TransactionData;
import Model.List.SoldVehicleList;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TransactionForm extends IBaseInputForm
{
    private final JTextField paidAmountTextField = new JTextField();
    private final JComboBox<Integer> dayComboBox = new JComboBox<>();
    private final JComboBox<String> monthComboBox = new JComboBox<>();
    private final JSpinner yearSpinner = new JSpinner();

    public TransactionForm(
            JFrame parentFrame,
            boolean updateRecord,
            TransactionData originalRecord) throws HeadlessException
    {
        super(updateRecord, originalRecord, SoldVehicleList.get());

        setTitle("Sold VehicleRecord Form");

        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addComponentPair("Paid Amount", paidAmountTextField);
        addComponentPair("Buy Date Day", dayComboBox);
        addComponentPair("Buy Date Month", monthComboBox);
        addComponentPair("Buy Date Year", yearSpinner);

        buildForm(parentFrame);

        setupYearComboBox();
        setupMonthComboBox();
        populateDateCheckbox();

        loadSoldVehicleData(originalRecord);
    }

    @Override
    public IRecordData getFinishedRecord() throws Exception
    {
        var date = new GregorianCalendar(
                (int) yearSpinner.getValue(),
                monthComboBox.getSelectedIndex(),
                dayComboBox.getSelectedIndex() + 1);

        TransactionData originalSoldVehicleRecord = (TransactionData) getOriginalRecord();
        TransactionData modifiedSoldVehicleRecord = (TransactionData) originalSoldVehicleRecord.clone();
        modifiedSoldVehicleRecord.setPaidAmount(Double.parseDouble(paidAmountTextField.getText()));
        modifiedSoldVehicleRecord.setDateOfSale(date);

        return modifiedSoldVehicleRecord;
    }

    @Override
    public boolean validateInputs()
    {
        paidAmountTextField.setBackground(Color.WHITE);
        if (!ValidationUtilities.isNumeric(paidAmountTextField.getText()))
        {
            paidAmountTextField.setBackground(getErrorBackgroundColor());
            paidAmountTextField.setText("");
            return false;
        }
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

    public void loadSoldVehicleData(TransactionData soldVehicleRecord)
    {
        if (soldVehicleRecord == null) { return; }
        var date = soldVehicleRecord.getDateOfSale();
        paidAmountTextField.setText(String.valueOf(soldVehicleRecord.getPaidAmount()));
        dayComboBox.setSelectedIndex(date.get(Calendar.DAY_OF_MONTH));
        monthComboBox.setSelectedIndex(date.get(Calendar.MONTH));
        yearSpinner.setValue(date.get(Calendar.YEAR));
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
