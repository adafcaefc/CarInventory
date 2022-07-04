package View.Form.Login;

import Controller.Session.SessionManager;
import Model.RecordModel.UserRecordModel;
import View.Form.BaseForm;
import View.MainWindow;
import View.Utility.SpringUtilities;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;

public class LoginForm extends BaseForm
{
    private final JTextField userNameTextField = new JTextField();
    private final JPasswordField passwordTextField = new JPasswordField();
    private final MainWindow mainWindow;

    private UserRecordModel logIn() throws NoSuchAlgorithmException
    {
        String userName = userNameTextField.getText();
        String password = String.valueOf(passwordTextField.getPassword());
        return SessionManager.get().logIn(userName, password);
    }

    @Override
    public void bindButtons(JButton okButton, JButton cancelButton)
    {
        okButton.addActionListener(e ->
        {
            try
            {
                UserRecordModel userRecord = logIn();
                if (userRecord != null)
                {
                    JOptionPane.showMessageDialog(
                            null, String.format("Welcome, %s!", userRecord.getUserName()),
                            "Login Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    mainWindow.updateMenuState(MainWindow.VEHICLE_ID);
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(
                            null, "Invalid username or password!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(
                        null, ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dispose());
    }

    public LoginForm(MainWindow mainWindow)
    {
        super();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        bindButtons(okButton, cancelButton);

        mainBody.setLayout(new SpringLayout());

        addLabeledComponent("UserRecord Name", userNameTextField);
        addLabeledComponent("Password", passwordTextField);

        mainBody.add(okButton);
        mainBody.add(cancelButton);

        setTitle("Login Form");

        SpringUtilities.makeCompactGrid(mainBody, 3, 2, 6, 6, 6, 6);

        pack();
        setLocationRelativeTo(mainWindow);

        this.mainWindow = mainWindow;
    }
}
