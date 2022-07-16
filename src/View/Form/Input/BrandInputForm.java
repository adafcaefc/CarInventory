package View.Form.Input;

import Model.Record.Data.BrandData;
import Model.Record.Data.IData;
import Model.Record.List.BrandList;

import javax.swing.*;

public class BrandInputForm extends IBaseInputForm
{
    private final JTextField nameTextField = new JTextField();

    public BrandInputForm(JFrame parentFrame, boolean updateRecord, BrandData originalBrandRecord)
    {
        super(updateRecord, originalBrandRecord, BrandList.get());

        setTitle("Brand Form");

        bindButtons(okButton, cancelButton);

        addComponentPair("Name", nameTextField);

        buildForm(parentFrame);

        loadBrandData(originalBrandRecord);
    }

    @Override
    public IData getFinishedRecord()
    {
        BrandData brandRecord = new BrandData();
        brandRecord.setBrandName(nameTextField.getText());
        return brandRecord;
    }

    @Override
    public boolean validateInputs()
    {
        return true;
    }

    public void loadBrandData(BrandData brandRecord)
    {
        if (brandRecord == null) { return; }
        nameTextField.setText(brandRecord.getBrandName());
    }
}
