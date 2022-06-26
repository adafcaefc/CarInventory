package View.Form.Input;

import Model.Model.Brand;
import Model.Model.DataRecord;
import Model.Pool.BrandPool;
import View.Utility.SpringUtilities;

import javax.swing.*;

public class BrandInputForm extends BaseInputForm
{
    private final JTextField nameTextField = new JTextField();

    public BrandInputForm(JFrame parentFrame, boolean updateRecord, Brand originalBrand)
    {
        super(updateRecord, originalBrand, BrandPool.get());
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

        loadBrandData(originalBrand);
    }

    @Override
    public DataRecord getFinishedRecord()
    {
        Brand brand = new Brand();
        brand.setBrandName(nameTextField.getText());
        return brand;
    }

    @Override
    public boolean validateInputs()
    {
        return true;
    }

    public void loadBrandData(Brand brandObj)
    {
        if (brandObj == null) { return; }
        nameTextField.setText(brandObj.getBrandName());
    }
}
