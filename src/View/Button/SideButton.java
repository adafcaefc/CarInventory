package View.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SideButton extends JButton
{
    public static final Color SELECTED_COLOR = new Color(0x183265);
    public static final Color UNSELECTED_COLOR = new Color(0x1F4690);

    public static final Color HOVER_COLOR = new Color(0x3A5BA0);
    public static final Color CLICKED_COLOR = new Color(0x5180E1);

    public static final Color TEXT_COLOR = new Color(0xEFEFEF);
    public static final Color DISABLED_TEXT_COLOR = new Color(0xF1EEE9);

    public static final Font BUTTON_FONT = new Font("Century Gothic", Font.BOLD, 16);

    boolean isSideButtonSelected = false;
    boolean isSideButtonEnabled = true;

    public SideButton(String text)
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
        setFont(BUTTON_FONT);
    }

    public boolean isSideButtonSelected()
    {
        return isSideButtonSelected;
    }

    public void setSideButtonSelected(boolean sideButtonSelected)
    {
        setSelected(sideButtonSelected);
        isSideButtonSelected = sideButtonSelected;
        updateAppearance();
    }

    public boolean isSideButtonEnabled()
    {
        return isSideButtonEnabled;
    }

    public void setSideButtonEnabled(boolean sideButtonEnabled)
    {
        setEnabled(sideButtonEnabled);
        isSideButtonEnabled = sideButtonEnabled;
        updateAppearance();
    }

    private Color getSelectedColor()
    {
        return isSideButtonSelected ? SELECTED_COLOR : UNSELECTED_COLOR;
    }

    private Color getTextColor()
    {
        return isSideButtonEnabled ? TEXT_COLOR : DISABLED_TEXT_COLOR;
    }

    public void updateAppearance()
    {
        setBackground(getSelectedColor());
        setForeground(getTextColor());
    }
}
