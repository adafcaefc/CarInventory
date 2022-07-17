package View.Form.Input;

import Model.ArraySingleton.UserArraySingleton;
import Model.Model.IRecordDataModel;
import Model.Model.UserDataModel;
import Model.Model.UserLevel;

import javax.swing.*;
import java.awt.*;

public class UserInputForm extends IBaseInputForm
{
    public final JComboBox<String> userLevelDropdownBox = new JComboBox<>();
    private final JPasswordField passwordTextField = new JPasswordField();
    private final JPasswordField passwordConfirmationTextField = new JPasswordField();
    private final JTextField userNameTextField = new JTextField();

    public UserInputForm(
            JFrame parentFrame,
            boolean updateRecord,
            UserDataModel originalRecord)
    throws HeadlessException
    {
        super(updateRecord, originalRecord, UserArraySingleton.get());

        setTitle("User Form");

        bindButtons(okButton, cancelButton);

        addComponentPair("User Name", userNameTextField);
        addComponentPair("Password", passwordTextField);
        addComponentPair("Retype", passwordConfirmationTextField);
        addComponentPair("User Level", userLevelDropdownBox);

        buildForm(parentFrame);

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
            for (var obj : UserArraySingleton.get())
            {
                UserDataModel userRecord = (UserDataModel) obj;
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
    public IRecordDataModel getFinishedRecord() throws Exception
    {
        UserDataModel userRecord = new UserDataModel();
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

    public void loadUserData(UserDataModel userRecord)
    {
        if (userRecord == null) { return; }
        userNameTextField.setText(userRecord.getUserName());
        userLevelDropdownBox.setSelectedIndex(userRecord.getUserLevel().getValue());
    }
}
