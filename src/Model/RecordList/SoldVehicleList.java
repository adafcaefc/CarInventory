package Model.RecordList;

public class SoldVehicleList extends IDataRecordList
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
