package View.Form;

import View.Utility.SpringUtilities;

import javax.swing.*;
import java.awt.*;

public abstract class IBaseForm extends JDialog
{
    public int componentPairCount = 0;
    protected JPanel mainBody = new JPanel();
    protected JButton okButton = new JButton("Ok");
    protected JButton cancelButton = new JButton("Cancel");

    public IBaseForm()
    {
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mainBody);
        mainBody.setLayout(new SpringLayout());
    }

    public void bindButtons(JButton okButton, JButton cancelButton)
    {
        cancelButton.addActionListener(e -> dispose());
    }

    public final Color getErrorBackgroundColor()
    {
        return new Color(0xf99597);
    }

    protected void addComponentPair(Component a, Component b)
    {
        mainBody.add(a);
        mainBody.add(b);
        componentPairCount++;
    }

    protected void addComponentPair(String text, Component c)
    {
        var label = new JLabel(text, JLabel.TRAILING);
        label.setLabelFor(c);
        addComponentPair(label, c);
    }

    protected void addComponentPair(String text_1, String text_2)
    {
        var label_1 = new JLabel(text_1, JLabel.TRAILING);
        var label_2 = new JLabel(text_2, JLabel.TRAILING);
        label_1.setLabelFor(label_2);
        addComponentPair(label_1, label_2);
    }

    protected void addComponentPair(Component c)
    {
        addComponentPair("", c);
    }

    protected void buildForm()
    {
        addComponentPair(okButton, cancelButton);
        SpringUtilities.makeCompactGrid(mainBody, componentPairCount, 2, 6, 6, 6, 6);
        pack();
    }

    protected void buildForm(Component relativeComponent)
    {
        buildForm();
        setLocationRelativeTo(relativeComponent);
    }
}
