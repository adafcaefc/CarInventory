package View.Form.Input;

import Controller.Utility.ValidationHelper;
import Model.Model.DataRecord;
import Model.Model.SoldVehicle;
import Model.Pool.SoldVehiclePool;
import View.Utility.SpringUtilities;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SoldVehicleInputForm extends BaseInputForm
{
    private final JTextField paidAmountTextField = new JTextField();
    private final JComboBox<Integer> dayComboBox = new JComboBox<>();
    private final JComboBox<String> monthComboBox = new JComboBox<>();
    private final JSpinner yearSpinner = new JSpinner();

    public SoldVehicleInputForm(
            JFrame parentFrame,
            boolean updateRecord,
            SoldVehicle originalRecord) throws HeadlessException
    {
        super(updateRecord, originalRecord, SoldVehiclePool.get());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addLabeledComponent("Paid Amount", paidAmountTextField);
        addLabeledComponent("Buy Date Day", dayComboBox);
        addLabeledComponent("Buy Date Month", monthComboBox);
        addLabeledComponent("Buy Date Year", yearSpinner);

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

        mainBody.add(okButton);
        mainBody.add(cancelButton);

        setTitle("Sold Vehicle Form");

        SpringUtilities.makeCompactGrid(mainBody, 5, 2, 6, 6, 6, 6);

        pack();
        setLocationRelativeTo(parentFrame);

        setupYearComboBox();
        setupMonthComboBox();
        populateDateCheckbox();

        loadSoldVehicleData(originalRecord);
    }

    @Override
    public DataRecord getFinishedRecord() throws Exception
    {
        var date = new GregorianCalendar(
                (int) yearSpinner.getValue(),
                monthComboBox.getSelectedIndex(),
                dayComboBox.getSelectedIndex() + 1);

        SoldVehicle originalSoldVehicle = (SoldVehicle) getOriginalRecord();
        SoldVehicle modifiedSoldVehicle = (SoldVehicle) originalSoldVehicle.clone();
        modifiedSoldVehicle.setPaidAmount(Double.parseDouble(paidAmountTextField.getText()));
        modifiedSoldVehicle.setDateOfSale(date);

        return modifiedSoldVehicle;
    }

    @Override
    public boolean validateInputs()
    {
        paidAmountTextField.setBackground(Color.WHITE);
        if (!ValidationHelper.isNumeric(paidAmountTextField.getText()))
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

    public void loadSoldVehicleData(SoldVehicle soldVehicleObj)
    {
        if (soldVehicleObj == null) { return; }
        var date = soldVehicleObj.getDateOfSale();
        paidAmountTextField.setText(String.valueOf(soldVehicleObj.getPaidAmount()));
        dayComboBox.setSelectedIndex(date.get(Calendar.DAY_OF_MONTH));
        monthComboBox.setSelectedIndex(date.get(Calendar.MONTH));
        yearSpinner.setValue(date.get(Calendar.YEAR));
    }

    public void setupMonthComboBox()
    {
        monthComboBox.addActionListener(e -> populateDateCheckbox());
    }
}
