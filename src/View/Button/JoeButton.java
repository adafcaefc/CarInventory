package View.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JoeButton extends JButton
{
    public static final Color SELECTED_COLOR = new Color(0x183265);
    public static final Color UNSELECTED_COLOR = new Color(0x1F4690);

    public static final Color HOVER_COLOR = new Color(0x3A5BA0);
    public static final Color CLICKED_COLOR = new Color(0x5180E1);

    public static final Color TEXT_COLOR = new Color(0xEFEFEF);
    public static final Color DISABLED_TEXT_COLOR = new Color(0xF1EEE9);

    public static final Font JOE_BUTTON_FONT = new Font("Century Gothic", Font.BOLD, 16);
    boolean isJoeSelected = false;
    boolean isJoeEnabled = true;

    public JoeButton(String text)
    {
        super(text);
        setFocusPainted(false);
        setBorderPainted(false);
        setRolloverEnabled(false);
        updateAppearance();
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent evt)
            {
                if (isEnabled()) { setBackground(HOVER_COLOR); }
            }

            @Override
            public void mouseExited(MouseEvent evt)
            {
                setBackground(getSelectedColor());
            }
        });
        setFont(JOE_BUTTON_FONT);
    }

    public boolean isJoeSelected()
    {
        return isJoeSelected;
    }

    public void setJoeSelected(boolean joeSelected)
    {
        setSelected(joeSelected);
        isJoeSelected = joeSelected;
        updateAppearance();
    }

    public boolean isJoeEnabled()
    {
        return isJoeEnabled;
    }

    public void setJoeEnabled(boolean joeEnabled)
    {
        setEnabled(joeEnabled);
        isJoeEnabled = joeEnabled;
        updateAppearance();
    }

    private Color getSelectedColor()
    {
        return isJoeSelected ? SELECTED_COLOR : UNSELECTED_COLOR;
    }

    private Color getTextColor()
    {
        return isJoeEnabled ? TEXT_COLOR : DISABLED_TEXT_COLOR;
    }

    public void updateAppearance()
    {
        setBackground(getSelectedColor());
        setForeground(getTextColor());
    }
}
