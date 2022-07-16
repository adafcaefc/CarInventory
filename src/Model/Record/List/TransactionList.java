package Model.Record.List;

public class TransactionList extends IList
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
