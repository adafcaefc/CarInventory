package Model.ArraySingleton;

public class UserArraySingleton extends IRecordArraySingleton
{
    private static final UserArraySingleton instance = new UserArraySingleton();

    public UserArraySingleton()
    {
        super(null);
    }

    public static UserArraySingleton get()
    {
        return instance;
    }
}
