package Model.RecordList;

public class UserList extends IDataRecordList
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
