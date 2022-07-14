package Model.List;

public class TransactionList extends IRecordList
{
    private static final TransactionList instance = new TransactionList();

    public TransactionList()
    {
        super(null);
    }

    public static TransactionList get()
    {
        return instance;
    }
}
