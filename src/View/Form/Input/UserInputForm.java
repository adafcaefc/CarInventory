package View.Form.Input;

import Model.Record.DataRecord;
import Model.Record.UserRecord;
import Model.Record.UserLevel;
import Model.Pool.UserPool;
import View.Utility.SpringUtilities;

import javax.swing.*;
import java.awt.*;

public class UserInputForm extends BaseInputForm
{
    private final JPasswordField passwordTextField = new JPasswordField();
    private final JPasswordField passwordConfirmationTextField = new JPasswordField();
    private final JTextField userNameTextField = new JTextField();
    private final JComboBox<String> userLevelDropdownBox = new JComboBox<>();

    public UserInputForm(
            JFrame parentFrame,
            boolean updateRecord,
            UserRecord originalRecord)
    throws HeadlessException
    {
        super(updateRecord, originalRecord, UserPool.get());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addLabeledComponent("UserRecord Name", userNameTextField);
        addLabeledComponent("Password", passwordTextField);
        addLabeledComponent("Retype", passwordConfirmationTextField);
        addLabeledComponent("UserRecord Level", userLevelDropdownBox);

        mainBody.add(okButton);
        mainBody.add(cancelButton);

        setTitle("UserRecord Form");

        SpringUtilities.makeCompactGrid(mainBody, 5, 2, 6, 6, 6, 6);

        pack();
        setLocationRelativeTo(parentFrame);

        populateUserLevelCombobox();
        loadUserData(originalRecord);
    }

    @Override
    public boolean validateInputs()
    {
        boolean inputIsValid = true;

        JComponent[] uiInputs = new JComponent[]
                {
                        userNameTextField,
                        passwordTextField,
                        passwordConfirmationTextField,
                        userLevelDropdownBox
                };

        for (JComponent c : uiInputs) { c.setBackground(Color.WHITE); }

        if (!isUpdateRecord())
        {
            for (var obj : UserPool.get())
            {
                UserRecord userRecord = (UserRecord) obj;
                if (userRecord.getUserName().equals(userNameTextField.getText()))
                {
                    userNameTextField.setBackground(getErrorBackgroundColor());
                    inputIsValid = false;
                }
            }
        }

        if (!new String(passwordTextField.getPassword()).equals(new String(passwordConfirmationTextField.getPassword())))
        {
            passwordConfirmationTextField.setBackground(getErrorBackgroundColor());
            inputIsValid = false;
        }

        if (userNameTextField.getText().isEmpty())
        {
            userNameTextField.setBackground(getErrorBackgroundColor());
            inputIsValid = false;
        }

        if (passwordTextField.getPassword().length == 0)
        {
            passwordTextField.setBackground(getErrorBackgroundColor());
            inputIsValid = false;
        }

        return inputIsValid;
    }

    @Override
    public DataRecord getFinishedRecord() throws Exception
    {
        UserRecord userRecord = new UserRecord();
        userRecord.setUserName(userNameTextField.getText());
        userRecord.setPasswordRaw(new String(passwordTextField.getPassword()));
        userRecord.setUserLevel(UserLevel.valueOf(userLevelDropdownBox.getSelectedIndex()));
        return userRecord;
    }

    public void populateUserLevelCombobox()
    {
        for (var userLevel : UserLevel.values())
        {
            userLevelDropdownBox.addItem(userLevel.toString());
        }
    }

    public void loadUserData(UserRecord userRecord)
    {
        if (userRecord == null) { return; }
        userNameTextField.setText(userRecord.getUserName());
        userLevelDropdownBox.setSelectedIndex(userRecord.getUserLevel().getValue());
    }
}
