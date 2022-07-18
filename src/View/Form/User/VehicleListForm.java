package View.Form.User;

import Controller.Session.SessionManager;
import Model.Record.Data.UserData;
import Model.Record.Data.VehicleData;
import Model.Record.List.VehicleList;
import View.Form.IBaseForm;
import View.MainWindow;

import javax.swing.*;

public class VehicleListForm extends IBaseForm
{
    public VehicleListForm(MainWindow mainWindow)
    {
        super();

        bindButtons(okButton, cancelButton);

        setTitle("Vehicle List Form");

        for (var obj : VehicleList.get())
        {
            VehicleData v = (VehicleData) obj;
            if (v.getBuyer() == SessionManager.get().getCurrentUser())
            {
                addComponentPair(
                        v.getVIN() + " - " + v.getModel().getModelName(),
                        String.format("%d/%.0f (%s)", v.getPaidAmount(), v.getRealPrice(), v.getPercentagePaid() >= 1 ? "Paid" : "Unpaid"));
            }
        }
        buildForm(mainWindow);
    }

    @Override
    public void bindButtons(JButton okButton, JButton cancelButton)
    {
        okButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> dispose());
    }
}
