package Model.List;

public class SoldVehicleList extends IRecordList
{
    private static final SoldVehicleList instance = new SoldVehicleList();

    public SoldVehicleList()
    {
        super(null);
    }

    public static SoldVehicleList get()
    {
        return instance;
    }
}
