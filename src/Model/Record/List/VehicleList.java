package Model.Record.List;

public class VehicleList extends IList
{
    private static final VehicleList instance = new VehicleList();

    private VehicleList()
    {
        super(TransactionList.get());
    }

    public static VehicleList get()
    {
        return instance;
    }
}
