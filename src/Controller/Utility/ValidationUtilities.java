package Controller.Utility;

public class ValidationUtilities
{
    private ValidationUtilities() { }

    public static boolean isNumeric(String str)
    {
        try
        {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public static boolean isWholeNumber(String str)
    {
        try
        {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }
}
