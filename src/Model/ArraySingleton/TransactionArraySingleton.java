package Model.ArraySingleton;

public class TransactionArraySingleton extends IRecordArraySingleton
{
    private static final TransactionArraySingleton instance = new TransactionArraySingleton();

    public TransactionArraySingleton()
    {
        super(null);
    }

    public static TransactionArraySingleton get()
    {
        return instance;
    }
}
