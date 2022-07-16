package View.Form.Input;

import Model.ArraySingleton.BrandArraySingleton;
import Model.Model.BrandDataModel;
import Model.Model.IRecordDataModel;

import javax.swing.*;

public class BrandInputForm extends IBaseInputForm
{
    private final JTextField nameTextField = new JTextField();

    public BrandInputForm(JFrame parentFrame, boolean updateRecord, BrandDataModel originalBrandRecord)
    {
        super(updateRecord, originalBrandRecord, BrandArraySingleton.get());

        setTitle("Brand Form");

        bindButtons(okButton, cancelButton);

        addComponentPair("Name", nameTextField);

        buildForm(parentFrame);

        loadBrandData(originalBrandRecord);
    }

    @Override
    public IRecordDataModel getFinishedRecord()
    {
        BrandDataModel brandRecord = new BrandDataModel();
        brandRecord.setBrandName(nameTextField.getText());
        return brandRecord;
    }

    @Override
    public boolean validateInputs()
    {
        return true;
    }

    public void loadBrandData(BrandDataModel brandRecord)
    {
        if (brandRecord == null) { return; }
        nameTextField.setText(brandRecord.getBrandName());
    }
}
