package View.Form.User;

import Model.Data.UserData;
import Model.Data.UserLevel;
import Model.List.UserList;
import View.Form.Input.UserInputForm;
import View.Utility.FormUtilities;

import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends UserInputForm
{
    public RegistrationForm(
            JFrame parentFrame,
            boolean updateRecord,
            UserData originalRecord)
    throws HeadlessException
    {
        super(parentFrame, updateRecord, originalRecord);
        userLevelDropdownBox.setSelectedIndex(UserLevel.REGULAR_USER.getValue());
        userLevelDropdownBox.setVisible(false);
        FormUtilities.findLabelFor(userLevelDropdownBox).setVisible(false);
    }
}
