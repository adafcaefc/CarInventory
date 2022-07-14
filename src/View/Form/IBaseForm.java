package View.Form;

import javax.swing.*;
import java.awt.*;

public abstract class IBaseForm extends JDialog
{
    protected JPanel mainBody = new JPanel();
    protected JButton okButton = new JButton("Ok");
    protected JButton cancelButton = new JButton("Cancel");

    public IBaseForm() { super(); }

    public void bindButtons(JButton okButton, JButton cancelButton)
    {
        cancelButton.addActionListener(e -> dispose());
    }
    public final Color getErrorBackgroundColor()
    {
        return new Color(0xf99597);
    }

    protected void addComponentPair(String text, java.awt.Component c)
    {
        var label = new JLabel(text, JLabel.TRAILING);
        label.setLabelFor(c);
        mainBody.add(label);
        mainBody.add(c);
    }
}
