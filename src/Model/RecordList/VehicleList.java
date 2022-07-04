package Model.RecordList;

public class VehicleList extends IDataRecordList
{
    private static final VehicleList instance = new VehicleList();

    private VehicleList()
    {
        super(null);
    }

    public static VehicleList get()
    {
        return instance;
    }
}
