package Model.Pool;

public class UserPool extends DataRecordPool
{
    private static final UserPool instance = new UserPool();

    public UserPool()
    {
        super(null);
    }

    public static UserPool get()
    {
        return instance;
    }
}
