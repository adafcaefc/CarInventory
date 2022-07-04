package View.Form.Input;

import Model.RecordModel.BrandRecordModel;
import Model.RecordModel.DataRecordModel;
import Model.RecordList.BrandList;
import View.Utility.SpringUtilities;

import javax.swing.*;

public class BrandInputForm extends BaseInputForm
{
    private final JTextField nameTextField = new JTextField();

    public BrandInputForm(JFrame parentFrame, boolean updateRecord, BrandRecordModel originalBrandRecord)
    {
        super(updateRecord, originalBrandRecord, BrandList.get());
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
    public DataRecordModel getFinishedRecord()
    {
        BrandRecordModel brandRecord = new BrandRecordModel();
        brandRecord.setBrandName(nameTextField.getText());
        return brandRecord;
    }

    @Override
    public boolean validateInputs()
    {
        return true;
    }

    public void loadBrandData(BrandRecordModel brandRecord)
    {
        if (brandRecord == null) { return; }
        nameTextField.setText(brandRecord.getBrandName());
    }
}
