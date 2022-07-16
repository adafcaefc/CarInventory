package View.Utility;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

public class FormUtilities
{
    public static double calculateRainbow(double value) { return Math.sin(value) * .5 + .5; }
    public static JLabel findLabelFor(JComponent component)
    {
        Optional<Component> label = Arrays.stream(component.getParent().getComponents())
                .filter(c -> c instanceof JLabel && component.equals(((JLabel) c).getLabelFor()))
                .findFirst();
        return (JLabel) label.orElse(null);
    }
}
