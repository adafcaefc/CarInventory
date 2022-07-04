package View.Form.Input;

import Model.RecordModel.BrandModel;
import Model.RecordModel.IDataRecordModel;
import Model.RecordList.BrandList;
import View.Utility.SpringUtilities;

import javax.swing.*;

public class BrandInputForm extends IBaseInputForm
{
    private final JTextField nameTextField = new JTextField();

    public BrandInputForm(JFrame parentFrame, boolean updateRecord, BrandModel originalBrandRecord)
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
    public IDataRecordModel getFinishedRecord()
    {
        BrandModel brandRecord = new BrandModel();
        brandRecord.setBrandName(nameTextField.getText());
        return brandRecord;
    }

    @Override
    public boolean validateInputs()
    {
        return true;
    }

    public void loadBrandData(BrandModel brandRecord)
    {
        if (brandRecord == null) { return; }
        nameTextField.setText(brandRecord.getBrandName());
    }
}
