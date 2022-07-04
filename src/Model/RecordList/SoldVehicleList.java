package Model.RecordList;

public class SoldVehicleList extends DataRecordList
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
