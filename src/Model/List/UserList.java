package Model.List;

public class UserList extends IRecordList
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
