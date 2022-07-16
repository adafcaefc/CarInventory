package Model.Record.List;

public class UserList extends IList
{
    private static final UserList instance = new UserList();

    public UserList()
    {
        super(null);
    }

    public static UserList get()
    {
        return instance;
    }
}
