package View.Form.Input;

import Model.Record.BrandRecord;
import Model.Record.DataRecord;
import Model.Pool.BrandPool;
import View.Utility.SpringUtilities;

import javax.swing.*;

public class BrandInputForm extends BaseInputForm
{
    private final JTextField nameTextField = new JTextField();

    public BrandInputForm(JFrame parentFrame, boolean updateRecord, BrandRecord originalBrandRecord)
    {
        super(updateRecord, originalBrandRecord, BrandPool.get());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addLabeledComponent("Name", nameTextField);

        mainBody.add(okButton);
        mainBody.add(cancelButton);

        setTitle("Brand Form");

        SpringUtilities.makeCompactGrid(mainBody, 2, 2, 6, 6, 6, 6);

        pack();
        setLocationRelativeTo(parentFrame);

        loadBrandData(originalBrandRecord);
    }

    @Override
    public DataRecord getFinishedRecord()
    {
        BrandRecord brandRecord = new BrandRecord();
        brandRecord.setBrandName(nameTextField.getText());
        return brandRecord;
    }

    @Override
    public boolean validateInputs()
    {
        return true;
    }

    public void loadBrandData(BrandRecord brandRecord)
    {
        if (brandRecord == null) { return; }
        nameTextField.setText(brandRecord.getBrandName());
    }
}
